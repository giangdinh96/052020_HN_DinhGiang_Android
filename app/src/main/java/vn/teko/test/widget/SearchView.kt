package vn.teko.test.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import vn.teko.test.R
import vn.teko.test.extension.hideKeyboard
import vn.teko.test.utils.TextWatcherCustom

typealias TextChangeListener = (String) -> Unit
typealias OnSearchPressListener = () -> Unit

class SearchView : ConstraintLayout {


    lateinit var contentEt: EditText
    lateinit var clearIv: ImageView
    var textChangeListener: TextChangeListener? = null
    var onSearchPressListener: OnSearchPressListener? = null

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

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_search, this, true)
        contentEt = findViewById(R.id.content_et)
        clearIv = findViewById(R.id.clear_iv)
        clearIv.visibility = View.INVISIBLE

        val input =
            context.obtainStyledAttributes(attrs, R.styleable.SearchView, 0, 0)

        // Text
        val text = input.getString(R.styleable.SearchView_text)
        setText(text ?: "")

        // Hint
        val hint = input.getString(R.styleable.SearchView_hint)
        setHint(hint ?: "")

        input.recycle()

        clearIv.setOnClickListener {
            contentEt.setText("")
        }

        contentEt.addTextChangedListener(TextWatcherCustom() { text ->
            textChangeListener?.invoke(text)
            if (text.isNotEmpty()) {
                clearIv.visibility = View.VISIBLE
            } else {
                clearIv.visibility = View.INVISIBLE
            }
        })

        contentEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchPressListener?.invoke()
                hideKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    fun setHint(value: String) {
        contentEt.hint = value
    }

    fun setHint(valueResId: Int) {
        contentEt.setHint(valueResId)
    }

    fun setText(text: String) {
        contentEt.setText(text)
    }

    fun setText(textResId: Int) {
        contentEt.setText(textResId)
    }

    fun getText(): String {
        return contentEt.text.toString()
    }
}