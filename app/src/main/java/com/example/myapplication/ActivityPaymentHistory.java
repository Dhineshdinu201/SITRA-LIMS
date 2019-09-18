package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class
ActivityPaymentHistory extends AppCompatActivity {
    RecyclerView recy_payment_his;
    String cusid;
    String GET_URL="http://lab.sitraonline.org/index.php/api/onlinepayment_history";
    private List<PaymentHistoryModel> paymentHistoryModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        try {
            cusid = getIntent().getStringExtra("cusid");
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        recy_payment_his=(RecyclerView)findViewById(R.id.recy_payment_his);
        recy_payment_his.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recy_payment_his.setLayoutManager(layoutManager);


        send();

    }



    public void send(){

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", "" + response);
                try {
                    String payment_against;
                    String payment_mode;
                    String tpsl_transcation_id;
                    String transcation_date,remarks;
                    String res_txn_msg;
                    String res_txn_amt;
                    int result;
                    JSONArray input=new JSONArray(response);
                    for(int i=0;i<input.length();i++){
                        JSONObject ob=input.getJSONObject(i);
                        Log.i("ob", String.valueOf(ob));
                        payment_against=ob.getString("payment_against_type");
                         payment_mode=ob.getString("payment_mode");
                         remarks=ob.getString("billnos_remakrs");
                         tpsl_transcation_id=ob.getString("tpsl_transcation_id");
                         transcation_date=ob.getString("transcation_date");
                       res_txn_amt=ob.getString("res_txn_amt");
                         res_txn_msg=ob.getString("res_txn_msg");
                         Log.i("strings","************"+payment_against+tpsl_transcation_id+transcation_date+res_txn_amt+res_txn_msg);
                         if(res_txn_msg.equals("success"

                         )){
                             result=R.drawable.completed;
                         }else {result=R.drawable.wrong;}

                        paymentHistoryModels=new ArrayList<>();
                        paymentHistoryModels.add(new PaymentHistoryModel("Ref No : "+tpsl_transcation_id, "Date : "+transcation_date, result, "Payment against type : "+payment_against, "Amount : "+res_txn_amt,remarks));


                    }
                    if(input.length()==0) {
                        Toast.makeText(ActivityPaymentHistory.this, "No Record Found", Toast.LENGTH_SHORT).show();

                    }else {
                        RecyclerPaymentHistory recyclerInwardPending = new RecyclerPaymentHistory(paymentHistoryModels);
                        recy_payment_his.setAdapter(recyclerInwardPending);
                    }


                } catch (JSONException e) {
                    Toast.makeText(ActivityPaymentHistory.this, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityPaymentHistory.this,"my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("custid", cusid);



                return map;
            }
        };
        queue.add(request);

    }
}
