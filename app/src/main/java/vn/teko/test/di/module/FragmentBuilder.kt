package vn.teko.test.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vn.teko.test.presentation.ui.productdetail.ProductDetailFragment
import vn.teko.test.presentation.ui.productlist.ProductListFragment

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeProductListFragment(): ProductListFragment

    @ContributesAndroidInjector
    abstract fun contributeProductDetailFragment(): ProductDetailFragment
}