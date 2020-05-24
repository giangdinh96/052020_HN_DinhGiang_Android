package vn.teko.test.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import vn.teko.test.R
import vn.teko.test.extension.loadImage
import vn.teko.test.extension.toLastLetterSuperscriptSpanString
import vn.teko.test.extension.toStrikethroughSpanString
import vn.teko.test.presentation.model.ProductItem

class ProductItemAdapter(layout: Int = R.layout.item_product) :
    BaseQuickAdapter<ProductItem, BaseViewHolder>(layout), LoadMoreModule {

    init {
        setDiffCallback(ProductItemDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: ProductItem) {
        val priceTv = holder.getView<TextView>(R.id.price_tv)
        val originPriceTv = holder.getView<TextView>(R.id.origin_price_tv)
        val discountPercentDlv = holder.getView<TextView>(R.id.discount_percent_dlv)
        holder.setText(R.id.name_tv, item.name)

        if (item.finalPrice.isEmpty()) {
            priceTv.visibility = View.GONE
        } else {
            priceTv.visibility = View.VISIBLE
            priceTv.text = item.finalPrice.toLastLetterSuperscriptSpanString()
        }

        if (item.finalPrice.isEmpty()) {
            originPriceTv.visibility = View.GONE
        } else {
            originPriceTv.visibility = View.VISIBLE
            originPriceTv.text = item.originPrice.toStrikethroughSpanString()
        }
        if (item.discountPercent.isEmpty()) {
            discountPercentDlv.visibility = View.GONE
        } else {
            discountPercentDlv.visibility = View.VISIBLE
            discountPercentDlv.text = item.discountPercent
        }

        holder.getView<ImageView>(R.id.image_iv).loadImage(url = item.image)
    }
}

class ProductItemDiffCallback : DiffUtil.ItemCallback<ProductItem>() {
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem == newItem
    }
}