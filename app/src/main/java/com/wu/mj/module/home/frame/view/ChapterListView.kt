package com.wu.mj.module.home.frame.view

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.ChapterInfo
import com.wu.mj.module.home.ui.activity.ChapterListActivity
import com.wu.mj.module.home.ui.activity.QuestionsInfoActivity
import com.wu.mj.module.home.ui.adapter.ChapterListAdapter

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class ChapterListView : MvpView {
    var mActivity: ChapterListActivity
    var mAdapter: ChapterListAdapter? = null

    constructor(mActivity: ChapterListActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        initToolBar("章节练习")
        mActivity.binding.rvContent.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = ChapterListAdapter(mActivity, R.layout.item_charpter)
        mActivity.binding.rvContent.adapter = mAdapter

        mAdapter!!.setOnViewClickListener(object : KtDataBindingAdapter.OnAdapterViewClickListener<ChapterInfo> {
            override fun onViewClick(v: View?, program: ChapterInfo?) {
                QuestionsInfoActivity.newInstance(mActivity, program?.title, program?.index)
            }

        })
    }

    private fun initToolBar(title: String) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar
        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }


    }

    fun showData(chapterList: List<ChapterInfo>) {
        mAdapter!!.addItems(chapterList.toMutableList())
    }

}