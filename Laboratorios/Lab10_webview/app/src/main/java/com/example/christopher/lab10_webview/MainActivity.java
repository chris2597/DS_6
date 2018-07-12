package com.example.christopher.lab10_webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView _wv;
    String _url = "http://www.google.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _wv = (WebView) findViewById(R.id.wv_browser);
        _wv.setWebViewClient(new MyBrowser());
        _wv.getSettings().setLoadsImagesAutomatically(true);
        _wv.getSettings().setJavaScriptEnabled(true);
        _wv.getSettings().setDisplayZoomControls(true);
        _wv.setVerticalScrollBarEnabled(true);
        _wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        _wv.loadUrl(_url);
    }

    public class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}