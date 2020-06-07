package com.wu.mj.module.launch.frame.presenter

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.common.utils.TimerWaitUtil
import com.wu.mj.module.launch.frame.view.LaunchView

import java.util.*

/**
 * 作者: 吴奎庆
 *
 * 时间: 2020/5/14
 *
 * 简介:
 */
class LaunchPresenter : MvpBasePresenter<LaunchView>() {
    var timerUtil: TimerWaitUtil? = null

    fun startTimer(startTime: Long) {
        timerUtil = TimerWaitUtil()
        timerUtil!!.totalTime = startTime
        timerUtil!!.intervalTime = 100
        timerUtil!!.setTimerLiener(object : TimerWaitUtil.TimeListener {
            override fun onFinish() {
                if (view != null) view.finishTimer()
            }

            override fun onInterval(time: Long) {
                if (view != null) view.onInterval(time)
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


    fun checkPermissions(activity: Activity?, permissions: Array<String>, requestCode: Int): Boolean { //Android6.0以下默认有权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
        val needList: MutableList<String> = ArrayList()
        var needShowRationale = false
        val length = permissions.size
        for (i in 0 until length) {
            val permisson = permissions[i]
            if (TextUtils.isEmpty(permisson)) continue
            if (ActivityCompat.checkSelfPermission(activity!!, permisson)
                    != PackageManager.PERMISSION_GRANTED) {
                needList.add(permisson)
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permisson)) needShowRationale = true
            }
        }
        return if (needList.size != 0) {
            if (needShowRationale) {
                view.showPermission(needList, requestCode)
                return false
            }
            ActivityCompat.requestPermissions(activity!!, needList.toTypedArray(), requestCode)
            false
        } else {
            true
        }
    }

    fun onRequestPermissionsResult(activity: Activity?, requestCode: Int, permissions: Array<String?>, grantResults: IntArray): BooleanArray? {
        var result = true
        var isNerverAsk = false
        val length = grantResults.size
        for (i in 0 until length) {
            val permission = permissions[i]
            val grandResult = grantResults[i]
            if (grandResult == PackageManager.PERMISSION_DENIED) {
                result = false
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity!!, permission!!)) isNerverAsk = true
            }
        }
        return booleanArrayOf(result, isNerverAsk)
    }


}