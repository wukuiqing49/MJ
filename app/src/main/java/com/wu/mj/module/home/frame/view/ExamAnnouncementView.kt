package com.wu.mj.module.home.frame.view

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wu.common.base.adapter.DataBindingAdapter
import com.wu.common.module.ui.WebViewActivity
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.AnnouncementInfo
import com.wu.mj.module.home.ui.adapter.ExamAnnouncementAdapter
import com.wu.mj.module.home.ui.fragment.ExamAnnouncementFragment

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/15
 *
 * 用途:
 */


class ExamAnnouncementView : MvpView {

    var mFragment: ExamAnnouncementFragment
    var mAdapter: ExamAnnouncementAdapter? = null

    constructor(mFragment: ExamAnnouncementFragment) {
        this.mFragment = mFragment
    }

    fun initView() {
        initToolBar("考试公告")
        mFragment.binding.rvContent.layoutManager = LinearLayoutManager(mFragment.activity)
        mAdapter = ExamAnnouncementAdapter(mFragment.activity as Context, R.layout.item_exam_annnouncement)
        mFragment.binding.rvContent.adapter = mAdapter

        mAdapter?.setOnViewClickListener(object : KtDataBindingAdapter.OnAdapterViewClickListener<AnnouncementInfo> {
            override fun onViewClick(v: View?, program: AnnouncementInfo?) {

                WebViewActivity.newInstance(mFragment.activity as Context, program?.href, program?.title)

            }


        })
    }

    private fun initToolBar(title: String?) {
        mFragment.binding.tvTitle.setText(title)
    }

    fun showView(lists: List<AnnouncementInfo>) {

        mAdapter?.addItems(lists.toMutableList())
    }

}