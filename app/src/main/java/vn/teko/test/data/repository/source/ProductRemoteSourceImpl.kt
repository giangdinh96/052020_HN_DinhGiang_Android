package vn.teko.test.data.repository.source

import io.reactivex.Observable
import io.reactivex.Single
import vn.teko.test.CODE_SUCCESS
import vn.teko.test.data.mapper.toPresentation
import vn.teko.test.data.remote.ProductService
import vn.teko.test.presentation.model.ProductItem
import javax.inject.Inject

class ProductRemoteSourceImpl @Inject constructor(private val productService: ProductService) :
    ProductRemoteSource {

    override fun getProductList(
        query: String,
        channel: String,
        visitorId: String,
        terminal: String,
        page: Int,
        perPage: Int
    ): Single<List<ProductItem>> {
        return productService.getProductList(query, channel, visitorId, terminal, page, perPage)
            .flatMapObservable {
                if (it.code == CODE_SUCCESS) {
                    Observable.fromIterable(it.result?.products)
                } else {
                    Observable.error(Throwable(it.message))
                }
            }
            .map { it.toPresentation() }
            .toList()
    }

    override fun getProductDetail(
        id: String,
        channel: String,
        terminal: String
    ): Single<ProductItem> {
        return productService.getProductDetail(id, channel, terminal)
            .flatMapObservable {
                if (it.code == CODE_SUCCESS) {
                    Observable.just(it.result?.product)
                } else {
                    Observable.error(Throwable(it.message))
                }
            }
            .map { it.toPresentation() }
            .firstOrError()
    }

}