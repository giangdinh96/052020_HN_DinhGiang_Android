package vn.teko.test.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import vn.teko.test.R
import vn.teko.test.databinding.ItemProductAttributeDataBinding
import vn.teko.test.presentation.model.ProductAttributeData
import kotlin.math.min

class ProductAttributeAdapter(var dataList: MutableList<ProductAttributeData> = ArrayList()) :
    BaseDataBindingAdapter<ItemProductAttributeDataBinding, BaseDataBindingViewHolder<ItemProductAttributeDataBinding>>() {
    var expandMode: Boolean = true
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseDataBindingViewHolder<ItemProductAttributeDataBinding> {
        val dataBinding = ItemProductAttributeDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BaseDataBindingViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        if (expandMode) {
            return dataList.size
        }
        return min(dataList.size, 4)
    }

    override fun onBindViewHolder(
        holderDataBinding: BaseDataBindingViewHolder<ItemProductAttributeDataBinding>,
        position: Int
    ) {
        holderDataBinding.bind(dataList[position])
        val context = holderDataBinding.binding.root.context
        when {
            position == 0 && dataList.size > 1 -> {
                holderDataBinding.binding.root.background =
                    context.getDrawable(R.drawable.bg_product_attribute_item_top_corner)
            }
            position % 2 == 1 -> {
                holderDataBinding.binding.root.setBackgroundColor(Color.WHITE)
            }
            else -> {
                holderDataBinding.binding.root.setBackgroundColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.colorPaleGrey,
                        null
                    )
                )
            }
        }
    }
}