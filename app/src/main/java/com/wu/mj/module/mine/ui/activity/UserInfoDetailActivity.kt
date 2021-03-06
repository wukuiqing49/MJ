package com.wu.mj.module.mine.ui.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wkq.database.utils.DataBaseUtils
import com.wu.mj.R
import com.wu.mj.databinding.ActivityUserDetailBinding
import com.wu.mj.module.home.ui.activity.ResultActivity
import com.wu.mj.module.mine.frame.presenter.UserInfoDetailPresenter
import com.wu.mj.module.mine.frame.view.UserInfoDetailView
import com.wu.mj.utlis.UserObservable
import java.util.*

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/13
 *
 * 用途:
 */


class UserInfoDetailActivity : MvpBindingActivity<UserInfoDetailView, UserInfoDetailPresenter, ActivityUserDetailBinding>(), Observer {

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
        UserObservable.addObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        UserObservable.deleteObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        if (o is UserObservable) {
            var type = arg as Int

            if (type == 2) {
                mvpView.updateNewIcon()
            }
            if (type == 1) {
                mvpView.updateNewName()
            }

        }


    }}