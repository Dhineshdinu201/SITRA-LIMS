package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.TwoStatePreference;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class MemberLoginMenu extends AppCompatActivity {
    ImageView pending, mem_view_rep,profile,stat_img;
    TextView advance_text, pending_text, txt_pending, txt_view_rep, txt_cusname,card_cus_name,txt_status,s_sam,t_lastupdate;
    Button payment_his,payment_mode,refresh;
    String cusname, cusid, cus_advance, cus_pending,cus_userid,cus_password,flag,message;
    Animation blink;
    String custid;



//    customer_advancemem_view_rep
//    customer_pending
//    advance_pending_message
//    mobile_advance_pending_lastupdation
//    advance_pending_visibility
    Constant constant=new Constant();
    private String url_reg = constant.ip+"validate_device_registration";
    private String url_adv = constant.ip+"get_customer_pending_advance";
    private String url_mob_track = constant.ip+"mobile_app_tracking";
    String deviceid="";
    String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login_menu);
        refresh=(Button)findViewById(R.id.refresh);
        pending = (ImageView) findViewById(R.id.pending);
        t_lastupdate=(TextView)findViewById(R.id.lastupdate);
        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add",android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen
        deviceid=android_id;
        sendd();

        try {
            cusid = getIntent().getStringExtra("cusid");
            cusname = getIntent().getStringExtra("cusname");
            cus_advance = getIntent().getStringExtra("cus_advance");
            cus_pending = getIntent().getStringExtra("cus_pending");
            cus_userid = getIntent().getStringExtra("userid");
            cus_password = getIntent().getStringExtra("password");
            flag=getIntent().getStringExtra("flag");
            message=getIntent().getStringExtra("message");

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        mem_view_rep = (ImageView) findViewById(R.id.mem_view_rep);
        profile = (ImageView) findViewById(R.id.profile);
        advance_text = (TextView) findViewById(R.id.advance_text);
        pending_text = (TextView) findViewById(R.id.pending_text);
        txt_pending = (TextView) findViewById(R.id.txt_pending);
        txt_cusname = (TextView) findViewById(R.id.cusname);
        txt_view_rep = (TextView) findViewById(R.id.txt_view_rep);
        payment_his = (Button) findViewById(R.id.payment_his);
        payment_mode=(Button)findViewById(R.id.payment_mode);
        card_cus_name=(TextView)findViewById(R.id.cus_name);
        txt_status=(TextView)findViewById(R.id.txt_status);
        s_sam=(TextView)findViewById(R.id.s_sam);
        stat_img=(ImageView)findViewById(R.id.stat_img);
        blink= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);


        advance_text.setText(cus_advance);
        pending_text.setText(cus_pending);
        txt_cusname.setText(cusname);
        card_cus_name.setText(cusname);
        if(flag.equals("0")){
            advance_text.setText("Collecting Information...");
            pending_text.setText("Collecting Information...");
            s_sam.setVisibility(View.INVISIBLE);
            stat_img.setVisibility(View.INVISIBLE);
            txt_status.setVisibility(View.INVISIBLE);
        }else if(flag.equals("1")){
            txt_status.setText(message);
            txt_status.setVisibility(View.VISIBLE);
            txt_status.startAnimation(blink);
            if(message.equals("")){
                stat_img.setImageResource(R.drawable.unlock);
            }else
            {
                stat_img.setImageResource(R.drawable.lock);
            }


        }
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberLoginMenu.this, ActivityMyProfile.class);
                intent.putExtra("userid",cus_userid);
                intent.putExtra("password",cus_password);
                startActivity(intent);
            }
        });

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberLoginMenu.this, Activity_Pending.class);
                intent.putExtra("cusid",cusid);
                startActivity(intent);
            }
        });
        txt_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberLoginMenu.this, Activity_Pending.class);
                intent.putExtra("cusid",cusid);
                startActivity(intent);
            }
        });
        mem_view_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberLoginMenu.this, PendingInwards.class);
                intent.putExtra("cusid",cusid);
                startActivity(intent);
            }
        });
        txt_view_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberLoginMenu.this, PendingInwards.class);
                intent.putExtra("cusid",cusid);
                startActivity(intent);
            }
        });
        payment_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberLoginMenu.this, ActivityPaymentHistory.class);
                intent.putExtra("cusid",cusid);
                startActivity(intent);
            }
        });
        payment_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberLoginMenu.this, ActivityPaymentMopde.class);
                intent.putExtra("cusid",cusid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you want to Logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendd_track();
                Intent intent = new Intent(MemberLoginMenu.this, HomeScreen.class);

                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void sendd(){
        RequestQueue queue = Volley.newRequestQueue(this);
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

                Toast.makeText(MemberLoginMenu.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
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
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url_mob_track, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MemberLoginMenu.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("device_id", deviceid);
                map.put("custid", custid);
                map.put("message", "member logout");
                map.put("input_values", "Inward no : "+""+" accesscode : "+"");
                Log.i("device id",deviceid);




                return map;
            }
        };
        queue.add(request);


    }
    public void update(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest request = new StringRequest(Request.Method.POST, url_adv, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);
                try {
                    JSONObject input=new JSONObject(response);
                    String cusumer_advance=input.getString("customer_advance");
                    String customer_pending=input.getString("customer_pending");
                    String flag=input.getString("advance_pending_visibility");
                    String message=input.getString("advance_pending_message");
                    String lastupdate=input.getString("mobile_advance_pending_lastupdation");
                    advance_text.setText(cusumer_advance);
                    pending_text.setText(customer_pending);
                    t_lastupdate.setVisibility(View.VISIBLE);
                    t_lastupdate.setText("Last Update :  "+lastupdate);
                    if(flag.equals("0")){
                        advance_text.setText("Collecting Information...");
                        pending_text.setText("Collecting Information...");
                        s_sam.setVisibility(View.INVISIBLE);
                        stat_img.setVisibility(View.INVISIBLE);
                        txt_status.setVisibility(View.INVISIBLE);



                    }else if(flag.equals("1")){
                        txt_status.setText(message);
                        txt_status.setVisibility(View.VISIBLE);
                        txt_status.startAnimation(blink);
                        if(message.equals("")){
                            stat_img.setImageResource(R.drawable.unlock);
                        }else
                        {
                            stat_img.setImageResource(R.drawable.lock);
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MemberLoginMenu.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("custid", custid);




                return map;
            }
        };
        queue.add(request);

    }



}
