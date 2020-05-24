package vn.teko.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductStatusResponse(@SerializedName("sale") var status: String)