package vn.teko.test.di.module

import android.content.Context
import dagger.Module
import vn.teko.test.App
import vn.teko.test.di.AppContext

@Module
abstract class AppModule {

    @AppContext
    abstract fun bindAppContext(app: App): Context
}