package com.wu.mjvv.module.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mjvv.R
import com.wu.mjvv.databinding.FragmentInfoViewBinding
import com.wu.mjvv.module.home.frame.presenter.InfoWebPresenter
import com.wu.mjvv.module.home.frame.view.InfoWebView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/12
 *
 * 用途:
 */


class InfoWebViewActivity : MvpBindingActivity<InfoWebView, InfoWebPresenter, FragmentInfoViewBinding>() {

    var title: String? = null
    var index: String? = null
    fun newInstance(context: Context, index: String?, title: String?) {
        var intnet = Intent(context, InfoWebViewActivity().javaClass)
        intnet.putExtra("index", index)
        intnet.putExtra("title", title)
        context.startActivity(intnet)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_info_view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = intent.getStringExtra("title")
        index = intent.getStringExtra("index")
        if (view != null) view.initView()
        if (presenter != null) {
            presenter.getData(this, index)
        }

    }
}