package vn.teko.test.data.remote.model

data class ApiError(var code: String, override var message: String) : Exception(message)