package vn.teko.test.presentation.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vn.teko.test.base.BaseViewModel
import vn.teko.test.presentation.model.ProductItem
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor() : BaseViewModel() {
    var productId: String? = ""
    private val _bannerList = MutableLiveData<List<String>>()
    val bannerList: LiveData<List<String>>
        get() = _bannerList

    private val _productMoreList = MutableLiveData<List<ProductItem>>()
    val productMoreList: LiveData<List<ProductItem>>
        get() = _productMoreList

    init {
        val bannerListResult = listOf<String>(
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
            "https://cdn.pixabay.com/photo/2015/02/24/15/41/dog-647528__340.jpg",
            "https://cdn.shopify.com/s/files/1/1327/6929/files/Greece-Zakynthos-Ionian-Islands-960x600_1024x1024.jpg?v=1521125276",
            "https://i.etsystatic.com/13906434/r/il/bb5d11/1415398468/il_570xN.1415398468_9o91.jpg"
        )
        _bannerList.value = bannerListResult

        val productListResult = listOf<ProductItem>(
        )
        _productMoreList.value = productListResult
    }
}