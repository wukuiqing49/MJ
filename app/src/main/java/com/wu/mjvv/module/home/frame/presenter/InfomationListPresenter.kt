package com.wu.mjvv.module.home.frame.presenter

import android.content.Context
import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mjvv.module.home.frame.view.InformationListView
import com.wu.mjvv.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/11
 *
 * 用途:
 */


class InfomationListPresenter : MvpBasePresenter<InformationListView> (){

    fun getData(context: Context, type: String?) {
        var db=DBUtlis(context)
        if (view!=null)view.showData( db.getInfomationInfoList(type))

    }

}