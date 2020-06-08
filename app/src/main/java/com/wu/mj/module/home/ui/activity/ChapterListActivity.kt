package com.wu.mj.module.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
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

    companion object{
        fun newInstance(context: Context) {
            var intnet = Intent(context, ChapterListActivity().javaClass)
            context.startActivity(intnet)
        }

    }
    override fun getLayoutId(): Int {
        return R.layout.activity_chapter_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(mvpView!=null)mvpView.initView()
        if(presenter!=null)presenter.initData(this)
    }
}