package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Fragment_member_login extends Fragment {


    //Decalaration
    String GET_URL="http://lab.sitraonline.org/index.php/api/authendicate_member_login";

    EditText et_userid, et_password;
    CheckBox checkBox;
    private SharedPreferences mpref;
    private static final String PREF_NAME="SP_NAME";
    Button Login,cancel;
    String userid, password;
    String deviceid="";
    String custid;
    private String url_reg = "http://lab.sitraonline.org/index.php/api/validate_device_registration";
    String android_id;

    private String url_mob_track = "http://lab.sitraonline.org/index.php/api/mobile_app_tracking";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    ArrayList<String> list;


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancetate) {
        View view = inflater.inflate(R.layout.fragment_member_login, container, false);


        //initialization
        et_userid = (EditText) view.findViewById(R.id.et_userid);
        et_password = (EditText) view.findViewById(R.id.et_password);
        Login = (Button) view.findViewById(R.id.btn_submit);
        cancel = (Button) view.findViewById(R.id.btn_cancel);
        sendd();
        mpref=getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        userid=mpref.getString("USERID","");
        et_userid.setText(userid);
        password=mpref.getString("PASSWORD","");
        et_password.setText(password);
        checkBox=(CheckBox)view.findViewById(R.id.check);
        checkBox.setChecked(true);
        WifiManager manager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add",android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen
        sendd();
        deviceid=android_id;
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HomeScreen.class);
                startActivity(intent);
            }
        });






        //login click listener


        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendd_track();
                Log.i("userid","+++++++"+userid+password);
                if(checkBox.isChecked()==true){
                    userid = et_userid.getText().toString();
                    password = et_password.getText().toString();
                    if(TextUtils.isEmpty(userid)||TextUtils.isEmpty(password)){
                        Toast.makeText(getActivity(), "All Fields Must Be filled", Toast.LENGTH_SHORT).show();
                    }else {
                        SharedPreferences.Editor editor=mpref.edit();
                        editor.putString("USERID",userid);
                        editor.putString("PASSWORD",password);
                        editor.apply();

                    }
                }
                userid = et_userid.getText().toString();
                password = et_password.getText().toString();

                Log.i("userid()","+++++++"+userid+password);




                RequestQueue queue = Volley.newRequestQueue(getContext());
                StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("My success", "" + response);
                        try {
                            JSONObject input=new JSONObject(response);
                            String cusid=input.getString("custid");
                            String cus_name=input.getString("name");
                            String cusumer_advance=input.getString("customer_advance");
                            String customer_pending=input.getString("customer_pending");
                            String flag=input.getString("advance_pending_visibility");
                            String message=input.getString("advance_pending_message");
                            Intent intent=new Intent(getActivity(),MemberLoginMenu.class);
                            intent.putExtra("cusid",cusid);
                            intent.putExtra("userid",userid);
                            intent.putExtra("password",password);
                            intent.putExtra("cusname",cus_name);
                            intent.putExtra("cus_advance",cusumer_advance);
                            intent.putExtra("cus_pending",customer_pending);
                            intent.putExtra("flag",flag);
                            intent.putExtra("message",message);
                            startActivity(intent);
                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                        Log.i("My error", "" + error);
                    }
                }) {
                    @Override

                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("userid", userid);
                        map.put("password", password);


                        return map;
                    }
                };
                queue.add(request);

            }
        });
        return view;
    }
    public void sendd(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, url_reg, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);


                try {
                    //************parsing response object************
                    JSONArray ja=new JSONArray(response);

                    JSONObject object = ja.getJSONObject(0);


                    custid = object.getString("custid");

                    //****************parsing common data*************




                } catch (JSONException e) {
                    e.printStackTrace();


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
    public void sendd_track(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, url_mob_track, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);



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
                map.put("device_id", deviceid);
                map.put("custid", custid);
                map.put("message", "member logged in");
                map.put("input_values", "userid : "+userid+" Password : "+password);
                Log.i("device id",deviceid);

                return map;
            }
        };
        queue.add(request);


    }
}



