package com.wu.mj.module.mine.frame.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.database.utils.DataBaseUtils
import com.wu.common.utils.AlertDialogUtils
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.CacheUtil
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.ProgressInfo
import com.wu.mj.module.login.ui.activity.LoginActivity
import com.wu.mj.module.mine.ui.activity.AboutActivity
import com.wu.mj.module.mine.ui.activity.FeedbackActivity
import com.wu.mj.module.mine.ui.activity.UserInfoDetailActivity
import com.wu.mj.module.mine.ui.fragment.MineFragment
import com.wu.mj.utlis.DBUtlis


/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/25
 *
 * 用途:
 */


class MineView : MvpView, View.OnClickListener {

    var request = RequestOptions.centerCropTransform().error(R.drawable.iv_icon_defult).placeholder(R.drawable.iv_icon_defult)

    var mFragment: MineFragment

    constructor(mFragment: MineFragment) {
        this.mFragment = mFragment
    }

    fun initView() {

        mFragment.binding.tvClear.setText(CacheUtil.getTotalCacheSize(mFragment.activity))
        mFragment.binding.rlAbout.setOnClickListener(this)
        mFragment.binding.rlFeeedBack.setOnClickListener(this)
        mFragment.binding.rlVersion.setOnClickListener(this)
        mFragment.binding.rlUser.setOnClickListener(this)
        mFragment.binding.cdLogin.setOnClickListener(this)

        if (DataBaseUtils.getUser(mFragment.activity) != null) {
            mFragment.binding.tvName.setText(DataBaseUtils.getUser(mFragment.activity).userName)
        }
        Glide.with(mFragment).load(DataBaseUtils.getUser(mFragment.activity).userIcon).apply(request).into(mFragment.binding.ivIcon)


    }

    private fun clearCache() {
        AlertDialogUtils.showClearDialog(mFragment.activity, "取消", "确定", "您确认要清除本地缓存吗?", "清除缓存", R.color.color_333, R.color.color_23d41e, object : AlertDialogUtils.DialogTwoListener {
            override fun onClickLeft(dialog: Dialog?) {
                dialog?.cancel()
            }

            override fun onClickRight(dialog: Dialog?) {

                CacheUtil.clearAllCache(mFragment.activity)
                dialog?.cancel()
                showMessage("缓存清理成功")
                mFragment.binding.tvClear.setText(CacheUtil.getTotalCacheSize(mFragment.activity))
            }

        })
    }

    fun showMessage(message: String?) {
        if (mFragment == null || TextUtils.isEmpty(message)) return
        AlertUtil.showDeftToast(mFragment!!.activity, message)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rl_clear -> {
                clearCache()
            }

            R.id.rl_about -> {
                AboutActivity.newInstance(mFragment.activity as Context)
            }

            R.id.rl_feeed_back -> {
                FeedbackActivity.newInstance(mFragment.activity as Context)
            }

            R.id.rl_version -> {
                mFragment.presenter.startTimer()
            }
            R.id.rl_user -> {
                UserInfoDetailActivity.newInstance(mFragment.activity as Context)
            }
            R.id.cd_login -> {
                logout()
            }


        }
    }

    private fun logout() {

        AlertDialogUtils.showClearDialog(mFragment.activity, "取消", "确定", "注销登录将清除您的答题记录,您是否要注销登录?", "注销登录", R.color.color_333, R.color.color_23d41e, object : AlertDialogUtils.DialogTwoListener {
            override fun onClickLeft(dialog: Dialog?) {
                dialog?.cancel()
            }

            override fun onClickRight(dialog: Dialog?) {

                dialog?.cancel()
                logoutUSer()
            }

        })
    }

    private fun logoutUSer() {
        DBUtlis(mFragment.activity).clearCache()
        DataBaseUtils.setOtheLoginFalse(mFragment.activity)
        var intent = Intent(mFragment.activity, LoginActivity().javaClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        mFragment.activity!!.startActivity(intent)
    }

    fun showHistory(typeProgress: ProgressInfo?) {
        mFragment.binding.tvLs.setText(typeProgress?.nowProgress.toString() + " / " + typeProgress?.totalProgress.toString())
    }

    fun showSIMULATION(typeProgress: ProgressInfo?) {
        mFragment.binding.tvMn.setText(typeProgress?.nowProgress.toString() + " / " + typeProgress?.totalProgress.toString())
    }

    fun showLX(lxProgress: ProgressInfo?) {
        mFragment.binding.tvLx.setText(lxProgress?.nowProgress.toString() + " / " + lxProgress?.totalProgress.toString())

    }

    fun showLv(totalProgress: ProgressInfo) {
        if (totalProgress.nowProgress!!.toInt() > 100) {
            mFragment.binding.ivLv.visibility = View.VISIBLE
        } else {
            mFragment.binding.ivLv.visibility = View.GONE
            return
        }

        var progress = totalProgress!!.nowProgress!!.toFloat() / totalProgress!!.totalProgress!!
        if (progress > 0.9) {
            mFragment.binding.ivLv.setBackgroundResource(R.mipmap.iv_perfect)
        } else if (progress > 0.8) {
            mFragment.binding.ivLv.setBackgroundResource(R.mipmap.iv_good)
        } else if (progress > 0.6) {
            mFragment.binding.ivLv.setBackgroundResource(R.mipmap.iv_success)
        } else {
            mFragment.binding.ivLv.setBackgroundResource(R.mipmap.iv_bad)
        }

    }

    fun updateIcon() {
        Glide.with(mFragment).load(DataBaseUtils.getUser(mFragment.activity).userIcon).apply(request).into(mFragment.binding.ivIcon)


    }

    fun updateName() {
        if (DataBaseUtils.getUser(mFragment.activity) != null) {
            mFragment.binding.tvName.setText(DataBaseUtils.getUser(mFragment.activity).userName)
        }

    }

    fun finishTimer() {

        showMessage("暂无新版本")

    }

}