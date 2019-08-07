package com.example.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.channels.ClosedSelectorException;

public class WebViewButtonActivity extends AppCompatActivity {

    private WebView webView;

    public class TestJSEvent{
        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(WebViewButtonActivity.this, toast, Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);

        //设置调试状态
        WebView.setWebContentsDebuggingEnabled(true);

        //Cookie管理
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie("domain", "cookie");

        //加载线上url
        webView.loadUrl("https://www.baidu.com");
        //加载本地html
//        webView.loadUrl("file:///android_asset/test.html");

        //允许与JavaScript交互
        webView.getSettings().setJavaScriptEnabled(true);

        //JS调用原生App
        //把对象注入到JS中，取名TestApp
        //此时，可以在JS代码中调用 TestJSEvent 类的方法
        webView.addJavascriptInterface(new TestJSEvent(), "TestApp");

        //原生App调用JS，直接如下调用JS中的方法即可
        webView.loadUrl("javascript:javaCallJS()");


        //使得网页能够在当前窗口打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //是否重新加载，可以用来拦截界面！
//                view.loadUrl("http://www.baidu.com");
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //页面开始，显示loading动画
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //初始页面加载结束，隐藏loading动画！
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //可以替换url的内容，url为资源文件
                super.onLoadResource(view, url);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                //可拦截任何请求！也可伪造请求
                return super.shouldInterceptRequest(view, request);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //获取网页加载的进度
                //可自己定义ProgressBar，然后显示加载newProgress即可
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取网页的标题，可以显示
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                super.onReceivedTouchIconUrl(view, url, precomposed);
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
            }

            @Override
            public void onCloseWindow(WebView window) {
                //关闭窗口时
                super.onCloseWindow(window);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                return super.onJsBeforeUnload(view, url, message, result);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                //获取位置权限
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
            }
        });

        //可自定义内部类
        webView.setWebChromeClient(new TestWebChromeClient());
    }

    /**
     * 点击返回时会调用这个方法
     * 现在不想退出界面，而是想回退到上一个页面
     */
    @Override
    public void onBackPressed() {
        if(webView != null && webView.canGoBack()){
//            //获得历史记录
//            WebBackForwardList webBackForwardList = webView.copyBackForwardList();  //历史记录List
//            WebHistoryItem historyItem = webBackForwardList.getItemAtIndex(0);      //获得第几个历史记录
//            String historyUrl = historyItem.getUrl();       //获得历史记录的URL

            //能够回退就回退到上个页面
            webView.goBack();       //后退
//            webView.goForward();    //前进
//            webView.goBackOrForward(3);     //前进或后退
//            webView.reload();   //刷新

        }
        else{
            //否则回退到上个界面
            super.onBackPressed();
        }
    }

    public class TestWebChromeClient extends WebChromeClient{

    }

}
