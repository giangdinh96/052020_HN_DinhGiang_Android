package vn.teko.test.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import vn.teko.test.di.ViewModelKey
import vn.teko.test.presentation.ui.productlist.ProductListViewModel

@Module
abstract class ViewModelModule {

    @ViewModelKey(ProductListViewModel::class)
    @IntoMap
    @Binds
    abstract fun bindProductListViewModel(viewModel: ProductListViewModel): ViewModel
}