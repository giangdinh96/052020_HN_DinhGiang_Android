package vn.teko.test.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import vn.teko.test.App
import vn.teko.test.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        FontConfigModule::class,
        NetworkConfigModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<App> {

    }
}