package com.wu.mj.module.login.frame.view

import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.database.utils.DataBaseUtils
import com.wu.common.module.ui.WebViewActivity
import com.wu.common.module.ui.WebViewPActivity
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.login.ui.activity.RegistActivity
import com.wu.mj.module.main.ui.activity.MainActivity

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/7
 *
 * 用途:
 */


class RegistView : MvpView {


    var mActivity: RegistActivity? = null

    constructor(mActivity: RegistActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        initToolBar("注册")
        setAgreementText()
        mActivity!!.binding.btRegist.setOnClickListener { regist() }

    }


    fun setAgreementText() {
        val textContent = "登录即代表阅读并同意《用户协议》 及 《隐私政策》"
        val ssb = SpannableStringBuilder(textContent)
        ssb.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                WebViewPActivity.newInstance(mActivity as Context, "yh", "用户协议")
            }
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }
        }, 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                WebViewPActivity.newInstance(mActivity as Context, "ys", "隐私协议")

            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }
        }, 20, 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(ForegroundColorSpan(mActivity!!.getResources().getColor(R.color.color_23d41e)), 11, 15, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        ssb.setSpan(ForegroundColorSpan(mActivity!!.getResources().getColor(R.color.color_23d41e)), 20, 24, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        mActivity!!.binding.tvAgreement.setMovementMethod(LinkMovementMethod.getInstance())
        mActivity!!.binding.tvAgreement.setText(ssb)
    }

    fun initToolBar(title: String) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar

        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }
    }


    fun showMessage(message: String?) {
        if (TextUtils.isEmpty(message) || mActivity == null) return
        AlertUtil.showDeftToast(mActivity, message)

    }

    fun startMain() {

        var intent: Intent = Intent();
        intent.setClass(mActivity as Context, MainActivity().javaClass)
        mActivity!!.startActivity(intent)
    }

    private fun regist() {
        if (TextUtils.isEmpty(mActivity!!.binding.etPhone.text.toString())) {
            showMessage("请输入手机号")
            return
        }
        if (TextUtils.isEmpty(mActivity!!.binding.etNick.text.toString())) {
            showMessage("请输入昵称")
            return
        }
        if (TextUtils.isEmpty(mActivity!!.binding.etPwd.text.toString())) {
            showMessage("请输入密码")
            return
        }
        if (TextUtils.isEmpty(mActivity!!.binding.etOk.text.toString())) {
            showMessage("请确认密码")
            return
        }

        if (!mActivity!!.binding.etPwd.text.toString().equals(mActivity!!.binding.etOk.text.toString())) {
            showMessage("密码不一致")
            return
        }

        if (DataBaseUtils.isExistUser(mActivity, mActivity!!.binding.etPhone.text.toString())) {
            showMessage("账户已注册")
            return
        } else {

            DataBaseUtils.insertHomeTopData(mActivity as Context, mActivity!!.binding.etPhone.text.toString(),
                    mActivity!!.binding.etNick.text.toString(),
                    mActivity!!.binding.etPwd.text.toString(),
                    ""
            )

            mActivity!!.presenter.startTimer()
        }


    }

    fun showLoaiding(show: Boolean) {
        if (show) {
            mActivity!!.binding.rlLoading.visibility = View.VISIBLE
        } else {
            mActivity!!.binding.rlLoading.visibility = View.GONE
        }
    }

    fun finishTimer() {
        startMain()

    }

}