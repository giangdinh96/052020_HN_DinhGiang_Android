package vn.teko.test.data.repository

import io.reactivex.Single
import vn.teko.test.presentation.model.ProductItem

interface ProductRepository {
    fun getProductList(
        query: String,
        channel: String,
        visitorId: String,
        terminal: String,
        page: Int,
        perPage: Int
    ): Single<List<ProductItem>>

    fun getProductDetail(
        id: String,
        channel: String,
        terminal: String
    ): Single<ProductItem>
}