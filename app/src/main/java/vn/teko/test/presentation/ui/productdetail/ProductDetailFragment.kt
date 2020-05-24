package vn.teko.test.presentation.ui.productdetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import vn.teko.test.PRODUCT_ID
import vn.teko.test.R
import vn.teko.test.base.BaseFragment
import vn.teko.test.databinding.FragmentProductDetailBinding
import vn.teko.test.di.AppViewModelFactory
import javax.inject.Inject

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory


    lateinit var productDetailViewModel: ProductDetailViewModel

    override fun getLayoutId() = R.layout.fragment_product_detail

    override fun init() {
        productDetailViewModel =
            ViewModelProvider(this, appViewModelFactory)[ProductDetailViewModel::class.java]
        productDetailViewModel.productId = arguments?.getString(PRODUCT_ID) ?: ""

    }

    override fun observeData() {

    }

    companion object {
        fun newInstance(productId: String): ProductDetailFragment {
            val result = ProductDetailFragment()
            val data = Bundle()
            data.putString(PRODUCT_ID, productId)
            result.arguments = data
            return result
        }
    }
}