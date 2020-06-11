package com.wu.mj.module.home.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wkq.lib_base.adapter.KtDataBindingViewHolder
import com.wu.mj.R
import com.wu.mj.databinding.ItemAnwersBinding
import com.wu.mj.databinding.ItemCharpterBinding
import com.wu.mj.databinding.ItemResultBinding
import com.wu.mj.module.home.frame.model.AnwserInfo
import com.wu.mj.module.home.frame.model.ChapterInfo
import com.wu.mj.module.home.frame.model.QuestionInfo

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class ResultAnwserAdapter(context: Context, layoutId: Int) : KtDataBindingAdapter<QuestionInfo>(context, layoutId) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var bindingHolder = holder as KtDataBindingViewHolder
        var binding = bindingHolder.viewBinding as ItemResultBinding
        binding.data = getItem(position)

        if (viewClickListener != null) {
            binding.rlRoot.setOnClickListener {
                viewClickListener?.onViewClick(binding.root, getItem(position))
            }

        }
    }

}