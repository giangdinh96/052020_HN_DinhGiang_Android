package vn.teko.test.presentation.ui.productdetail

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import vn.teko.test.R
import vn.teko.test.extension.loadImage

class BannerAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_banner) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.getView<ImageView>(R.id.image_iv).loadImage(item)
    }
}