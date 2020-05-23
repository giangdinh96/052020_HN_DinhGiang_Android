package vn.teko.test.presentation.ui.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import vn.teko.test.base.BaseViewModel
import vn.teko.test.common.LoadMoreStatus
import vn.teko.test.common.RefreshStatus
import vn.teko.test.common.SingleLiveEvent
import vn.teko.test.common.TypeLoading
import vn.teko.test.data.repository.ProductRepository
import vn.teko.test.presentation.model.ProductItem
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val repository: ProductRepository) :
    BaseViewModel() {
    var channel: String = "pv_online"
    var visitorId: String = ""
    var terminal = "CP01"
    private var page: Int = 1
    private val perPage: Int = 20
    private var typeLoading = TypeLoading.REFRESH

    private val _productListResult = MutableLiveData<MutableList<ProductItem>>()
    val productListResult: LiveData<MutableList<ProductItem>>
        get() = _productListResult

    private val _refreshStatus = SingleLiveEvent<RefreshStatus>()
    val refreshStatus: LiveData<RefreshStatus>
        get() = _refreshStatus

    private val _loadMoreStatus = SingleLiveEvent<LoadMoreStatus>()
    val loadMoreStatus: LiveData<LoadMoreStatus>
        get() = _loadMoreStatus

    private val _showToast = SingleLiveEvent<String>()
    val showToast: MutableLiveData<String>
        get() = _showToast

    private val publishSearch = PublishSubject.create<String>()

    init {
        initSearch()
        _refreshStatus.value = RefreshStatus.SHOW
        refresh()
    }

    fun refresh(query: String = "") {
        page = 1
        _loadMoreStatus.value = LoadMoreStatus.DISABLE
        typeLoading = TypeLoading.REFRESH
        _productListResult.value = mutableListOf()
        getProductList(query)
    }

    fun loadMore(query: String) {
        typeLoading = TypeLoading.LOAD_MORE
        getProductList(query)
    }

    private fun getProductList(query: String) {
        publishSearch.onNext(query)
    }

    private fun initSearch() {
        val disposable =
            publishSearch.debounce(1000, TimeUnit.MILLISECONDS)
                .switchMapSingle { query ->
                    if (typeLoading == TypeLoading.REFRESH) {
                        _refreshStatus.postValue(RefreshStatus.SHOW)
                    }
                    repository.getProductList(query, channel, visitorId, terminal, page, perPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(::onGetListError)
                        .onErrorReturn { listOf() }

                }
                .subscribe(::onGetListSuccess)
        addDisposable(disposable)
    }

    private fun onGetListError(error: Throwable) {
        _showToast.value = error.message
        _refreshStatus.value = RefreshStatus.HIDE
        if (typeLoading == TypeLoading.LOAD_MORE) {
            _loadMoreStatus.value = LoadMoreStatus.ERROR
        }
    }

    private fun onGetListSuccess(result: List<ProductItem>?) {
        val newValue = ArrayList<ProductItem>()
        if (typeLoading == TypeLoading.REFRESH) {
            _refreshStatus.value = RefreshStatus.HIDE
        } else {
            _productListResult.value?.let { it1 -> newValue.addAll(it1) }
        }
        if (result != null) {
            newValue.addAll(result)
        }
        _productListResult.value = newValue

        if (newValue.isNotEmpty()) {
            _loadMoreStatus.value = LoadMoreStatus.ENABLE
        }
        if (result == null || result.size < perPage) {
            _loadMoreStatus.value = LoadMoreStatus.END
        } else {
            page++
            _loadMoreStatus.value = LoadMoreStatus.COMPLETE
        }
    }
}