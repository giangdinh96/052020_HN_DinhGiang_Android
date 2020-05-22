package vn.teko.test.ui

import vn.teko.test.base.BaseActivity
import vn.teko.test.base.BaseFragment
import vn.teko.test.ui.productlist.ProductListFragment

class MainActivity : BaseActivity() {

    override fun getRootFragment(): BaseFragment<*> = ProductListFragment.newInstance()

    override fun initLayout() {
    }
}
