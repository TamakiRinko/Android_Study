package com.example.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);

        //加载线上url
//        webView.loadUrl("http://www.baidu.com");
        //加载本地html
        webView.loadUrl("file:///android_asset/test.html");

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
    }
}
