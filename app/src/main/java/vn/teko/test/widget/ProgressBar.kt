package vn.teko.test.widget

import android.animation.AnimatorInflater
import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import vn.teko.test.R

class ProgressBar : ProgressBar {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        val animator = AnimatorInflater.loadAnimator(context, R.animator.anim_progress)
        animator.setTarget(this)
        animator.start()
    }
}