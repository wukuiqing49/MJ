package com.wu.mjvv.module.home.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wkq.lib_base.adapter.KtDataBindingViewHolder
import com.wu.mjvv.R
import com.wu.mjvv.databinding.ItemAnwersBinding
import com.wu.mjvv.databinding.ItemCharpterBinding
import com.wu.mjvv.module.home.frame.model.AnwserInfo

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionAnwserAdapter(context: Context, layoutId: Int) : KtDataBindingAdapter<AnwserInfo>(context, layoutId) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var bindingHolder = holder as KtDataBindingViewHolder
        var binding = bindingHolder.viewBinding as ItemAnwersBinding
        binding.data = getItem(position)

        if (getItem(position)!!.getIsCheck() == true) {
            binding.tvCheck.setTextColor(mContext.resources.getColor(R.color.color_white))
        } else {
            binding.tvCheck.setTextColor(mContext.resources.getColor(R.color.color_333))
        }

        if (viewClickListener != null) {
            binding.root.setOnClickListener {
                if (getItem(position)!!.getIsCheck() == true) {
                    getItem(position)!!.setIsCheck(false)
//                    binding.tvCheck.setTextColor(mContext.resources.getColor(R.color.color_333))
                    binding.tvCheck.setBackgroundResource(R.drawable.shape_anwser_abc_bg)
                } else {
                    getItem(position)!!.setIsCheck(true)
//                    binding.tvCheck.setTextColor(mContext.resources.getColor(R.color.color_white))
                    binding.tvCheck.setBackgroundResource(R.drawable.shape_anwser_abc_true)
                }

                viewClickListener?.onViewClick(binding.root, getItem(position)
                )
            }
        }

    }

}