package com.mmts.mykotlindemo.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mmts.mykotlindemo.R
import com.mmts.mykotlindemo.util.JavaScriptMethods
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    private var WEB_URL = "https://www.baidu.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        //接收传值
        val bundle:Bundle?=intent.extras
        if(bundle!=null){
            WEB_URL = bundle.getString("url").toString()
        }
        initWebView()
    }
    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView(){
        web_view?.loadUrl(WEB_URL)

        val webClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }

        //下面这些直接复制就好
        web_view?.webViewClient=webClient
        //3设置Android 和 js沟通的桥梁
        web_view?.addJavascriptInterface(JavaScriptMethods(this,web_view),"JavaScriptMoth")

        val webSettings = web_view!!.settings
        webSettings.javaScriptEnabled = true  // 开启 JavaScript 交互
        webSettings.setAppCacheEnabled(true) // 启用或禁用缓存
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT // 只要缓存可用就加载缓存, 哪怕已经过期失效 如果缓存不可用就从网络上加载数据
        webSettings.setAppCachePath(cacheDir.path) // 设置应用缓存路径

        // 缩放操作
        webSettings.setSupportZoom(false) // 支持缩放 默认为true 是下面那个的前提
        webSettings.builtInZoomControls = false // 设置内置的缩放控件 若为false 则该WebView不可缩放
        webSettings.displayZoomControls = false // 隐藏原生的缩放控件

        webSettings.blockNetworkImage = false // 禁止或允许WebView从网络上加载图片
        webSettings.loadsImagesAutomatically = true // 支持自动加载图片

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webSettings.safeBrowsingEnabled = true // 是否开启安全模式
        }

        webSettings.javaScriptCanOpenWindowsAutomatically = true // 支持通过JS打开新窗口
        webSettings.domStorageEnabled = true // 启用或禁用DOM缓存
        webSettings.setSupportMultipleWindows(true) // 设置WebView是否支持多窗口

        // 设置自适应屏幕, 两者合用
        webSettings.useWideViewPort = true  // 将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true  // 缩放至屏幕的大小
        webSettings.allowFileAccess = true // 设置可以访问文件

        webSettings.setGeolocationEnabled(true) // 是否使用地理位置

        web_view?.fitsSystemWindows = true
        web_view?.setLayerType(View.LAYER_TYPE_HARDWARE,null)
        web_view?.loadUrl(WEB_URL)
    }

    //设置返回键的监听
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (web_view!!.canGoBack()){
                web_view!!.goBack()  //返回上一个页面
                return true
            }else{
                finish()
                return true
            }
        }
        return false
    }
}

