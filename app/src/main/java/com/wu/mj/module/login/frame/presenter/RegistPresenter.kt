package com.wu.mj.module.login.frame.presenter

import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.common.utils.TimerWaitUtil
import com.wu.mj.module.login.frame.view.RegistView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/7
 *
 * 用途:
 */


class RegistPresenter :MvpBasePresenter<RegistView>() {

    var timerUtil: TimerWaitUtil? = null

    fun startTimer() {
        timerUtil = TimerWaitUtil()
        view.showLoaiding(true)
        timerUtil!!.totalTime = 2000
        timerUtil!!.intervalTime = 100
        timerUtil!!.setTimerLiener(object : TimerWaitUtil.TimeListener {
            override fun onFinish() {
                view.showLoaiding(false)
                if (view != null) view.finishTimer()
                if (timerUtil != null) timerUtil!!.cancel()

            }

            override fun onInterval(time: Long) {

            }

        })
        timerUtil!!.start()

    }

    fun resume() {
        if (timerUtil != null) timerUtil!!.resume()

    }
    fun stop() {
        if (timerUtil != null) timerUtil!!.pause()

    }
}