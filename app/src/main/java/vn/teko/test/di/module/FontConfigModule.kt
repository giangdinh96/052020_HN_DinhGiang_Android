package vn.teko.test.di.module

import dagger.Module
import dagger.Provides
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import vn.teko.test.FONT_DEFAULT_PATH
import vn.teko.test.R
import vn.teko.test.di.FontPathDefault
import javax.inject.Singleton

@Module
object FontConfigModule {
    @JvmStatic
    @FontPathDefault
    @Singleton
    @Provides
    fun provideFontPathDefault(): String {
        return FONT_DEFAULT_PATH
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCalligraphyInterceptor(@FontPathDefault fontPathDefault: String): CalligraphyInterceptor {
        return CalligraphyInterceptor(
            CalligraphyConfig.Builder()
                .setDefaultFontPath(fontPathDefault)
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideViewPump(calligraphyInterceptor: CalligraphyInterceptor): ViewPump {
        return ViewPump.builder().addInterceptor(calligraphyInterceptor).build()
    }
}