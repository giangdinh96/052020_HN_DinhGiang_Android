package vn.teko.test.presentation.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.teko.test.base.BaseViewModel
import vn.teko.test.common.Resource
import vn.teko.test.data.repository.ProductRepository
import vn.teko.test.presentation.model.ProductItem
import vn.teko.test.utils.DecimalFormatUtils
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(private val productRepository: ProductRepository) :
    BaseViewModel() {
    var productId: String = ""
        set(value) {
            field = value
            getProductDetail()
        }
    var channel: String = "pv_online"
    var terminal = "CP01"
    var pricePerItem = 0.0
    private val _result = MutableLiveData<Resource<ProductItem>>()
    val result: LiveData<Resource<ProductItem>>
        get() = _result

    private val _counter = MutableLiveData<Int>(1)
    val counter: LiveData<Int>
        get() = _counter

    private val _priceDisplayWithCounter = MutableLiveData<String>("")
    val priceWithCounter: LiveData<String>
        get() = _priceDisplayWithCounter

    private fun getProductDetail() {
        _result.value = Resource.Loading()
        val disposable = productRepository.getProductDetail(productId, channel, terminal)
            .delay(200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                pricePerItem = it.priceNumber
                _result.value = Resource.Success(it)
                _counter.value = 1
                updatePriceWithCounter()
            }, {
                _result.value = Resource.Error(it.toString())
            })
        addDisposable(disposable)
    }

    private fun updatePriceWithCounter() {
        _priceDisplayWithCounter.value =
            DecimalFormatUtils.formatPrice(pricePerItem * _counter.value!!) + "đ"
    }

    fun addCounter() {
        _counter.value = _counter.value?.plus(1)
        updatePriceWithCounter()
    }

    fun minusCounter() {
        if (_counter.value!! > 1) {
            _counter.value = _counter.value?.minus(1)
            updatePriceWithCounter()
        }
    }

    fun addCart() {

    }
}