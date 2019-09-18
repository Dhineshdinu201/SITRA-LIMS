package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {
WebView webn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String url=getIntent().getStringExtra("c");
        webn=(WebView)findViewById(R.id.webn);
        webn.loadUrl(url);
        webn.setWebViewClient(new WebViewClient());
        WebSettings webSettings=webn.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
