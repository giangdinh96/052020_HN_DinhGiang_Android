package vn.teko.test.di.module

import dagger.Binds
import dagger.Module
import vn.teko.test.data.repository.ProductRepository
import vn.teko.test.data.repository.ProductRepositoryImpl

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}