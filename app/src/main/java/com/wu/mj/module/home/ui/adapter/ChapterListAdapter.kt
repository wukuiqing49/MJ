package com.wu.mj.module.home.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wkq.lib_base.adapter.KtDataBindingViewHolder
import com.wu.mj.databinding.ItemCharpterBinding
import com.wu.mj.module.home.frame.model.ChapterInfo

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class ChapterListAdapter(context: Context, layoutId: Int) : KtDataBindingAdapter<ChapterInfo>(context, layoutId) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var bindingHolder = holder as KtDataBindingViewHolder
        var binding = bindingHolder.viewBinding as ItemCharpterBinding
        binding.data = getItem(position)

        if (viewClickListener!=null){
            binding.root.setOnClickListener { viewClickListener?.onViewClick(binding.root,getItem(position)) }
        }

    }

}