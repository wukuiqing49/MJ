package com.wu.mj.module.home.frame.view


import android.webkit.WebView
import android.webkit.WebViewClient
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.R

import com.wu.common.utils.StatusBarUtil
import com.wu.common.utils.WebViewUtil
import com.wu.mj.module.home.ui.activity.WebViewNewsActivity
import com.wu.mj.utlis.DBUtlis
import com.wu.mj.utlis.JsObject


/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/21
 *
 * 用途:
 */

class WebViewNewsView : MvpView {

    var mActivity: WebViewNewsActivity

    constructor(mActivity: WebViewNewsActivity) {
        this.mActivity = mActivity
    }

    fun initView() {

        initToolBar(mActivity.title)


        var newStr = DBUtlis(mActivity).getNewInfo(mActivity.id).a_desc!!.replace("\n", "<br/>").toString()

        var strP = "<p>" + newStr + "</p>"

        val html = "<html><head><meta charset=\"UTF-8\"><style type=\"text/css\">html,body{padding:5px;margin:5px;font-size:" + 13 + "px} img{background-size:contain|cover;width:100%;height: auto;} p{margin:0px;font-size:" + 13 + "px}</style></head><body>" + strP +"</body></html>"
        // 设置支持javascript脚本
        mActivity.binding.webRoot.getSettings().setJavaScriptEnabled(true)
        mActivity.binding.webRoot.addJavascriptInterface(JsObject(), "injectedObject")
        mActivity.binding.webRoot.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                val js = "javascript:(function(){" +
                        "var images = document.getElementsByTagName(\"img\");" +
                        "var imageUrls = new Array(); " +
                        "for(var i=0; i<images.length; i++) {" +
                        "   imageUrls[i] = images[i].src; " +
                        "   images[i].pos = i; " +
                        "   images[i].onclick = function(){" +
                        "       window.injectedObject.openImage(this.src, this.pos);" +
                        "   }" +
                        "}" +
                        "window.injectedObject.setImageUrls(imageUrls);  " +
                        "})()"
                mActivity.binding.webRoot.loadUrl(js)
            }
        })

        mActivity.binding.webRoot.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)


    }

    fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar

        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }
    }


}


