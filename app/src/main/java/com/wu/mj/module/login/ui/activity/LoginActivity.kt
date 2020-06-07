package com.wu.mj.module.login.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mj.R
import com.wu.mj.databinding.ActivityLoginBinding
import com.wu.mj.module.login.frame.presenter.LoginPresenter
import com.wu.mj.module.login.frame.view.LoginView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/7
 *
 * 用途:
 */


class LoginActivity :MvpBindingActivity<LoginView,LoginPresenter,ActivityLoginBinding>() {

    companion object{

        fun newInstance(context: Context) {
            var intnet = Intent(context, LoginActivity().javaClass)
            context.startActivity(intnet)
        }

    }

    override fun getLayoutId(): Int {
       return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (view!=null)view.initView()
    }
}