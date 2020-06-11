package com.wu.mj.module.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mj.R
import com.wu.mj.databinding.ActivityChapterListBinding
import com.wu.mj.module.home.frame.presenter.ChapterListPresenter
import com.wu.mj.module.home.frame.view.ChapterListView
import com.wu.mj.module.login.ui.activity.RegistActivity

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class ChapterListActivity : MvpBindingActivity<ChapterListView, ChapterListPresenter, ActivityChapterListBinding>() {
    var type=""

    companion object {
        fun newInstance(context: Context) {
            var intnet = Intent(context, ChapterListActivity().javaClass)
            context.startActivity(intnet)
        }

        fun newInstance(context: Context, type: String) {
            var intnet = Intent(context, ChapterListActivity().javaClass)
            intnet.putExtra("type", type)
            context.startActivity(intnet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chapter_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = intent.getStringExtra("type")
        if (mvpView != null) mvpView.initView()
        if(TextUtils.isEmpty(type)){
            if (presenter != null) presenter.initData(this)
        }else{
            if (presenter != null) presenter.initDataType(this,type)
        }

    }
}