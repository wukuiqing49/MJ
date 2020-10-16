package com.wu.mjvv.module.home.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wkq.lib_base.adapter.KtDataBindingViewHolder
import com.wu.mjvv.databinding.ItemInfomationListBinding
import com.wu.mjvv.module.home.frame.model.InfomationInfo

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/11
 *
 * 用途:
 */


class InfomationInfoListAdapter(context: Context, layoutId: Int) : KtDataBindingAdapter<InfomationInfo>(context, layoutId) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var bindingHolder = holder as KtDataBindingViewHolder
        var binding = bindingHolder.viewBinding as ItemInfomationListBinding
        binding.data = getItem(position)
        if (TextUtils.isEmpty(getItem(position)?.icon)) {
            binding.ivCion.visibility = View.GONE

        } else {
            Glide.with(mContext).load(getItem(position)?.icon).into(binding.ivCion)
            binding.ivCion.visibility = View.VISIBLE
        }

        if (viewClickListener != null) {
            binding.root.setOnClickListener { viewClickListener?.onViewClick(binding.root, getItem(position)) }
        }
    }
}