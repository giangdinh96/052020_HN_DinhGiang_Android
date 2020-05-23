package vn.teko.test.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.math.roundToLong

object DecimalFormatUtils {
    private val NUMBER_DECIMAL_FORMAT =
        DecimalFormat(",##0")

    init {
        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = '.'
        NUMBER_DECIMAL_FORMAT.decimalFormatSymbols = decimalFormatSymbols
    }

    @JvmStatic
    fun formatPrice(number: Double): String {
        return formatNumber(if (number < 0) 0.0 else number)
    }

    @JvmStatic
    fun getPriceFromPriceFormatted(price: String): Double {
        var priceFormatted = price
        val result: Double
        if (priceFormatted.contains("$")) {
            val indexDollar = priceFormatted.indexOf("$")
            priceFormatted =
                priceFormatted.substring(0, indexDollar) + priceFormatted.substring(indexDollar + 1)
        }
        priceFormatted = priceFormatted.replace("-".toRegex(), "")
        priceFormatted = priceFormatted.replace(" ".toRegex(), "")
        priceFormatted = priceFormatted.replace(
            NUMBER_DECIMAL_FORMAT.decimalFormatSymbols.groupingSeparator
                .toString().toRegex(), ""
        )
        result = try {
            priceFormatted.toDouble()
        } catch (e: Exception) {
            0.0
        }
        return result
    }

    @JvmStatic
    fun formatNumber(number: Double): String {
        return formatNumber(NUMBER_DECIMAL_FORMAT, roundNumber(number))
    }

    @JvmStatic
    fun formatNumber(decimalFormat: DecimalFormat, number: Double): String {
        return decimalFormat.format(number)
    }

    @JvmStatic
    fun roundNumber(number: Float): Double {
        return (number * 100).roundToLong() / 100.0
    }

    @JvmStatic
    fun roundNumber(number: Double): Double {
        return (number * 100).roundToLong() / 100.0
    }
}