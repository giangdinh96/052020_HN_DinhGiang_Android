package vn.teko.test.presentation.ui.productlist

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vn.teko.test.COLOR_REFRESH_PROGRESS
import vn.teko.test.R
import vn.teko.test.base.BaseFragment
import vn.teko.test.databinding.FragmentProductListBinding
import vn.teko.test.di.AppViewModelFactory
import vn.teko.test.extension.setup
import vn.teko.test.extension.updateLoadMore
import vn.teko.test.extension.updateRefresh
import vn.teko.test.presentation.adapter.ProductItemAdapter
import javax.inject.Inject

class ProductListFragment : BaseFragment<FragmentProductListBinding>() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    lateinit var productListViewModel: ProductListViewModel

    private val productItemAdapter = ProductItemAdapter()

    override fun getLayoutId() = R.layout.fragment_product_list

    override fun init() {
        productListViewModel =
            ViewModelProvider(this, appViewModelFactory)[ProductListViewModel::class.java]

        initProductList()
    }

    private fun initProductList() {
        binding.productListRefreshRcv.setup()
        binding.productListRefreshRcv.adapter = productItemAdapter
        initBackClick()
        initSearchView()
        initRefresh()
        initLoadMore()
    }

    private fun initBackClick() {
        binding.backIv.setOnClickListener {
            activity?.finish()
        }
    }

    private fun initSearchView() {
        binding.searchProductSv.textChangeListener = {
            callRefreshList()
        }
        binding.searchProductSv.onSearchPressListener = {
            callRefreshList()
        }
    }

    private fun initRefresh() {
        binding.productListRefreshSrl.setColorSchemeResources(*COLOR_REFRESH_PROGRESS)
        binding.productListRefreshSrl.setOnRefreshListener {
            callRefreshList()
        }
    }

    private fun initLoadMore() {
        productItemAdapter.loadMoreModule.setOnLoadMoreListener {
            productListViewModel.loadMore(binding.searchProductSv.getText().trim())
        }
    }

    private fun callRefreshList() {
        productListViewModel.refresh(binding.searchProductSv.getText().trim())
    }

    override fun observeData() {
        productListViewModel.productListResult.observe(this, Observer {
            productItemAdapter.setDiffNewData(it)

        })

        productListViewModel.loadMoreStatus.observe(this, Observer {
            productItemAdapter.updateLoadMore(it)
        })

        productListViewModel.refreshStatus.observe(this, Observer {
            binding.productListRefreshSrl.updateRefresh(it)
        })

        productListViewModel.showToast.observe(this, Observer {
            showToast(it)
        })
    }

    companion object {
        fun newInstance(): ProductListFragment {
            return ProductListFragment();
        }
    }
}