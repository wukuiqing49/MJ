package com.wu.common.module.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.just.agentweb.AgentWeb
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.common.R
import com.wu.common.databinding.ActivityWebBinding
import com.wu.common.databinding.ActivityWebPBinding
import com.wu.common.module.frame.presenter.WebViewPPresenter
import com.wu.common.module.frame.presenter.WebViewPresenter
import com.wu.common.module.frame.view.WebViewPView
import com.wu.common.module.frame.view.WebViewView


/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/21
 *
 * 用途:
 */

class WebViewPActivity : MvpBindingActivity<WebViewPView, WebViewPPresenter, ActivityWebPBinding>() {

    var type: String? = null
    var title: String? = null

    companion object {
        fun newInstance(context: Context, type: String?,title:String?) {
            var intnet = Intent(context, WebViewPActivity().javaClass)
            intnet.putExtra("type", type)
            intnet.putExtra("title", title)
            context.startActivity(intnet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web_p
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = intent.getStringExtra("type")
        title = intent.getStringExtra("title")
        if (mvpView != null) mvpView.initView()

    }

}


