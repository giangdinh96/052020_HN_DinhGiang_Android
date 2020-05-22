package vn.teko.test.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import vn.teko.test.R
import vn.teko.test.utils.DimensionUtils

class DiscountLabelView : androidx.appcompat.widget.AppCompatTextView {

    private val path = Path()
    private val paint = Paint()

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val input =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.DiscountLabelView, 0, 0
            )

        val labelBackgroundColor = input.getColor(
            R.styleable.DiscountLabelView_labelBackgroundColor,
            ResourcesCompat.getColor(
                resources,
                R.color.colorTomato, null
            )
        )
        paint.color = labelBackgroundColor
        paint.style = Paint.Style.FILL

        input.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        val spaceSize = DimensionUtils.dpToPx(context, 4f).toFloat()
        path.moveTo(0f, height / 2f)
        path.lineTo(spaceSize, height.toFloat())
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(width.toFloat(), 0f)
        path.lineTo(spaceSize, 0f)
        path.lineTo(0f, height / 2f)
        canvas?.drawPath(path, paint)
        super.onDraw(canvas)
    }
}