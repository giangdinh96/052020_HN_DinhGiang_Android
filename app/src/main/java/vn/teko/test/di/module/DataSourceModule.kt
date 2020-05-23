package vn.teko.test.di.module

import dagger.Binds
import dagger.Module
import vn.teko.test.data.repository.source.ProductRemoteSource
import vn.teko.test.data.repository.source.ProductRemoteSourceImpl

@Module
abstract class DataSourceModule {
    @Binds
    abstract fun bindProductRemoteSource(productRemoteSourceImpl: ProductRemoteSourceImpl): ProductRemoteSource
}