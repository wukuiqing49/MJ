package com.wu.mjvv.module.home.frame.presenter

import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mjvv.module.home.frame.view.InfoWebView
import com.wu.mjvv.module.home.ui.activity.InfoWebViewActivity
import com.wu.mjvv.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/12
 *
 * 用途:
 */


class InfoWebPresenter : MvpBasePresenter<InfoWebView>() {

    fun getData(infoWebViewActivity: InfoWebViewActivity, index: String?) {

        var db = DBUtlis(infoWebViewActivity)

        var info = db.getInfomationDetail(index)

        view.showView(info.icon,info.desc)


    }
}