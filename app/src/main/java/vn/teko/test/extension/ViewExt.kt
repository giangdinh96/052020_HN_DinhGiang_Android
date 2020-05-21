package vn.teko.test.extension

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.util.*

fun View.setOnTouchHideKeyboard(viewExcludeList: List<View>?) {
    // Set up touch listener for non-text box views to hide keyboard.
    if (this !is EditText && viewExcludeList?.contains(this) != true) {
        setOnTouchListener { _: View, _: MotionEvent? ->
            hideKeyboard()
            val activity: Activity = context as Activity
            if (activity.currentFocus != null) activity.currentFocus!!.clearFocus()
            false
        }
    }

    //If a layout container, iterate over children and seed recursion.
    if (this is ViewGroup) {
        for (i in 0 until childCount) {
            getChildAt(i).setOnTouchHideKeyboard(viewExcludeList)
        }
    }
}

fun View.delayViewPress(duration: Long = 100) {
    isClickable = false
    isFocusable = false
    Handler().postDelayed({
        isClickable = true
        isFocusable = true
    }, duration)
}

fun View.focusView() {
    isFocusable = true
    isFocusableInTouchMode = true
    requestFocus()
    isFocusable = false
    isFocusableInTouchMode = false
    hideKeyboard()
}

fun View.showKeyboard() {
    post {
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        Objects.requireNonNull(imm).showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    Objects.requireNonNull(imm).hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.expand(animationListener: Animation.AnimationListener? = null) {
    measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val targetHeight = measuredHeight

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    layoutParams.height = 1
    visibility = View.VISIBLE
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            layoutParams.height =
                if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }
    a.setAnimationListener(animationListener)
    // 1dp/ms
    a.duration = (targetHeight / context.resources.displayMetrics.density).toLong()
    startAnimation(a)
}

fun View.collapse() {
    val initialHeight = measuredHeight
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                visibility = View.GONE
            } else {
                layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // 1dp/ms
    a.duration = (initialHeight / context.resources.displayMetrics.density).toLong()
    startAnimation(a)
}