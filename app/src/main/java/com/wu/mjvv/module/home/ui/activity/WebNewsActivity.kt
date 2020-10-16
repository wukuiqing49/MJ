package com.wu.mjvv.module.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.common.R
import com.wu.common.databinding.ActivityWebPBinding
import com.wu.mjvv.module.home.frame.presenter.WebViewNewsPresenter
import com.wu.mjvv.module.home.frame.view.WebViewNewsView


/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/21
 *
 * 用途:
 */

class WebViewNewsActivity : MvpBindingActivity<WebViewNewsView, WebViewNewsPresenter, ActivityWebPBinding>() {

    var id: String? = null
    var title: String? = null

    companion object {
        fun newInstance(context: Context, id: String?,title:String?) {
            var intnet = Intent(context, WebViewNewsActivity().javaClass)
            intnet.putExtra("id", id)
            intnet.putExtra("title", title)
            context.startActivity(intnet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web_p
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        if (mvpView != null) mvpView.initView()

    }

}


