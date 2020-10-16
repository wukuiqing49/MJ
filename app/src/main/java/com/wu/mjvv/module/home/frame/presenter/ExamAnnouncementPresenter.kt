package com.wu.mjvv.module.home.frame.presenter

import android.content.Context
import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mjvv.module.home.frame.view.ExamAnnouncementView
import com.wu.mjvv.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/15
 *
 * 用途:
 */


class ExamAnnouncementPresenter :MvpBasePresenter<ExamAnnouncementView>() {
    fun getData(context: Context) {

        var db= DBUtlis(context)
        var lists=db.getAnnouncement()
        if(view!=null)view.showView(lists)
    }

}