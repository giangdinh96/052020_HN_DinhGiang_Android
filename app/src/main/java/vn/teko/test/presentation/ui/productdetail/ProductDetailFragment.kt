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
import vn.teko.test.common.Resource
import vn.teko.test.databinding.FragmentProductDetailBinding
import vn.teko.test.di.AppViewModelFactory
import vn.teko.test.extension.setup
import vn.teko.test.extension.toLastLetterSuperscriptSpanString
import vn.teko.test.extension.toStrikethroughSpanString
import vn.teko.test.presentation.adapter.BannerAdapter
import vn.teko.test.presentation.adapter.ProductItemAdapter
import vn.teko.test.presentation.model.ProductItem
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

        initToolbarListener()
        initBanner()
        initProductList()
        initPickCartListener()
    }

    private fun initToolbarListener() {
        binding.toolbarTb.setOnActionLeftClickListener {
            fragmentManager?.popBackStack()
        }
        binding.toolbarTb.setOnActionRightClickListener {
            showToast("Cart click")
        }
    }

    private fun initBanner() {
        val pager = binding.banner.bannerVp
        pager.adapter = bannerAdapter
        autoViewPager.apply {
            viewPager2 = pager
            start()
        }
        binding.banner.bannerIndicator.setViewPager2(pager)
    }

    private fun initProductList() {
        productItemAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        binding.moreProduct.productListRcv.setup(orientation = LinearLayoutManager.HORIZONTAL)
        binding.moreProduct.productListRcv.adapter = productItemAdapter
    }

    private fun initPickCartListener() {
        binding.pickCart.minusIv.setOnClickListener {
            productDetailViewModel.minusCounter()
        }

        binding.pickCart.plusIv.setOnClickListener {
            productDetailViewModel.addCounter()
        }

        binding.pickCart.addCartBtn.setOnClickListener {
            productDetailViewModel.addCart()
        }
    }

    override fun observeData() {
        productDetailViewModel.result.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.loading.visibility = View.GONE
                    binding.content.visibility = View.VISIBLE
                    showProductDetail(it.data!!)
                }
                is Resource.Error -> {
                    binding.loading.visibility = View.GONE
                    binding.content.visibility = View.GONE
                    binding.pickCart.root.visibility = View.GONE
                    showToast(it.message!!)
                }
                is Resource.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
            }
        })

        productDetailViewModel.counter.observe(viewLifecycleOwner, Observer {
            binding.pickCart.counterTv.text = it.toString()
        })

        productDetailViewModel.priceWithCounter.observe(viewLifecycleOwner, Observer {
            binding.pickCart.addCartBtn.text = it.toLastLetterSuperscriptSpanString()
        })
    }

    private fun showProductDetail(item: ProductItem) {
        // set toolbar
        binding.toolbarTb.setTitle(item.name)

        // set banner
        val images = item.images
        bannerAdapter.setNewInstance(ArrayList(images))
        if (images.isEmpty()) {
            binding.banner.root.visibility = View.GONE
        }

        // set header info
        val priceTv = binding.headerInfo.priceTv
        val originPriceTv = binding.headerInfo.originPriceTv
        val discountPercentDlv = binding.headerInfo.discountPercentDlv
        binding.headerInfo.nameTv.text = item.name
        binding.headerInfo.idTv.text = item.id
        binding.headerInfo.stateTv.text = item.status

        // set price
        if (item.finalPrice.isEmpty()) {
            priceTv.visibility = View.GONE

            binding.toolbarTb.setSubtitleShow(false)

            binding.pickCart.root.visibility = View.GONE
        } else {
            val finalPrice = item.finalPrice.toLastLetterSuperscriptSpanString()
            priceTv.visibility = View.VISIBLE
            priceTv.text = finalPrice

            binding.toolbarTb.setSubtitleShow(true)
            binding.toolbarTb.setSubtitle(finalPrice)

            binding.pickCart.root.visibility = View.VISIBLE
        }

        if (item.originPrice.isEmpty()) {
            originPriceTv.visibility = View.GONE
        } else {
            originPriceTv.visibility = View.VISIBLE
            originPriceTv.text = item.originPrice.toStrikethroughSpanString()
        }
        if (item.discountPercent.isEmpty()) {
            discountPercentDlv.visibility = View.GONE
        } else {
            discountPercentDlv.visibility = View.VISIBLE
            discountPercentDlv.text = item.discountPercent
        }

        // set product detail
        binding.productDetail.setData(productData = item.attributes)

        // set product more list
        val products = ArrayList<ProductItem>()
        // fake product list
        products.add(item)
        products.add(item.copy(id = "2"))
        products.add(item.copy(id = "3"))
        products.add(item.copy(id = "4"))
        productItemAdapter.setNewInstance(products)
        if (products.isEmpty()) {
            binding.moreProduct.root.visibility = View.GONE
        }
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