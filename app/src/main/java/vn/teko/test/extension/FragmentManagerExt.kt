package vn.teko.test.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import vn.teko.test.*

fun FragmentManager.getTopFragment(): Fragment? {
    val backStackEntryCount = this.backStackEntryCount
    if (backStackEntryCount > 0) {
        val lastIndex = backStackEntryCount - 1
        val backStackEntry = getBackStackEntryAt(lastIndex)
        return findFragmentByTag(backStackEntry.name)
    } else {
        if (fragments.isNotEmpty()) {
            return fragments[0]
        }
    }
    return null
}

fun FragmentManager.addFragment(
    fragment: Fragment,
    containerId: Int = FRAGMENT_CONTAINER_ID,
    tag: String = fragment::class.java.name,
    isAddToBackStack: Boolean = true,
    isWithAnim: Boolean = true,
    isReplace: Boolean = false,
    enterAnim: Int = ANIM_ENTER_RIGHT_TO_LEFT,
    exitAnim: Int = ANIM_EXIT_RIGHT_TO_LEFT,
    popEnterAnim: Int = ANIM_ENTER_LEFT_TO_RIGHT,
    popExitAnim: Int = ANIM_EXIT_LEFT_TO_RIGHT
) {
    beginTransaction().apply {
        if (isWithAnim) setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
        // BackStack
        if (isAddToBackStack) addToBackStack(tag)

        if (isReplace) {
            replace(containerId, fragment, tag)
        } else {
            add(containerId, fragment, tag)
        }
        commit()
    }
}
