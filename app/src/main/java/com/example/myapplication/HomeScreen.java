package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {
    //*****************Declaration*******************
    ImageView img_testing,img_test_fac,img_viewreport,img_memberlogin,img_payment,img_contact;
    EditText et_sammple,et_accesscode;
    String sample,accesscode;
    Button submit;
    ArrayList<String>id_list,message_list;
    TextView notification,rateus;
    ArrayAdapter arrayAdapter;
    ArrayList<String>doc_name;
    int count=0;
    Animation animblink;

    String type="0";
    String custid;
    Context context=this;
    Constant constant=new Constant();
    private String url_reg = constant.ip+"validate_device_registration";
    private String url = constant.ip+"getInwardDetails";
    private String url_announcement = constant.ip+"get_announcement_lists";
    private String url_mob_track = constant.ip+"mobile_app_tracking";
    Bundle bundle = new Bundle();
    String deviceid="";
    String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        showRegister();
        sendd();

        //**************************initialization*****************************
        img_testing = (ImageView) findViewById(R.id.test_available);
        img_test_fac = (ImageView) findViewById(R.id.img_test_fac);
        img_viewreport = (ImageView) findViewById(R.id.img_view_report);
        img_memberlogin = (ImageView) findViewById(R.id.img_member);
        img_payment = (ImageView) findViewById(R.id.img_payment);
        img_contact = (ImageView) findViewById(R.id.img_contact);
        et_sammple=(EditText)findViewById(R.id.et_sample);
        et_accesscode=(EditText)findViewById(R.id.et_accesscode);
        submit=(Button)findViewById(R.id.btn_submit);
        notification=(TextView)findViewById(R.id.notification);
        rateus=(TextView)findViewById(R.id.rateus);
        animblink= AnimationUtils.loadAnimation(this,R.anim.blink);
        rateus.startAnimation(animblink);






        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add",android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen
        deviceid=android_id;
        sendd();





        notification.setText(""+count);

        //***********************************img_testing************************
        img_testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, Activity_testing.class);
                startActivity(intent);
                Log.i("123", "123");
            }
        });
        //**************************************************************************


        //***********************************img_test_fac************************
        img_test_fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, TestingFacility.class);
                startActivity(intent);
                Log.i("123", "123");
            }
        });
        //**************************************************************************



        //***********************************img_viewreport************************
        img_viewreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, home2.class);
                bundle.putString("position", "5");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");
                startActivity(intent);
                Log.i("123", "123");
            }
        });
        //**************************************************************************



        //***********************************img_memberlogin************************
        img_memberlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, home2.class);
                bundle.putString("position", "6");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");
                startActivity(intent);
                Log.i("123", "123");
            }
        });
        //**************************************************************************



        //***********************************img_payment************************
        img_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, home2.class);
                bundle.putString("position", "8");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");
            }
        });
        //**************************************************************************




        //***********************************img_contact************************
        img_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, home2.class);
                bundle.putString("position", "7");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");
                startActivity(intent);
                Log.i("123", "123");
            }
        });
        //**************************************************************************




        //***********************************img_contact************************
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendd_track();
                sample=et_sammple.getText().toString();
                accesscode=et_accesscode.getText().toString();

                if(sample.isEmpty()||accesscode.isEmpty()){
                    Toast.makeText(HomeScreen.this, "Please enter the valid data", Toast.LENGTH_SHORT).show();
                }else {

                    RequestQueue queue = Volley.newRequestQueue(context);
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.i("My success", "" + response);


                            try {
                                //************parsing response object************
                                JSONObject object = new JSONObject(response);

                                JSONObject ob = object.getJSONObject("common_data");
                                String inwardno = ob.getString("inwardno");
                                String inward_date = ob.getString("inward_date");
                                String inward_status = ob.getString("inwardstatus");
                                String no_of_samples = ob.getString("noofsamples");
                                String last_publish_date = ob.getString("last_test_published_date");

                                //****************parsing common data*************
                                Intent intent=new Intent(HomeScreen.this,Activity_view_report.class);
                                intent.putExtra("inwardno",sample);
                                intent.putExtra("password",accesscode);
                                intent.putExtra("type","0");
                                startActivity(intent);

                                Log.i("sample no:----",sample);

                                Log.i("accesscode:----",accesscode);



                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(HomeScreen.this, "Please enter valid credentials", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(HomeScreen.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
                            Log.i("My error", "" + error);
                        }
                    }) {
                        @Override

                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("inwardno", sample);
                            map.put("accesscode", accesscode);
                            map.put("condition","");
                            map.put("type",type);


                            return map;
                        }
                    };
                    queue.add(request);




                    et_sammple.setText("");
                    et_accesscode.setText("");



                }
            }
        });
        //**************************************************************************

    }
    public void showRegister(){
        Activity activity = null;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        Button close;

        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater factory = LayoutInflater.from(this);
        final View vi = factory.inflate(R.layout.alert_register, null);

        Button register=(Button)vi.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this,Register.class);
                startActivity(intent);
            }
        });






        alertDialog.setView(vi);
        alertDialog.show();
        alertDialog.setCancelable(false);


    }

    @Override
    public void onBackPressed(){
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

    }

    public void send(){

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url_announcement, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);


                try {
                    id_list=new ArrayList<>();
                    message_list=new ArrayList<>();
                    //************parsing response object************
                    JSONArray ja=new JSONArray(response);
                    count=ja.length();
                    notification.setText(""+count);
                    for(int i=0;i<ja.length();i++) {

                        JSONObject ob = ja.getJSONObject(i);
                        String id = ob.getString("id");
                        String message = ob.getString("message");
                        id_list.add(id);
                        message_list.add(message);

                        Log.i("sample no:----",id);

                        Log.i("accesscode:----",message);

                    }


                        //****************parsing common data*************
                        Log.i("mmm","++"+message_list);
                    notification.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Activity activity = null;
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                            ListView a_listview ;
                            TextView a_head;

                            final AlertDialog alertDialog = dialogBuilder.create();
                            LayoutInflater factory = LayoutInflater.from(context);
                            final View vi = factory.inflate(R.layout.alert_payment_mode_image, null);
                            String[] dept={"asd","asd","asd","asd","asd","asd","asd","asd","asd","asd","asd"};

                            a_listview=(ListView)vi.findViewById(R.id.list_item);
                            a_head=(TextView)vi.findViewById(R.id.head);
                            a_head.setText("Announcements");
                            Log.i("mmm","++"+message_list);
                            arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, message_list );
                            a_listview.setAdapter(arrayAdapter);






                            alertDialog.setView(vi);
                            alertDialog.show();
                            alertDialog.setCancelable(true);



                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(HomeScreen.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(HomeScreen.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
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
                    send();


                    //****************parsing common data*************




                } catch (JSONException e) {
                    e.printStackTrace();


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(HomeScreen.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(HomeScreen.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("device_id", deviceid);
                map.put("custid", custid);
                map.put("message", "Inward loged in");
                map.put("input_values", "Inward no : "+sample+" accesscode : "+accesscode);
                Log.i("device id",deviceid);




                return map;
            }
        };
        queue.add(request);


    }
    }


