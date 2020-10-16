package com.wu.mjvv.module.mine.frame.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.StatusBarUtil
import com.wu.mjvv.R
import com.wu.mjvv.module.mine.ui.activity.AboutActivity


/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/14
 *
 * 用途:
 */


class AboutView : MvpView {


    var mActivity: AboutActivity

    constructor(mActivity: AboutActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        initToolBar("关于我们")
        mActivity.binding.rlSupport.setOnClickListener { joinQQGroup("mdSrZGBrHPBX6dJjlJ7zKGi-hOac9N") }
        mActivity.binding.rlEmail.setOnClickListener {
            //获取剪贴板管理器：
            //获取剪贴板管理器：
            val cm: ClipboardManager? = mActivity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
// 创建普通字符型ClipData
            // 创建普通字符型ClipData
            val mClipData = ClipData.newPlainText("Label", "1571478988@163.com")
// 将ClipData内容放到系统剪贴板里。
            // 将ClipData内容放到系统剪贴板里。
            cm?.setPrimaryClip(mClipData)
            showMessage("已复制到剪切板")
        }

    }

    private fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar
        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }

    }


    fun showMessage(message: String?) {
        if (mActivity == null || TextUtils.isEmpty(message)) return
        AlertUtil.showDeftToast(mActivity, message)
    }

    fun joinQQGroup(key: String): Boolean {
        val intent = Intent()
        intent.data = Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D$key")
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return try {
            mActivity.startActivity(intent)
            true
        } catch (e: Exception) { // 未安装手Q或安装的版本不支持
            false
        }
    }
}