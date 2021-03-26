package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import im.delight.android.webview.AdvancedWebView;
import pl.droidsonroids.gif.GifImageView;

public class Fragment_payment extends Fragment implements AdvancedWebView.Listener  {
    //*********************Declaration******************************
    Constant constant=new Constant();
    private String url = constant.ip+"validate_device_registration";
    private AdvancedWebView mWebView;
    Snackbar snackbar;
    GifImageView loading;
    String deviceid="";
    String android_id;
    String custid;

    public Fragment_payment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancetate) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
//        mWebView = (AdvancedWebView) view.findViewById(R.id.webview_payment);
//        mWebView.loadUrl("http://lab.sitraonline.org.in/index.php/payment/techprocess");
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        loading=(GifImageView)view.findViewById(R.id.gif_loading);
        mWebView = (AdvancedWebView) view.findViewById(R.id.webview_payment);
        mWebView.setListener(getActivity(), this);
        WifiManager manager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add",android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen

        deviceid=android_id;
        send();




        return view;
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);

    }


    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        if(snackbar!=null){
            snackbar.dismiss();
        }
        snackbar=snackbar.make(mWebView,"Loading...",snackbar.LENGTH_INDEFINITE);
        Log.i("Loading","......");
        loading.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPageFinished(String url) {
        if(snackbar!=null){
            snackbar.dismiss();
        }
        snackbar=snackbar.make(mWebView,"Loading Finished",snackbar.LENGTH_SHORT);
        Log.i("Loading Finished","finished");
        loading.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        if(snackbar!=null){
            snackbar.dismiss();
        }
        snackbar=snackbar.make(mWebView,"Loading Error",snackbar.LENGTH_LONG);
        Log.i("Loading","error");
        loading.setVisibility(View.INVISIBLE);
        Toast.makeText(getActivity(), "Error!...please check connection...", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
    public void send(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);


                try {
                    //************parsing response object************
                    JSONArray ja=new JSONArray(response);

                    JSONObject object = ja.getJSONObject(0);


                     custid = object.getString("custid");

                    //****************parsing common data*************
                    loading.setVisibility(View.INVISIBLE);


                    mWebView.loadUrl("http://lab.sitraonline.org.in/index.php/payment/techprocess/"+custid);
                    Log.i("custid",""+custid);




                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Something went wrong!...", Toast.LENGTH_SHORT).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Please check connectivity", Toast.LENGTH_SHORT).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("mobile_device_id", deviceid);
                Log.i("device id",deviceid);

                return map;
            }
        };
        queue.add(request);


    }
}

