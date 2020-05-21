package vn.teko.test.utils

import android.R
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

object DimensionUtils {

    @JvmStatic
    fun dpToPx(context: Context, dp: Float): Int {
        return (dp * (context.resources
            .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    @JvmStatic
    fun pxToDp(context: Context?, px: Float): Float {
        return if (context == null || context.resources == null) 0F
        else px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    @JvmStatic
    fun getWidthScreenDp(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels / displayMetrics.density
    }

    @JvmStatic
    fun getHeightScreenDp(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels / displayMetrics.density
    }

    @JvmStatic
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId =
            context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    @JvmStatic
    fun getActionBarHeight(context: Context): Int {
        val tv = TypedValue()
        return if (context.theme.resolveAttribute(R.attr.actionBarSize, tv, true))
            TypedValue.complexToDimensionPixelSize(
                tv.data, context.resources.displayMetrics
            )
        else 0
    }
}