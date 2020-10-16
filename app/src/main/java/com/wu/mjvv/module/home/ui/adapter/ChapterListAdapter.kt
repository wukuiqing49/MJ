package com.wu.mjvv.module.home.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wkq.lib_base.adapter.KtDataBindingViewHolder
import com.wu.mjvv.databinding.ItemCharpterBinding
import com.wu.mjvv.module.home.frame.model.ChapterInfo

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
        if (getItem(position)?.progress!=null){
            binding.nbP.progress=getItem(position)?.progress!!.nowProgress!!.toInt()
            binding.nbP.max=getItem(position)?.progress!!.totalProgress!!.toInt()
        }

        if (viewClickListener!=null){
            binding.root.setOnClickListener { viewClickListener?.onViewClick(binding.root,getItem(position)) }
        }

    }

}