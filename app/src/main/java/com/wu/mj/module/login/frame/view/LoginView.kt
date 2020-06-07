package com.wu.mj.module.login.frame.view

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.database.utils.DataBaseUtils
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.login.ui.activity.LoginActivity
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


class LoginView : MvpView, View.OnClickListener {

    var mActivity: LoginActivity? = null

    constructor(mActivity: LoginActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        initToolBar("登录")

        mActivity!!.binding.tvRegist.setOnClickListener(this)
        mActivity!!.binding.btLogin.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_regist -> {
                RegistActivity.newInstance(mActivity as Context)
            }
            R.id.bt_login -> {
                login()
            }
        }
    }

    private fun login() {
        if (TextUtils.isEmpty(mActivity!!.binding.etPhone.text.toString())) {
            showMessage("请输入手机号")
            return
        }
        if (TextUtils.isEmpty(mActivity!!.binding.etPwd.text.toString())) {
            showMessage("请输入密码")
            return
        }

        if (!DataBaseUtils.isExistUser(mActivity, mActivity!!.binding.etPhone.text.toString())) {
            showMessage("账户不存在")
            return
        }

        if (DataBaseUtils.checkUser(mActivity, mActivity!!.binding.etPhone.text.toString(), mActivity!!.binding.etPwd.text.toString())) {
            mActivity!!.presenter.startTimer()
        } else {
            showMessage("密码错误")
        }


    }

    fun showLoaiding(show:Boolean){
        if(show){
            mActivity!!.binding.rlLoading.visibility=View.VISIBLE
        }else{
            mActivity!!.binding.rlLoading.visibility=View.GONE
        }

    }

    fun startMain() {
        var intent: Intent = Intent();
        intent.setClass(mActivity as Context, MainActivity().javaClass)
        mActivity!!.startActivity(intent)
    }

    fun finishTimer() {
        startMain()
    }

}