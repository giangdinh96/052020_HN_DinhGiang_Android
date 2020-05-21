package vn.teko.test

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import vn.teko.test.di.component.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}