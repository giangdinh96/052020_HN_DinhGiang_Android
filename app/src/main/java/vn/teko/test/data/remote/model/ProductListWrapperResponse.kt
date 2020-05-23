package vn.teko.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductListWrapperResponse(@SerializedName("products") var products: List<ProductItemResponse>?)