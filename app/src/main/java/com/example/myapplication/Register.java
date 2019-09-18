package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText name,org,mobile,email,password,country;
    Button register;
    String deviceid="";
    String android_id;
    String GET_URL="http://lab.sitraonline.org/index.php/api/app_registration";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.name);
        org=(EditText)findViewById(R.id.org);
        mobile=(EditText)findViewById(R.id.number);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        country=(EditText)findViewById(R.id.Country);
        register=(Button)findViewById(R.id.register);

        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add",android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen
        deviceid=android_id;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()&&org.getText().toString().isEmpty()&&mobile.getText().toString().isEmpty()&&password.getText().toString().isEmpty()&&country.getText().toString().isEmpty()){

                }else {
                    api();
                }
            }
        });

    }
    public void api(){
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);


                try {
                    //will receive id when the register is success
                    JSONObject js=new JSONObject(response);
                    Boolean status=js.getBoolean("status");
                    String id=js.getString("id");
                    //************parsing response object**********
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Register.this, "Somewhere went wrong", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Register.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                //send your params here
                map.put("type", "0");
                map.put("mobile_device_id", deviceid);
                map.put("name", name.getText().toString());
                map.put("organization_millname", org.getText().toString());
                map.put("mobileno", mobile.getText().toString());
                map.put("emailid", email.getText().toString());

                return map;
            }
        };
        queue.add(request);

    }
}

