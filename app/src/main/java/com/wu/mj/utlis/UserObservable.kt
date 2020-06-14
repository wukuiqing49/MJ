package com.wu.mj.utlis

import java.util.*

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/14
 *
 * 用途: 用户信息修改的观察者
 */


object UserObservable : Observable() {


    fun update(type: Int) {
        setChanged()
        notifyObservers(type)
    }

}