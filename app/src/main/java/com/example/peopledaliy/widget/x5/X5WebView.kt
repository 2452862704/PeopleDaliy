package com.example.peopledaliy.widget.x5

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


class X5WebView : WebView {
    private val client: WebViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            if (url != null) {
                view.loadUrl(url)
            }
            return true
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    constructor(arg0: Context?, arg1: AttributeSet?) : super(arg0, arg1) {
        this.setWebViewClient(client)
        initWebViewSettings()
        this.getView().setClickable(true)
    }

    private fun initWebViewSettings() {
        val webSetting: WebSettings = this.getSettings()
        webSetting.setJavaScriptEnabled(true)
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true)
        webSetting.setAllowFileAccess(true)
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
        webSetting.setSupportZoom(true)
        webSetting.setBuiltInZoomControls(true)
        webSetting.setUseWideViewPort(true)
        webSetting.setSupportMultipleWindows(true)
        webSetting.setAppCacheEnabled(true)
        webSetting.setDomStorageEnabled(true)
        webSetting.setGeolocationEnabled(true)
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND)
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE)
    }

    constructor(arg0: Context?) : super(arg0!!) {
        setBackgroundColor(85621)
    }
}
