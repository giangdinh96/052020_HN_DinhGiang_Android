package vn.teko.test.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vn.teko.test.data.remote.model.BaseResponse
import vn.teko.test.data.remote.model.ProductDetailWrapperResponse
import vn.teko.test.data.remote.model.ProductListWrapperResponse

interface ProductService {
    @GET("search")
    fun getProductList(
        @Query("q") query: String,
        @Query("channel") channel: String,
        @Query("visitorId") visitorId: String,
        @Query("terminal") terminal: String,
        @Query("_page") page: Int,
        @Query("_limit") perPage: Int
    ): Single<BaseResponse<ProductListWrapperResponse>>

    @GET("products/{id}")
    fun getProductDetail(
        @Path("id") id: String,
        @Query("channel") channel: String,
        @Query("terminal") terminal: String
    ): Single<BaseResponse<ProductDetailWrapperResponse>>
}