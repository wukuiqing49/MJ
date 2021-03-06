package com.wu.mj.module.home.frame.presenter

import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mj.module.home.frame.model.ChapterInfo
import com.wu.mj.module.home.frame.view.ChapterListView
import com.wu.mj.module.home.ui.activity.ChapterListActivity
import com.wu.mj.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class ChapterListPresenter : MvpBasePresenter<ChapterListView>() {
    //获取章节的数据
    fun initData(chapterListActivity: ChapterListActivity) {
        var db = DBUtlis(chapterListActivity)
        var chapters = db.chapterList
        chapters.forEach {
            it?.progress = db.getProgress(it?.index)
        }
        if (view != null) {
            view.showData(chapters)
        }
    }

    fun initDataType(chapterListActivity: ChapterListActivity, type: String?) {
        var db = DBUtlis(chapterListActivity)
        var chapters: List<ChapterInfo>? = null
        chapters = db.getHistoryChapterList(type)
        chapters.forEach {
            it?.progress = db.getProgressOther(it?.id)
        }
        if (view != null) {
            view.showData(chapters)
        }
    }

}