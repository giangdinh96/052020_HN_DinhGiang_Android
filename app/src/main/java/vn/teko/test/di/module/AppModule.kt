package vn.teko.test.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import vn.teko.test.App
import vn.teko.test.di.AppContext
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @AppContext
    @Singleton
    abstract fun bindAppContext(app: App): Context
}