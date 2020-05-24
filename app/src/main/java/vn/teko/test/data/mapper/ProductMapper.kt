package vn.teko.test.data.mapper

import vn.teko.test.data.remote.model.ProductResponse
import vn.teko.test.presentation.model.ProductAttributeData
import vn.teko.test.presentation.model.ProductItem
import vn.teko.test.utils.DecimalFormatUtils
import kotlin.random.Random

fun ProductResponse.toPresentation(): ProductItem {
    // bind image
    val imagesResult = mutableListOf<String>()
    if (!images.isNullOrEmpty()) {
        for (item in images!!) {
            item.url?.let {
                imagesResult.add(it)
            }
        }
    }
    var imagePrimary = ""
    if (imagesResult.isNotEmpty()) {
        imagePrimary = imagesResult[0]
    }

    // bind price
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

    // bind status

    // bind product attribute list
    val productAttributeDataList = ArrayList<ProductAttributeData>()
    attributes?.let {
        for (attribute in it) {
            productAttributeDataList.add(ProductAttributeData(attribute.name, attribute.value))
        }
    }

    val percentFormatted = if (randomPercent == 0) "" else "$randomPercent%"
    return ProductItem(
        id,
        name,
        imagePrimary,
        imagesResult,
        priceNumber,
        priceFormatted,
        priceOriginFormatted,
        percentFormatted,
        status?.status ?: "",
        productAttributeDataList
    )
}