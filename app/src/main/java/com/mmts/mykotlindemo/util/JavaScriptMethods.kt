package com.mmts.mykotlindemo.util

import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
/**
 * @author: As
 * @date: 2022-02-28
 */
class JavaScriptMethods {
    var mContext: Context? = null
    var mWebView: WebView? = null

    constructor(mContext: Context, mWebView: WebView) {
        this.mContext = mContext
        this.mWebView = mWebView
    }

    /**
     * js要调用的安卓的无参方法
     */
    @JavascriptInterface
    fun jsCallAndroidMethod() {
        mContext!!.toast("js要调用的安卓的方法")
    }

    /**
     * js要调用的安卓的有参方法
     */
    @JavascriptInterface
    fun jsCallAndroidMethod(string: String) {
        mContext!!.toast(string)
    }

    /**
     * js要调用的安卓的方法，获得数据，然后把数据回传给js
     */
    @JavascriptInterface
    fun getData(jsMothName: String) {
        //模拟去网上获得数据
        doAsync {
            Thread.sleep(2 * 1000)
            var string = "网络数据"
            //回调js的方法。
            callbackJs(jsMothName, string)
        }
    }

    /**
     * android 回调js，并向js传数据。
     */
    private fun callbackJs(jsMothName: String, string: String) {
        //调用js的方法必须在ui线程中运行。
        mContext!!.runOnUiThread {
            mWebView!!.loadUrl("javascript:$jsMothName('$string')")
        }
    }

}