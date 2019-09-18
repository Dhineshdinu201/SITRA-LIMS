package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
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

import pl.droidsonroids.gif.GifImageView;

public class ActivityPaymentMopde extends AppCompatActivity {
GifImageView loading;
ImageView add;
RecyclerView recy_pay_mode;
    Context context=this;
    TextView refresh;
    private List<PaymentModeModel> testNames;
    String cusid;
    ArrayList<String>idlist,paymodelist,payreflist,paydatelist,payvaluelist,payremarkslist,documentcountlist;
    String url = "http://lab.sitraonline.org/index.php/api/get_payment_query_lists";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mopde);
        loading=(GifImageView)findViewById(R.id.loading);
        add=(ImageView)findViewById(R.id.add);
        refresh=(TextView)findViewById(R.id.refresh);
        idlist=new ArrayList<>();
        paymodelist=new ArrayList<>();
        payreflist=new ArrayList<>();
        payvaluelist=new ArrayList<>();
        paydatelist=new ArrayList<>();
        payremarkslist=new ArrayList<>();
        documentcountlist=new ArrayList<>();



        try {
            cusid = getIntent().getStringExtra("cusid");
        }
        catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(this, "try login again and refresh", Toast.LENGTH_SHORT).show();
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityPaymentMopde.this,ActivityAddPaymentMode.class);
                intent.putExtra("cusid",cusid);
                startActivity(intent);
            }
        });
        recy_pay_mode=(RecyclerView)findViewById(R.id.recy_payment_mode);
        recy_pay_mode.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        recy_pay_mode.setLayoutManager(layoutManager);
        Recycler_payment_mode recyclerPaymentMode=new Recycler_payment_mode(this,testNames);

    send();
    refresh.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            send();
        }
    });
    }

    public void send(){

        idlist.clear();
        paymodelist.clear();
        payreflist.clear();
        paydatelist.clear();
        payvaluelist.clear();
        payremarkslist.clear();
        documentcountlist.clear();


        idlist=new ArrayList<>();
        paymodelist=new ArrayList<>();
        payreflist=new ArrayList<>();
        payvaluelist=new ArrayList<>();
        paydatelist=new ArrayList<>();
        payremarkslist=new ArrayList<>();
        documentcountlist=new ArrayList<>();
        testNames=new ArrayList<>();

        testNames.clear();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);
                loading.setVisibility(View.INVISIBLE);


                try {
                    JSONArray js=new JSONArray(response);
                    for(int i=0;i<js.length();i++){
                        JSONObject jo=js.getJSONObject(i);
                        String s_id=jo.getString("id");
                        String s_pay_mode=jo.getString("pay_mode");
                        String s_payt_reference=jo.getString("payt_reference");
                        String s_pay_date =jo.getString("pay_date");
                        String s_pay_value =jo.getString("pay_value");
                        String s_pay_remarks=jo.getString("pay_remarks");
                        String s_document_count=jo.getString("document_count");
                        idlist.add(s_id);
                        paymodelist.add(s_pay_mode);
                        payreflist.add(s_payt_reference);
                        paydatelist.add(s_pay_date);
                        payvaluelist.add(s_pay_value);
                        payremarkslist.add(s_pay_remarks);
                        documentcountlist.add(s_document_count);
                        testNames.add(new PaymentModeModel(paydatelist,paymodelist,payreflist,payvaluelist,idlist,payremarkslist,documentcountlist));



                    }
                    Recycler_payment_mode adapter=new Recycler_payment_mode(context,testNames);
                    recy_pay_mode.setAdapter(adapter);











                } catch (JSONException e) {
                    Toast.makeText(ActivityPaymentMopde.this, "Credentials Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.INVISIBLE);

                Toast.makeText(ActivityPaymentMopde.this, "Please Check Connectivity :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();


                map.put("custid",cusid);




                return map;
            }
        };
        queue.add(request);

    }

    @Override
    protected void onResume() {
        super.onResume();
        send();

    }
}
