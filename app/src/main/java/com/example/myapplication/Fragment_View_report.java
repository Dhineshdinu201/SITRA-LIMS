package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Iterator;
import java.util.Map;

public class Fragment_View_report  extends Fragment {
//Decalaration

    EditText et_userid, et_password;
    Button Login, forgot_password, cancel;
    String userid, password;
    private RequestQueue mRequestQueue;
    private String url_reg = "http://lab.sitraonline.org/index.php/api/validate_device_registration";
    private StringRequest mStringRequest;
    private String url_mob_track = "http://lab.sitraonline.org/index.php/api/mobile_app_tracking";
    Bundle bundle = new Bundle();
    TextView aspr, cusmob, cusemail, aspi, inmob, inemail;
    ArrayList<String> sample_list, sample_no_list, sample_desc_list, sample_status_list;
    private String url = "http://lab.sitraonline.org/index.php/api/getInwardDetails";
    private String url_mail = "http://lab.sitraonline.org/index.php/api/check_inward_mail_phone";
    ArrayList<String> list;
    String deviceid = "";
    String custid;
    String android_id;


    @Override
    @Nullable
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancetate) {
        View view = inflater.inflate(R.layout.fragment_view_report, container, false);
        //initialization
        et_userid = (EditText) view.findViewById(R.id.et_sample);
        et_password = (EditText) view.findViewById(R.id.et_accesscode);
        Login = (Button) view.findViewById(R.id.btn_submit);
        cancel = (Button) view.findViewById(R.id.btn_cancel);
        forgot_password = (Button) view.findViewById(R.id.btn_reg_email);
        aspr = (TextView) view.findViewById(R.id.aspr);
        cusmob = (TextView) view.findViewById(R.id.cus_mob);
        cusemail = (TextView) view.findViewById(R.id.cus_email);
        aspi = (TextView) view.findViewById(R.id.aspi);
        inmob = (TextView) view.findViewById(R.id.in_mob);
        inemail = (TextView) view.findViewById(R.id.in_email);
        sample_list = new ArrayList<>();
        sample_no_list = new ArrayList<>();
        sample_desc_list = new ArrayList<>();
        sample_status_list = new ArrayList<>();
        sendd();

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aspr.setVisibility(View.VISIBLE);
                cusmob.setVisibility(View.VISIBLE);
                cusemail.setVisibility(View.VISIBLE);
                aspi.setVisibility(View.VISIBLE);
                inmob.setVisibility(View.VISIBLE);
                inemail.setVisibility(View.VISIBLE);
                send();
            }
        });
        WifiManager manager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add", android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen
        deviceid = android_id;

        senddd();


        //login click listener


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendd_track();
                sample_list.clear();
                userid = et_userid.getText().toString();
                password = et_password.getText().toString();
                Log.i("userid", userid);
                Log.i("password", password);
                if (userid.equals("") || password.equals("")) {
                    Toast.makeText(getActivity(), "Please enter valid credentials", Toast.LENGTH_SHORT).show();
                } else {


                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.i("My success", "" + response);


                            try {
                                //************parsing response object************
                                JSONObject object = new JSONObject(response);
                                //****************parsing common data*************
                                JSONObject ob = object.getJSONObject("common_data");
                                String inwardno = ob.getString("inwardno");
                                String inward_date = ob.getString("inward_date");
                                String inward_status = ob.getString("inwardstatus");
                                String no_of_samples = ob.getString("noofsamples");
                                String last_publish_date = ob.getString("last_test_published_date");


                                //********************parsing sample formatted array**********
                                JSONArray sample = ob.getJSONArray("samples_formatted");
                                for (int i = 0; i < sample.length(); i++) {
                                    String sample_no = sample.getString(i);
                                    Log.i("sample_no", sample_no);
                                    sample_list.add(sample_no);
                                }


//****************************************************parsing sample object***************
                                JSONObject obj_sample = object.getJSONObject("samples");

                                for (int i = 0; i < obj_sample.length(); i++) {
                                    //******************parsing sample list********************
                                    JSONObject obj_sample_data = obj_sample.getJSONObject(sample_list.get(i));


                                    String sampleno = obj_sample_data.getString("sampleno");
                                    Log.i("sampleno", sampleno);
                                    sample_no_list.add(sampleno);
                                    String description = obj_sample_data.getString("description");
                                    Log.i("description", description);
                                    sample_desc_list.add(description);
                                    String sample_status = obj_sample_data.getString("sample_status");
                                    Log.i("sample_status", sample_status);
                                    sample_status_list.add(sample_status);
//                                JSONObject obj_testname=obj_sample_data.getJSONObject("")

                                }


//*************************************************parsing bundle to activity report**************
                                Log.i("inwardno", inwardno);
                                Log.i("inward_date", inward_date);
                                Log.i("inward_status", inward_status);
                                Log.i("no_of_samples", no_of_samples);
                                Log.i("last_publish_date", last_publish_date);


                                Intent intent = new Intent(getContext(), Activity_view_report.class);
                                bundle.putString("inwardno", inwardno);
                                bundle.putString("inward_date", inward_date);
                                bundle.putString("inward_status", inward_status);
                                bundle.putString("no_of_samples", no_of_samples);
                                bundle.putString("password", password);
                                intent.putExtra("inwardno", userid);
                                intent.putExtra("password", password);
                                intent.putExtra("type", "0");
                                bundle.putString("last_publish_date", last_publish_date);
                                bundle.putStringArrayList("sample_no_list", sample_no_list);
                                bundle.putStringArrayList("sample_description", sample_desc_list);
                                bundle.putStringArrayList("sample_status_list", sample_status_list);
                                intent.putExtras(bundle);
                                startActivity(intent);


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), "Please enter valid credentials", Toast.LENGTH_SHORT).show();
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
                            map.put("inwardno", userid);
                            map.put("accesscode", password);
                            map.put("condition", "");


                            return map;
                        }
                    };
                    queue.add(request);

                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeScreen.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void send() {
        userid = et_userid.getText().toString();
        password = et_password.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, url_mail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);


                try {
                    //************parsing response object************
                    JSONObject object = new JSONObject(response);
                    //****************parsing common data*************
                    String inward_mobileno = object.getString("inward_mobileno");
                    String inward_email = object.getString("inward_email");
                    String customer_mobileno = object.getString("customer_mobileno");
                    String customer_email = object.getString("customer_email");


                    cusmob.setText("Customer Mobile : " + customer_mobileno);
                    cusemail.setText("Customer Email : " + customer_email);
                    inmob.setText("Inaward Mobile : " + inward_mobileno);
                    inemail.setText("Inward email : " + inward_email);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Please enter valid credentials", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Please check connectivity", Toast.LENGTH_SHORT).show();
                Log.i("My error", "" + error);

                cusmob.setText("Customer Mobile : NA");
                cusemail.setText("Customer Email : NA");
                inmob.setText("Inaward Mobile :NA");
                inemail.setText("Inward email :NA");
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("inwardno", userid);
                map.put("accesscode", password);


                return map;
            }
        };
        queue.add(request);

    }

    public void senddd() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST,url_reg , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);


                try {
                    //************parsing response object************
                    JSONArray ja = new JSONArray(response);

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
                Log.i("device id", deviceid);

                return map;
            }
        };
        queue.add(request);


    }

    public void sendd_track() {
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
                map.put("message", "view report logged in");
                map.put("input_values", "userid : " + userid + " Password : " + password);
                Log.i("device id", deviceid);
                Log.i("custid id", custid);


                return map;
            }
        };
        queue.add(request);


    }

    public void sendd() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, url_reg, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);


                try {
                    //************parsing response object************
                    JSONArray ja = new JSONArray(response);

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
                Log.i("device id", deviceid);

                return map;
            }
        };
        queue.add(request);

    }
}
