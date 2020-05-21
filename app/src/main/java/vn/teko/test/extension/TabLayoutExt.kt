package vn.teko.test.extension

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import vn.teko.test.utils.FontUtils

fun TabLayout.updateFont(
    context: Context,
    fontNormal: String?,
    fontSelected: String?,
    selectedIndex: Int
) {
    if (tabCount < 1) {
        return
    }
    val childTab = getChildAt(0) as ViewGroup
    for (i in 0 until tabCount) {
        val containerTabContent = childTab.getChildAt(i) as ViewGroup
        val textView = containerTabContent.getChildAt(1) as TextView
        val typefaceNormal = FontUtils.get(context, fontNormal!!)
        textView.typeface = typefaceNormal
    }
    val containerTabContent = childTab.getChildAt(selectedIndex) as ViewGroup
    val textView = containerTabContent.getChildAt(1) as TextView
    val typefaceBold = FontUtils.get(context, fontSelected!!)
    textView.typeface = typefaceBold
}