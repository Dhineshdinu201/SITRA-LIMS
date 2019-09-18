package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;
import pl.droidsonroids.gif.GifImageView;

public class WebView_pdf extends AppCompatActivity {

WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_pdf);
        webView=(WebView)findViewById(R.id.webview);
        String content=getIntent().getStringExtra("html");
        Log.i("html",content);
        webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);



    }


}