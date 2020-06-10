package com.wu.mj.module.home.frame.presenter

import android.content.Context
import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mj.module.home.frame.view.QuestionsDetailView
import com.wu.mj.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsDetailPresenter : MvpBasePresenter<QuestionsDetailView>() {

    fun getQuestionData(context: Context?, problemId: String?) {
        var db = DBUtlis(context)
        if (db.database.isOpen) {
            var anwers = db.getAnwserList(problemId)
            if (view != null) view.showData(anwers)
        }
    }

}