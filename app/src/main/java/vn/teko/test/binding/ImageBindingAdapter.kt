package vn.teko.test.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import vn.teko.test.extension.loadImage
import vn.teko.test.extension.loadImageCircle
import vn.teko.test.extension.loadImageRound

@BindingAdapter("android:url", "android:circle", "android:corner", requireAll = false)
fun setImage(imageView: ImageView, url: String, circle: Boolean, corner: Float) {
    if (circle) {
        imageView.loadImageCircle(url)
    } else {
        if (corner > 0) {
            imageView.loadImageRound(corner.toInt(), url)
        } else {
            imageView.loadImage(url)
        }
    }
}