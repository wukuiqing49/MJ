package com.wu.mj.module.home.frame.presenter

import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mj.module.home.frame.view.QuestionsInfoView
import com.wu.mj.module.home.ui.activity.QuestionsInfoActivity
import com.wu.mj.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsInfoPresenter :MvpBasePresenter<QuestionsInfoView>() {
    fun getQuestions(questionsInfoActivity: QuestionsInfoActivity, index: String?) {

       var db= DBUtlis(questionsInfoActivity)
        if (db.database.isOpen){
            db.getQuestionList(index)
        }

    }

}