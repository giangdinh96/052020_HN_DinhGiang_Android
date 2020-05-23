package vn.teko.test.data.mapper

import vn.teko.test.data.remote.model.ProductItemResponse
import vn.teko.test.presentation.model.ProductItem
import vn.teko.test.utils.DecimalFormatUtils
import kotlin.random.Random

fun ProductItemResponse.toPresentation(): ProductItem {
    val imageFormatted = if (images?.isNotEmpty() != false) {
        images?.get(0)?.url
    } else {
        ""
    }


    val priceNumber = price?.sellPrice ?: 0.0
    val randomPercent = if (priceNumber > 0) Random.nextInt(40) else 0
    var priceFormatted = ""
    var priceOriginFormatted = ""
    if (priceNumber > 0.0) {
        priceOriginFormatted = DecimalFormatUtils.formatPrice(priceNumber)
        priceFormatted = if (randomPercent > 0) {
            DecimalFormatUtils.formatPrice(priceNumber - randomPercent / 100f * priceNumber)
        } else {
            DecimalFormatUtils.formatPrice(priceNumber)
        } + "đ"
    }

    return ProductItem(
        id,
        name,
        imageFormatted,
        priceFormatted,
        priceOriginFormatted,
        if (randomPercent == 0) "" else "$randomPercent%"
    )
}