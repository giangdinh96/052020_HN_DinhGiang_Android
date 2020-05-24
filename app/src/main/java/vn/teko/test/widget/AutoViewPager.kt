package vn.teko.test.widget

import android.os.Handler
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

class AutoViewPager : OnPageChangeCallback(), OnPageChangeListener {
    var viewPager2: ViewPager2? = null
    var viewPager: ViewPager? = null
    var duration: Long
    private val autoHandler: Handler
    private val autoRunnable: Runnable

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        if (state == ViewPager2.SCROLL_STATE_IDLE) {
            stopAuto()
            startAuto()
        } else if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
            stopAuto()
        }
    }

    private fun nextPage() {
        viewPager?.apply {
            if (childCount == 0) return
            val lastPosition = childCount - 1
            val nextPosition = if (currentItem < lastPosition) currentItem + 1 else 0
            setCurrentItem(nextPosition, true)
        }
        viewPager2?.apply {
            val itemCount = adapter?.itemCount ?: 0
            if (itemCount == 0) return
            val lastPosition = itemCount - 1
            val nextPosition = if (currentItem < lastPosition) currentItem + 1 else 0
            setCurrentItem(nextPosition, true)
        }
    }

    private fun initViewPagerListener() {
        removeViewPagerListener()
        viewPager?.addOnPageChangeListener(this)
        viewPager2?.registerOnPageChangeCallback(this)
    }

    private fun removeViewPagerListener() {
        viewPager?.removeOnPageChangeListener(this)
        viewPager2?.unregisterOnPageChangeCallback(this)
    }

    private fun startAuto() {
        autoHandler.postDelayed(autoRunnable, duration)
    }

    private fun stopAuto() {
        autoHandler.removeCallbacks(autoRunnable)
    }

    fun start() {
        startAuto()
        initViewPagerListener()
    }

    fun stop() {
        stopAuto()
        removeViewPagerListener()
    }

    companion object {
        private const val DURATION_DEFAULT: Long = 3000
    }

    init {
        duration = DURATION_DEFAULT
        autoHandler = Handler()
        autoRunnable = Runnable { nextPage() }
    }
}