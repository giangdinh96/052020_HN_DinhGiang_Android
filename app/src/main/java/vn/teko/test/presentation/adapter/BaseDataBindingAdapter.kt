package vn.teko.test.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import vn.teko.test.BR

abstract class BaseDataBindingAdapter<V : ViewDataBinding, VH : BaseDataBindingViewHolder<V>> :
    RecyclerView.Adapter<VH>() {

}

class BaseDataBindingViewHolder<V : ViewDataBinding>(val binding: V) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(any: Any) {
        binding.setVariable(BR.item, any)
        binding.executePendingBindings()
    }
}