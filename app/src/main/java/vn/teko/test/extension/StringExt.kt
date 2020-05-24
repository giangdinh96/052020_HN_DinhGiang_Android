package vn.teko.test.extension

import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.SuperscriptSpan

fun String.toStrikethroughSpanString(): Spannable {
    val result = SpannableString(this)
    if (this.isEmpty())
        return result
    val span = StrikethroughSpan()
    result.setSpan(span, 0, result.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return result
}

fun String.toLastLetterSuperscriptSpanString(): Spannable {
    val result = SpannableString(this)
    if (this.isEmpty())
        return result
    val span = SuperscriptSpan()
    val spanTextSize = RelativeSizeSpan(0.75f)
    result.setSpan(span, result.length - 1, result.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    result.setSpan(
        spanTextSize,
        result.length - 1,
        result.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return result
}