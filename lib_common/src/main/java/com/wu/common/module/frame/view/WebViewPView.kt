package com.wu.common.module.frame.view

import android.text.Html
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.R
import com.wu.common.module.ui.WebViewPActivity
import com.wu.common.utils.StatusBarUtil
import com.wu.common.utils.WebViewUtil


/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/21
 *
 * 用途:
 */

class WebViewPView : MvpView {

    var mActivity: WebViewPActivity

    constructor(mActivity: WebViewPActivity) {
        this.mActivity = mActivity
    }

    fun initView() {

        initToolBar(mActivity.title)
        if (mActivity.type.equals("ys")){
            WebViewUtil.setPtoHtmlData(mActivity,mActivity.resources.getString(R.string.privacy_policy),15,mActivity.binding.webRoot);
        }else{
            WebViewUtil.setPtoHtmlData(mActivity,mActivity.resources.getString(R.string.user_agreement),15,mActivity.binding.webRoot);
        }
    }

    fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar

        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }
    }


}


