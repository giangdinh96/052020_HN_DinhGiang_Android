package vn.teko.test.base

import android.content.Context
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import vn.teko.test.BASE_ACTIVITY_LAYOUT_ID
import vn.teko.test.FRAGMENT_CONTAINER_ID
import vn.teko.test.extension.addRootFragment

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        if (savedInstanceState == null) {
            addRootFragment(getRootFragment())
        }

        initLayout()

    }

    abstract fun getRootFragment(): BaseFragment<*>

    fun getLayoutId() = BASE_ACTIVITY_LAYOUT_ID

    fun getContainerId() = FRAGMENT_CONTAINER_ID

    abstract fun initLayout()
}