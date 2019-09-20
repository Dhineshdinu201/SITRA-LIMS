package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class ActivityMyProfile extends AppCompatActivity {
String userid,password;
GifImageView loading;
TextView name,address_1,address_2,txt_city,txt_state,txt_country,txt_pincode,txt_email,txt_phone,txt_mobile;
Constant constant=new Constant();
    String GET_URL=constant.ip+"authendicate_member_login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        name=(TextView)findViewById(R.id.name);
        address_1=(TextView)findViewById(R.id.address1);
        address_2=(TextView)findViewById(R.id.address2);
        txt_city=(TextView)findViewById(R.id.city);
        txt_state=(TextView)findViewById(R.id.state);
        txt_country=(TextView)findViewById(R.id.country);
        txt_pincode=(TextView)findViewById(R.id.pincode);
        txt_email=(TextView)findViewById(R.id.email);
        txt_phone=(TextView)findViewById(R.id.phone);
        txt_mobile=(TextView)findViewById(R.id.mobile);
        loading=(GifImageView)findViewById(R.id.loading);




        try {
            userid = getIntent().getStringExtra("userid");
            password = getIntent().getStringExtra("password");
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);
                loading.setVisibility(View.INVISIBLE);
                try {
                    JSONObject input=new JSONObject(response);
                    String cusid=input.getString("custid");
                    String cus_name=input.getString("name");
                    String address1=input.getString("address1");
                    String address2=input.getString("address2");
                    String city=input.getString("city");
                    String state=input.getString("state");
                    String country=input.getString("country");
                    String pincode=input.getString("pincode");
                    String email=input.getString("email");
                    String phone=input.getString("phone");
                    String mobile=input.getString("mobile");
                    String cusumer_advance=input.getString("customer_advance");
                    String customer_pending=input.getString("customer_pending");
                    name.setText("  "+cus_name);
                    address_1.setText("  "+address1);
                    address_2.setText("  "+address2);
                    txt_city.setText("  "+city);
                    txt_state.setText("  "+state);
                    txt_country.setText("  "+country);
                    txt_pincode.setText("  "+pincode);
                    txt_email.setText("  "+email);
                    txt_phone.setText("  "+phone);
                    txt_mobile.setText("  "+mobile);

                } catch (JSONException e) {
                    Toast.makeText(ActivityMyProfile.this, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.INVISIBLE);


                Toast.makeText(ActivityMyProfile.this, "my error :" + error, Toast.LENGTH_LONG).show();
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
}
