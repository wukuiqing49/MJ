package com.wu.mj.module.home.frame.view

import android.text.TextUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.R
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.module.home.ui.activity.InfoWebViewActivity
import com.wu.mj.utlis.JsObject

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/12
 *
 * 用途:
 */


class InfoWebView : MvpView {

    var mActivity: InfoWebViewActivity

    constructor(mActivity: InfoWebViewActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        initToolBar(mActivity.title)

    }

    fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar

        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }
    }


    fun showView(icon: String?, desc: String?) {

//        mActivity.mAgentWeb = AgentWeb.with(mActivity)
//                .setAgentWebParent(mActivity.binding.webRoot, RelativeLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator()
//                .createAgentWeb()
//                .ready()
//                .go(getHtmlData(desc,icon))

        getHtmlData(desc, icon, mActivity.binding.web)

    }

    fun getHtmlData(bodyHTML: String?, icon: String?, web: WebView) {
        var strs: String? = null
        if (TextUtils.isEmpty(icon)) {
            strs = ""
        } else {
            strs = "<img src=" + icon + ">" + "</'img'>"

        }
        var newStr=bodyHTML!!.replace("\n","<br/>").toString()

        var strP = "<p>" + newStr + "</p>"

        val html = "<html><head><meta charset=\"UTF-8\"><style type=\"text/css\">html,body{padding:5px;margin:5px;font-size:" + 13 + "px} img{background-size:contain|cover;width:100%;height: auto;} p{margin:0px;font-size:" + 13 + "px}</style></head><body>" + strs+strP + "</body></html>"
        // 设置支持javascript脚本
        web.getSettings().setJavaScriptEnabled(true)
        web.addJavascriptInterface(JsObject(), "injectedObject")
        web.setWebViewClient(object : WebViewClient() {
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
                web.loadUrl(js)
            }
        })

        web.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)


    }


}