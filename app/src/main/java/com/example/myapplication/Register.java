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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class Register extends AppCompatActivity {
    EditText org,addr1,addr2,city,state,pincode,email,mobile,a_mobile,con_per_name;
    TextView deviceId;
    Button register;
    Spinner org_type;
    String deviceid="";
    String android_id;
    String str_org_type;
    Constant constant=new Constant();
    String GET_URL=constant.ip+"app_sitralims_device_registration";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        org_type=(Spinner)findViewById(R.id.org_type);
        org=(EditText)findViewById(R.id.org);
        addr1=(EditText)findViewById(R.id.add1);
        addr2=(EditText)findViewById(R.id.addr2);
        city=(EditText)findViewById(R.id.city);
        state=(EditText)findViewById(R.id.state);
        pincode=(EditText)findViewById(R.id.pincode);
        email=(EditText)findViewById(R.id.email);
        mobile=(EditText)findViewById(R.id.number);
        a_mobile=(EditText)findViewById(R.id.a_number);
        con_per_name=(EditText)findViewById(R.id.Con_per_name);
        register=(Button)findViewById(R.id.register);
        deviceId=(TextView)findViewById(R.id.deviceid);
        String[]a_org_type={"Non-Member","Member","TSC","Others"};
        ArrayAdapter dataAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,a_org_type);
        org_type.setAdapter(dataAdapter);
        org_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        str_org_type="1";
                        break;
                    case 1:
                        str_org_type="2";
                        break;
                    case 2:
                        str_org_type="3";
                        break;
                    case 3:
                        str_org_type="4";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add",android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen
        deviceid=android_id;
        deviceId.setText(deviceid);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(org.getText().toString().isEmpty()&&con_per_name.getText().toString().isEmpty()&&a_mobile.getText().toString().isEmpty()&&email.getText().toString().isEmpty()&&pincode.getText().toString().isEmpty()&&state.getText().toString().isEmpty()&&city.getText().toString().isEmpty()&&addr1.getText().toString().isEmpty()&&addr2.getText().toString().isEmpty()&&mobile.getText().toString().isEmpty()){

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
                    String status=js.getString("status");
                    String err_msg=js.getString("err_msg");
                    if(status.equals(3)){
                        Toast.makeText(Register.this, "Device Registered. Please wait for approval", Toast.LENGTH_SHORT).show();
                    }
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
                map.put("reg_type",str_org_type);
                map.put("mill_organization_name",org.getText().toString());
                map.put("address1",addr1.getText().toString());
                map.put("address2",addr2.getText().toString());
                map.put("city",city.getText().toString());
                map.put("state",state.getText().toString());
                map.put("pincode",pincode.getText().toString());
                map.put("email",email.getText().toString());
                map.put("mobile_no",mobile.getText().toString());
                map.put("alternative_contact_no",a_mobile.getText().toString());
                map.put("contact_person_name",con_per_name.getText().toString());
                map.put("mobile_device_id",deviceid);
                Log.i("reg_type",str_org_type);
                Log.i("mill_organization_name",org.getText().toString());
                Log.i("address1",addr1.getText().toString());
                Log.i("address2",addr2.getText().toString());
                Log.i("city",city.getText().toString());
                Log.i("state",state.getText().toString());
                Log.i("pincode",pincode.getText().toString());
                Log.i("email",email.getText().toString());
                Log.i("mobile_no",mobile.getText().toString());
                Log.i("alternative_contact_no",a_mobile.getText().toString());
                Log.i("contact_person_name",con_per_name.getText().toString());
                Log.i("mobile_device_id",deviceid);

                return map;
            }
        };
        queue.add(request);

    }
}

