package vn.teko.test.data.repository

import io.reactivex.Single
import vn.teko.test.data.repository.source.ProductRemoteSource
import vn.teko.test.presentation.model.ProductItem
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productRemoteSource: ProductRemoteSource) :
    ProductRepository {
    override fun getProductList(
        query: String,
        channel: String,
        visitorId: String,
        terminal: String,
        page: Int,
        perPage: Int
    ): Single<List<ProductItem>> {
        return productRemoteSource.getProductList(
            query,
            channel,
            visitorId,
            terminal,
            page,
            perPage
        )
    }

    override fun getProductDetail(
        id: String,
        channel: String,
        terminal: String
    ): Single<ProductItem> {
        return productRemoteSource.getProductDetail(id, channel, terminal)
    }

}