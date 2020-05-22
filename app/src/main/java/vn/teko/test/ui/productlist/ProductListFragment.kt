package vn.teko.test.ui.productlist

import androidx.lifecycle.ViewModelProvider
import vn.teko.test.R
import vn.teko.test.base.BaseFragment
import vn.teko.test.databinding.FragmentProductListBinding
import vn.teko.test.di.AppViewModelFactory
import javax.inject.Inject

class ProductListFragment : BaseFragment<FragmentProductListBinding>() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    lateinit var productListViewModel: ProductListViewModel

    override fun getLayoutId() = R.layout.fragment_product_list

    override fun init() {
        productListViewModel =
            ViewModelProvider(this, appViewModelFactory)[ProductListViewModel::class.java]
    }

    override fun observeData() {

    }

    companion object {
        fun newInstance(): ProductListFragment {
            return ProductListFragment();
        }
    }
}