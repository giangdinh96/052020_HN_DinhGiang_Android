package vn.teko.test.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import vn.teko.test.R
import vn.teko.test.extension.setup
import vn.teko.test.presentation.adapter.ProductAttributeAdapter
import vn.teko.test.presentation.model.ProductAttributeData

class ProductInfoDetailView : LinearLayout {
    lateinit var tabTl: TabLayout
    lateinit var containerFl: FrameLayout
    private val containerProductDescription: View by lazy<View> {
        LayoutInflater.from(context)
            .inflate(R.layout.item_product_description, containerFl, false)
    }
    private val containerProductData: View by lazy<View> {
        LayoutInflater.from(context)
            .inflate(R.layout.item_product_data, containerFl, false)
    }
    private val containerProductCompare: View by lazy<View> {
        LayoutInflater.from(context)
            .inflate(R.layout.item_product_compare_price, containerFl, false)
    }

    private val descriptionTv: TextView by lazy<TextView> {
        containerProductDescription.findViewById(R.id.description_tv)
    }

    private val dataRv: RecyclerView by lazy<RecyclerView> {
        containerProductData.findViewById(R.id.data_rv)
    }
    private val holderV: View by lazy<View> {
        findViewById(R.id.holder_v)
    }
    private val loadActionTv: TextView by lazy<TextView> {
        findViewById(R.id.load_action_tv)
    }

    private var productAttributeAdapter = ProductAttributeAdapter()

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
        LayoutInflater.from(context).inflate(R.layout.view_product_info_detail, this, true)

        tabTl = findViewById(R.id.tab_tl)
        containerFl = findViewById(R.id.container_fl)
        dataRv.setup()
        dataRv.isNestedScrollingEnabled = true
        dataRv.adapter = productAttributeAdapter

        tabTl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                containerFl.removeAllViews()
                hideLoadAction()
                when (tab?.position) {
                    0 -> showTabDescription()
                    1 -> showTabData()
                    2 -> showTabCompare()
                }
            }
        })
        tabTl.getTabAt(1)?.select()

        loadActionTv.setOnClickListener {
            when (tabTl.selectedTabPosition) {
                1 -> {
                    if (productAttributeAdapter.expandMode) {
                        productAttributeAdapter.expandMode = false
                        switchToCollapse()
                    } else {
                        productAttributeAdapter.expandMode = true
                        switchToExpand()
                    }
                }
            }
        }
    }

    private fun showTabDescription() {
        containerFl.addView(containerProductDescription)
    }

    private fun showTabData() {
        containerFl.addView(containerProductData)
        productAttributeAdapter.expandMode = false
        if (productAttributeAdapter.dataList.size > 4) {
            showLoadAction()
            switchToCollapse()
        }
    }

    private fun showTabCompare() {
        containerFl.addView(containerProductCompare)
    }

    private fun switchToExpand() {
        holderV.visibility = View.GONE
        showLoadAction()
        loadActionTv.text = context.getString(R.string.show_less)
    }

    private fun switchToCollapse() {
        holderV.visibility = View.VISIBLE
        showLoadAction()
        loadActionTv.text = context.getString(R.string.show_more)
    }

    private fun showLoadAction() {
        loadActionTv.visibility = View.VISIBLE
    }

    private fun hideLoadAction() {
        loadActionTv.visibility = View.GONE
        holderV.visibility = View.GONE
    }

    fun setData(
        description: String = context.getString(R.string.no_info),
        productData: List<ProductAttributeData> = ArrayList()
    ) {
        descriptionTv.text = description
        productAttributeAdapter.dataList.clear()
        productAttributeAdapter.dataList.addAll(productData)
    }
}