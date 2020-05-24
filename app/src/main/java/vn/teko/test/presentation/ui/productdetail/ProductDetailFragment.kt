package vn.teko.test.presentation.ui.productdetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import vn.teko.test.PRODUCT_ID
import vn.teko.test.R
import vn.teko.test.base.BaseFragment
import vn.teko.test.databinding.FragmentProductDetailBinding
import vn.teko.test.di.AppViewModelFactory
import vn.teko.test.extension.setup
import vn.teko.test.presentation.adapter.ProductItemAdapter
import vn.teko.test.widget.AutoViewPager
import javax.inject.Inject

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory
    lateinit var productDetailViewModel: ProductDetailViewModel

    private var bannerAdapter = BannerAdapter()
    private val autoViewPager = AutoViewPager()

    private var productItemAdapter = ProductItemAdapter(R.layout.item_product_horizontal)

    override fun getLayoutId() = R.layout.fragment_product_detail

    override fun init() {
        productDetailViewModel =
            ViewModelProvider(this, appViewModelFactory)[ProductDetailViewModel::class.java]
        productDetailViewModel.productId = arguments?.getString(PRODUCT_ID) ?: ""

        initBanner()
        initProductList()
    }

    private fun initBanner() {
        val pager = binding.containerBanner.bannerVp
        pager.adapter = bannerAdapter
        autoViewPager.apply {
            viewPager2 = pager
            start()
        }
        binding.containerBanner.bannerIndicator.setViewPager2(pager)
    }

    private fun initProductList() {
        productItemAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        binding.containerMoreProduct.productListRcv.setup(orientation = LinearLayoutManager.HORIZONTAL)
        binding.containerMoreProduct.productListRcv.adapter = productItemAdapter
    }

    override fun observeData() {
        productDetailViewModel.bannerList.observe(viewLifecycleOwner, Observer {
            bannerAdapter.setNewInstance(ArrayList(it))
            if (it.isEmpty()) {
                binding.containerBanner.rootCl.visibility = View.GONE
            }
        })

        productDetailViewModel.productMoreList.observe(viewLifecycleOwner, Observer {
            productItemAdapter.setNewInstance(ArrayList(it))
            if (it.isEmpty()) {
                binding.containerMoreProduct.rootCl.visibility = View.GONE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        autoViewPager.stop()
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