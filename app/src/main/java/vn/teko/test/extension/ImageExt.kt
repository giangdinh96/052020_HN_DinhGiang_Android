package vn.teko.test.extension

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import vn.teko.test.R

const val DEFAULT_PLACE_HOLDER = R.drawable.image_placeholder

fun ImageView.loadImage(
    url: String? = null,
    resId: Int = DEFAULT_PLACE_HOLDER,
    uri: Uri? = null,
    placeHolderId: Int = DEFAULT_PLACE_HOLDER,
    errorId: Int = DEFAULT_PLACE_HOLDER
) {
    builder(
        url, resId, uri,
        placeHolderId, errorId
    ).into(this)
}

fun ImageView.loadImageCircle(
    url: String? = null,
    resId: Int = DEFAULT_PLACE_HOLDER,
    uri: Uri? = null
) {
//  val factory =
//    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
    builder(url, resId, uri)
        .apply {
//      transition(withCrossFade(factory))
            transform(CenterCrop(), CircleCrop())
            placeholder(null)
        }.into(this)
}

fun ImageView.loadImageRound(
    roundPx: Int,
    url: String? = null,
    resId: Int = DEFAULT_PLACE_HOLDER,
    uri: Uri? = null
) {
    builder(url, resId, uri)
        .apply {
            transform(CenterCrop(), RoundedCorners(roundPx))
            placeholder(null)
        }.into(this)
}

fun ImageView.builder(
    url: String? = null,
    resId: Int = DEFAULT_PLACE_HOLDER,
    uri: Uri? = null,
    placeHolderId: Int = DEFAULT_PLACE_HOLDER,
    errorId: Int = DEFAULT_PLACE_HOLDER
): RequestBuilder<Drawable> {
    val requestManager = Glide.with(this.context)
    val requestBuilder: RequestBuilder<Drawable> = when {
        url != null -> requestManager.load(url)
        uri != null -> requestManager.load(uri)
        else -> requestManager.load(resId)
    }
    requestBuilder.apply {
        placeholder(placeHolderId)
        error(errorId)
        transition(DrawableTransitionOptions.withCrossFade())
    }
    return requestBuilder
}

fun ImageView.pauseLoad() {
    Glide.with(this).pauseRequests()
}

fun ImageView.resumeLoad() {
    Glide.with(this).resumeRequests()
}