package com.wu.mj.module.main.frame.view

import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.main.ui.activity.MainActivity
import com.wu.mj.module.main.ui.adapter.HomeFragmentPagerAdapter
import com.wu.mj.module.main.ui.widget.QMUITabSegment

/**
 * 作者: 吴奎庆
 *
 * 时间: 2020/5/8
 *
 * 简介:
 */

class MainView : MvpView {

    var mActivity: MainActivity

    constructor(mActivity: MainActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        StatusBarUtil.setTransparentForWindow(mActivity)
        StatusBarUtil.addTranslucentView(mActivity, 0)

        var mainAdapter = HomeFragmentPagerAdapter(mActivity, mActivity.supportFragmentManager)

        val component: QMUITabSegment.Tab = QMUITabSegment.Tab(
            ContextCompat.getDrawable(mActivity, R.drawable.ic_index_home_gray),
            ContextCompat.getDrawable(mActivity, R.drawable.ic_index_home),
            "首页", false
        )

        val util: QMUITabSegment.Tab = QMUITabSegment.Tab(
            ContextCompat.getDrawable(mActivity, R.drawable.ic_index_dashboard_gray),
            ContextCompat.getDrawable(mActivity, R.drawable.ic_index_dashboard),
            "动态", false
        )
        val novel: QMUITabSegment.Tab = QMUITabSegment.Tab(
            ContextCompat.getDrawable(mActivity, R.drawable.ic_index_notifications_gray),
            ContextCompat.getDrawable(mActivity, R.drawable.ic_index_notifications),
            "我的", false
        )

        mActivity.binding.tabs.addTab(component)
            .addTab(util)
            .addTab(novel)
//            .addTab(lab)

        mActivity.binding.tabs.setDefaultNormalColor(mActivity.resources.getColor(R.color.color_n))
        mActivity.binding.tabs.setDefaultSelectedColor(mActivity.resources.getColor(R.color.color_s))
        mActivity.binding.pager.adapter = mainAdapter
        mActivity.binding.pager.offscreenPageLimit = 4
        mActivity.binding.tabs.setupWithViewPager(mActivity.binding.pager, false)


    }

    fun showMessage(message: String?) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message)

    }

}