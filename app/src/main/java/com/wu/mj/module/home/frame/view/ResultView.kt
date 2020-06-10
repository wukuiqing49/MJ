package com.wu.mj.module.home.frame.view

import androidx.recyclerview.widget.GridLayoutManager
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.QuestionInfo
import com.wu.mj.module.home.ui.activity.ResultActivity
import com.wu.mj.module.home.ui.adapter.ResultAnwserAdapter

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/10
 *
 * 用途:
 */


class ResultView : MvpView {
    var mActivity: ResultActivity
    var mAdapter:ResultAnwserAdapter?=null
    constructor(mActivity: ResultActivity) {
        this.mActivity = mActivity
    }


    fun initView() {
        initToolBar("答题结果")
        mAdapter = ResultAnwserAdapter(mActivity, R.layout.item_result)
        mActivity.binding.rvContent.layoutManager=GridLayoutManager(mActivity,10)
        mActivity.binding.rvContent.adapter = mAdapter
    }

    private fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar
        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }


    }

    /**
     * 展示结果
     */
    fun showResult(results: List<QuestionInfo>) {
        mAdapter!!.addItems(results.toMutableList())
    }
}