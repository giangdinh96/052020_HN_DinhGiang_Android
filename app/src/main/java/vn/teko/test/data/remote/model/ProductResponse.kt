package vn.teko.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("sku")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("images")
    var images: List<ImageResponse>?,
    @SerializedName("price")
    var price: PriceResponse?,
    @SerializedName("status")
    var status: ProductStatusResponse?,
    @SerializedName("attributeGroups")
    var attributes: List<ProductAttributeResponse>?
)