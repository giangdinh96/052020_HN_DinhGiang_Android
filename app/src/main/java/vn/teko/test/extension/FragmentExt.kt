package vn.teko.test.extension

import android.view.View
import androidx.fragment.app.Fragment
import vn.teko.test.base.BaseActivity
import vn.teko.test.base.BaseFragment


fun Fragment.getBaseActivity(): BaseActivity = requireActivity().getBaseActivity()

fun Fragment.getBaseFragment(): BaseFragment<*> = this as BaseFragment<*>

fun Fragment.showKeyboard(view: View?) {
    view?.showKeyboard()
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun BaseFragment<*>.addFragment(fragment: BaseFragment<*>) {
    getBaseActivity().addFragment(fragment)
}

fun BaseFragment<*>.replaceFragment(fragment: BaseFragment<*>) {
    getBaseActivity().replaceFragment(fragment)
}

fun BaseFragment<*>.addChildFragment(fragment: BaseFragment<*>, containerId: Int) {
    childFragmentManager.addFragment(fragment = fragment, containerId = containerId)
}

fun BaseFragment<*>.replaceChildFragment(fragment: BaseFragment<*>, containerId: Int) {
    childFragmentManager.addFragment(
        fragment = fragment,
        containerId = containerId,
        isReplace = true
    )
}