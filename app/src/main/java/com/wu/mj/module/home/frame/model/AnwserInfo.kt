package com.wu.mj.module.home.frame.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.wu.mj.BR


/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/9
 *
 * 用途:
 */


class AnwserInfo : BaseObservable() {

    var id: String? = null
    var anwer: String? = null
    var values: String? = null
    var problemId: String? = null

    @Bindable
    var isCheck: Boolean = false

    fun setIsCheck(check: Boolean) {
        isCheck = check
        notifyPropertyChanged(BR.isCheck)
    }
    fun getIsCheck() :Boolean{
       return isCheck
    }
}