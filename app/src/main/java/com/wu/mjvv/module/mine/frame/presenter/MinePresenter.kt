package com.wu.mjvv.module.mine.frame.presenter

import android.content.Context
import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.common.utils.TimerWaitUtil
import com.wu.mjvv.module.mine.frame.view.MineView
import com.wu.mjvv.utlis.DBUtlis

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

    var timerUtil: TimerWaitUtil? = null

    fun startTimer() {
        timerUtil = TimerWaitUtil()

        timerUtil!!.totalTime = 2000
        timerUtil!!.intervalTime = 100
        timerUtil!!.setTimerLiener(object : TimerWaitUtil.TimeListener {
            override fun onFinish() {
                if (view != null) view.finishTimer()
                if (timerUtil != null) timerUtil!!.cancel()

            }

            override fun onInterval(time: Long) {

            }

        })
        timerUtil!!.start()

    }


}