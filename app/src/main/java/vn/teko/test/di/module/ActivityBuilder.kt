package vn.teko.test.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vn.teko.test.ui.MainActivity

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}