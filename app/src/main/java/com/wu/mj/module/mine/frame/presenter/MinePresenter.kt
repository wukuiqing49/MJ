package com.wu.mj.module.mine.frame.presenter

import android.content.Context
import android.util.Log
import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mj.module.mine.frame.view.MineView
import com.wu.mj.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/25
 *
 * 用途:
 */


class MinePresenter : MvpBasePresenter<MineView>() {

    fun getHistoryData(context: Context) {
        var db= DBUtlis(context)
           if(view!=null) view.showHistory(  db.getTypeProgress("HISTORY"))
    }

    fun getSIMULATIONData(context: Context) {
        var db= DBUtlis(context)
        db.getTypeProgress("SIMULATION")
        if(view!=null) view.showSIMULATION(  db.getTypeProgress("SIMULATION"))
    }

    fun getLX(context: Context) {
        var db= DBUtlis(context)
        if(view!=null) view.showLX(db.getLXProgress())
    }
    fun getTotal(context: Context) {
        var db= DBUtlis(context)
        if(view!=null) view.showLv(db.getTotalProgress())
    }
}