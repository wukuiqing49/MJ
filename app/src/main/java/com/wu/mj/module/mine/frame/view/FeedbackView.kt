package com.wu.mj.module.mine.frame.view

import android.text.TextUtils
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.mine.ui.activity.FeedbackActivity

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/14
 *
 * 用途:
 */


class FeedbackView : MvpView {
    var mActivity: FeedbackActivity

    constructor(mActivity: FeedbackActivity) {
        this.mActivity = mActivity

    }

    fun initView() {
        initToolBar("意见反馈")
    }

    private fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar
        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }
        var right = toolbar.addRightTextButton("提交", R.id.done);
        right.setTextColor(mActivity.resources.getColor(R.color.color_white))
        right.setOnClickListener {
            if (TextUtils.isEmpty(mActivity.binding.etName.text)) {
                showMessage("请输入要反馈的内容")
            } else if ( TextUtils.isEmpty(mActivity.binding.etPhone.text)) {
                showMessage("请输入要联系方式")

            } else {
                mActivity.presenter.startTimer()
            }

        }
    }

    fun finishTimer() {
        showMessage("已反馈!!!")
        mActivity.finish()

    }

    fun showMessage(message: String?) {
        if (mActivity == null || TextUtils.isEmpty(message)) return
        AlertUtil.showDeftToast(mActivity, message)
    }
}