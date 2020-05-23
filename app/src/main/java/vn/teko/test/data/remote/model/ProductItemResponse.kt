package vn.teko.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductItemResponse(
    @SerializedName("sku")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("images")
    var images: List<ImageResponse>?,
    @SerializedName("price")
    var price: PriceResponse?
)