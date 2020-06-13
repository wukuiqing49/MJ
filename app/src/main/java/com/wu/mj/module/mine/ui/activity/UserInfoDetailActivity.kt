package com.wu.mj.module.mine.ui.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mj.R
import com.wu.mj.databinding.ActivityUserDetailBinding
import com.wu.mj.module.home.ui.activity.ResultActivity
import com.wu.mj.module.mine.frame.presenter.UserInfoDetailPresenter
import com.wu.mj.module.mine.frame.view.UserInfoDetailView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/13
 *
 * 用途:
 */


class UserInfoDetailActivity : MvpBindingActivity<UserInfoDetailView, UserInfoDetailPresenter, ActivityUserDetailBinding>() {

    companion object {
        fun newInstance(context: Context) {
            var intnet = Intent(context, UserInfoDetailActivity().javaClass)
            context.startActivity(intnet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_user_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mvpView != null) mvpView.initView()
    }
}