package vn.teko.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("url")
    var url: String?
)