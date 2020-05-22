package vn.teko.test.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vn.teko.test.ui.productlist.ProductListFragment

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeProductListFragment(): ProductListFragment
}