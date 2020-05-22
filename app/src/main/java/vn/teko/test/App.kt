package vn.teko.test

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.inflationx.viewpump.ViewPump
import vn.teko.test.di.component.DaggerAppComponent
import javax.inject.Inject

class App : DaggerApplication() {
    @Inject
    lateinit var viewPump: ViewPump

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        ViewPump.init(viewPump)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}