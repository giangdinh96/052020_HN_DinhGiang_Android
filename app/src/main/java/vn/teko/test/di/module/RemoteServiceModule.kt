package vn.teko.test.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import vn.teko.test.data.remote.ProductService

@Module
object RemoteServiceModule {
    @Provides
    @JvmStatic
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)
}