package vn.teko.test.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import vn.teko.test.App
import vn.teko.test.di.module.AppModule

@Component(
    modules = [AppModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<App> {

    }
}