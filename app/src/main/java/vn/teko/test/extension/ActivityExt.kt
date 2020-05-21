package vn.teko.test.extension

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import vn.teko.test.R
import vn.teko.test.base.BaseActivity
import vn.teko.test.base.BaseFragment

fun Activity.getContext(): Context = this

fun Activity.getBaseActivity(): BaseActivity = this as BaseActivity

fun Activity.showKeyboard(view: View?) {
    view?.showKeyboard()
}

fun Activity.hideKeyboard() {
    val view = currentFocus ?: findViewById(R.id.content)
    view?.hideKeyboard()
}

fun BaseActivity.addRootFragment(fragment: BaseFragment<*>) {
    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    supportFragmentManager.addFragment(
        fragment = fragment,
        containerId = getContainerId(),
        isAddToBackStack = false,
        isReplace = true,
        isWithAnim = false
    )
}

fun BaseActivity.addFragment(fragment: BaseFragment<*>) {
    supportFragmentManager.addFragment(fragment = fragment, containerId = getContainerId())
}

fun BaseActivity.replaceFragment(fragment: BaseFragment<*>) {
    supportFragmentManager.addFragment(
        fragment = fragment,
        containerId = getContainerId(),
        isReplace = true
    )
}