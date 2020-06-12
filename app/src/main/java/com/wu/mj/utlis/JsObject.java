package com.wu.mj.utlis;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import java.util.ArrayList;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020/6/12
 * <p>
 * 用途:
 */


public class JsObject {
    private String[] mImageUrls;

    @JavascriptInterface
    public void setImageUrls(String[] imageUrls) {
        this.mImageUrls = imageUrls;
    }

    @JavascriptInterface
    public void openImage(String src, int pos) {

    }
}
