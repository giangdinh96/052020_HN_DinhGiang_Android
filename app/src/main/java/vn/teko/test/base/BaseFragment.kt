package vn.teko.test.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment
import vn.teko.test.extension.setOnTouchHideKeyboard

abstract class BaseFragment<V : ViewDataBinding> : DaggerFragment() {
    lateinit var binding: V
    private var toast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        init()
        val rootView = binding.root
        rootView.setOnTouchHideKeyboard(null)
        rootView.isClickable = true
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    abstract fun getLayoutId(): Int

    abstract fun init()

    abstract fun observeData()
}