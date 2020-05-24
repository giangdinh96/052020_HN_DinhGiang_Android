package vn.teko.test.presentation.ui.productdetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import vn.teko.test.PRODUCT_ID
import vn.teko.test.R
import vn.teko.test.base.BaseFragment
import vn.teko.test.databinding.FragmentProductDetailBinding
import vn.teko.test.di.AppViewModelFactory
import vn.teko.test.extension.setup
import vn.teko.test.presentation.adapter.ProductItemAdapter
import vn.teko.test.presentation.model.ProductItem
import javax.inject.Inject

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    lateinit var productDetailViewModel: ProductDetailViewModel

    private var productItemAdapter = ProductItemAdapter(R.layout.item_product_horizontal)

    override fun getLayoutId() = R.layout.fragment_product_detail

    override fun init() {
        productDetailViewModel =
            ViewModelProvider(this, appViewModelFactory)[ProductDetailViewModel::class.java]
        productDetailViewModel.productId = arguments?.getString(PRODUCT_ID) ?: ""

        initProductList()
    }

    private fun initProductList() {
        binding.containerMoreProduct.productListRcv.setup(orientation = LinearLayoutManager.HORIZONTAL)
        binding.containerMoreProduct.productListRcv.adapter = productItemAdapter

        productItemAdapter.addData(ProductItem("123", "123", "123", "123", "123", "123"))
        productItemAdapter.addData(ProductItem("123", "123", "123", "123", "123", "123"))
        productItemAdapter.addData(ProductItem("123", "123", "123", "123", "123", "123"))
        productItemAdapter.addData(ProductItem("123", "123", "123", "123", "123", "123"))
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