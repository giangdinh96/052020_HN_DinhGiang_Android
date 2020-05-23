package vn.teko.test.data.repository.source

import io.reactivex.Single
import vn.teko.test.presentation.model.ProductItem

interface ProductRemoteSource {
    fun getProductList(
        query: String,
        channel: String,
        visitorId: String,
        terminal: String,
        page: Int,
        perPage: Int
    ): Single<List<ProductItem>>
}