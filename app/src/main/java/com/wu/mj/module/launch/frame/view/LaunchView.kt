package com.wu.mj.module.launch.frame.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.database.utils.DataBaseUtils
import com.wu.common.utils.*
import com.wu.mj.R
import com.wu.mj.module.launch.ui.activity.LaunchActivity
import com.wu.mj.module.login.ui.activity.LoginActivity
import com.wu.mj.module.main.ui.activity.MainActivity
import com.wu.mj.utlis.MAsyncTask


/**
 * 作者: 吴奎庆
 *
 * 时间: 2020/5/14
 *
 * 简介:
 */
class LaunchView : MvpView {

    var mContext: LaunchActivity


    constructor(mContext: LaunchActivity) {
        this.mContext = mContext
    }

    fun checkPermissions() {
        val hasPermission: Boolean = mContext.getPresenter().checkPermissions(mContext
                , mContext.permissionsREAD,
                mContext.REQUEST_CODE_LAUNCH)
        if (hasPermission) {
//            MAsyncTask(mContext).execute()
            mContext.presenter.startTimer(3000)
        }
    }


    fun showPermissionPerpetual(requestCode: Int) {
        if (mContext.dialog != null) mContext.dialog?.dismiss()
        mContext.dialog = AlertDialogUtils.showTwoButtonDialog(
                mContext,
                "取消",
                "去设置",
                "你的手机没有授权软件权限,将无法正常使用!", R.color.color_dialog_btn,
                R.color.color_ffa300, object : AlertDialogUtils.DialogTwoListener {
            override fun onClickLeft(dialog: Dialog?) {
                dialog?.dismiss()
                showMessage("无法获取存读取权限,您的app将无法正常使用")
                mContext.finish()
            }

            override fun onClickRight(dialog: Dialog?) {
                dialog?.dismiss()
                PermissionChecker.settingPermissionActivity(mContext, requestCode)
            }
        })
    }

    fun initView() {
        StatusBarUtil.setTransparentForWindow(mContext)
        StatusBarUtil.addTranslucentView(mContext, 0)
        StatusBarUtil.setLightMode(mContext)
        processFullScreen()
        mContext.binding.tvAdTime.setOnClickListener { finishTimer() }
        DataBaseUtils.initUser(mContext as Context, "10000000001",
                "测试1",
                "123456",
                ""
        )

        DataBaseUtils.initUser(mContext as Context, "10000000003",
                "测试2",
                "123456",
                "")
    }

    /**
     *   处理刘海屏全屏问题
     */
    fun processFullScreen() {

        val lp: WindowManager.LayoutParams = mContext.getWindow().getAttributes()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        mContext.getWindow().setAttributes(lp)
        // 设置页面全屏显示
        val decorView: View = mContext.getWindow().getDecorView()
        decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    fun finishTimer() {
        if (SharedPreferencesHelper.getInstance(mContext).getLong("startTime") < 1000) {
            SharedPreferencesHelper.getInstance(mContext).setValue("startTime", System.currentTimeMillis())
        }

        var intent: Intent = Intent();
        if (DataBaseUtils.isLogin(mContext)) {
            intent.setClass(mContext, MainActivity().javaClass)
            mContext.startActivity(intent)
        } else {
            intent.setClass(mContext, LoginActivity().javaClass)
            mContext.startActivity(intent)
        }
        mContext.finish()

    }

    fun onInterval(time: Long) {
        val x = (time + 1000) / 1000 as Int
        mContext.binding.tvAdTime.setText("跳过  $x")
    }


    fun showPermission(needList: List<String>, requestCode: Int) {
        if (mContext.dialog != null) mContext!!.dialog?.dismiss()
        mContext.dialog = AlertDialogUtils.showTwoButtonDialog(
                mContext,
                "取消",
                "我知道了",
                "你的手机没有授权软件权限,将无法正常使用!",
                R.color.color_dialog_btn, R.color.color_ffa300, object : AlertDialogUtils.DialogTwoListener {
            override fun onClickLeft(dialog: Dialog?) {
                dialog?.dismiss()
                showMessage("无法获取存读取权限,您的app将无法正常使用")
                mContext.finish()
            }

            override fun onClickRight(dialog: Dialog?) {
                dialog?.dismiss()
                ActivityCompat.requestPermissions(mContext, needList.toTypedArray(), requestCode)
            }

        })
    }

    fun showMessage(message: String?) {
        if (mContext == null || TextUtils.isEmpty(message)) return
        AlertUtil.showDeftToast(mContext, message)
    }
}