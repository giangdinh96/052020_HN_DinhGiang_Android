package vn.teko.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductAttributeResponse(
    @SerializedName("name") var name: String,
    @SerializedName("value") var value: String
)