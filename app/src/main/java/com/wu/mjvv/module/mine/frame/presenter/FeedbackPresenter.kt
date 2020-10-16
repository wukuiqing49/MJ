package com.wu.mjvv.module.mine.frame.presenter

import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.common.utils.TimerWaitUtil
import com.wu.mjvv.module.mine.frame.view.FeedbackView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/14
 *
 * 用途:
 */


class FeedbackPresenter :MvpBasePresenter<FeedbackView>() {

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