package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class PendingInwards extends AppCompatActivity {
    //**************************Declaration*******************************************
    EditText et_search;
    Constant constant=new Constant();
    String GET_URL=constant.ip+"get_memberlogin_inwards";
    Button btn_search,refresh,fromm,tooo;
    String from_date="";
    GifImageView loading;
    TextView re;
    String todate="";
    Context context=this;
    String[] from = {""};
    String fro;
    ArrayList<String>inwardno_list,accesscode_list;
    ArrayList<String> date_list,type_list,no_of_samples_list,status_list,Requestno_list;
    String[] current={""};
    String current_date;
    String[] currentt={""};
    String curren_date;
    String[] curren={""};
    String search="";
    String currentt_date,dummy_from="";
    String dummyto="";
    String cusid;
    final String[] too = {""};
    String to="";
    RecyclerView recy_inward;
    private List<PendingInwardModel> testNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_inwards);
        //****************************initialization**************************************

        et_search=(EditText)findViewById(R.id.et_search_pending);
        loading=(GifImageView)findViewById(R.id.loading);
        btn_search=(Button)findViewById(R.id.search);
        refresh=(Button)findViewById(R.id.refresh);
        fromm=(Button)findViewById(R.id.from);
        tooo=(Button)findViewById(R.id.to);
        re=(TextView)findViewById(R.id.re);
        cusid = getIntent().getStringExtra("cusid");
        recy_inward=(RecyclerView)findViewById(R.id.recy_inward);
        recy_inward.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        recy_inward.setLayoutManager(layoutManager);
        RecyclerInwardPending recyclerInwardPending=new RecyclerInwardPending(this,testNames);
        Calendar calendar=Calendar.getInstance();
        curren[0]=""+calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+ calendar.get(Calendar.DAY_OF_MONTH);

        curren_date=curren[0];
        et_search.setSelected(false);
        send();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search=et_search.getText().toString();
                Log.i("et_search",search);
                send();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
                from_date="";
                todate="";
                search="";
                fromm.setText("FROM");
                tooo.setText("TO");
                send();
            }
        });
        fromm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showwdialog_from();
            }
        });
        tooo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from_date=current_date;
                showwdialog_to();
            }
        });




    }


    public void send() {
        testNames=new ArrayList<>();
        testNames.clear();
        testNames.removeAll(testNames);
        inwardno_list=new ArrayList<>();
        accesscode_list=new ArrayList<>();
        date_list=new ArrayList<>();
        type_list=new ArrayList<>();
        no_of_samples_list=new ArrayList<>();
        status_list=new ArrayList<>();
        Requestno_list=new ArrayList<>();
    RequestQueue queue = Volley.newRequestQueue(this);
    StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("My success", "" + response);
            loading.setVisibility(View.INVISIBLE);
            try{
                re.setVisibility(View.INVISIBLE);
                JSONArray input=new JSONArray(response);
                for(int i=0;i<input.length();i++){
                JSONObject object=input.getJSONObject(i);
                String s_inwardno=object.getString("inwardno");
                String s_accessno=object.getString("accessno");
                String s_reqno=object.getString("reqno");
                    String s_noofsamples=object.getString("noofsamples");
                    String s_inward_date=object.getString("inward_date");
                    String s_labid=object.getString("labid");
                    String s_inward_type=object.getString("inward_type");
                    String s_inward_status=object.getString("inward_status");
                    inwardno_list.add(s_inwardno);
                    accesscode_list.add(s_accessno);

                    date_list.add(s_inward_date);
                    type_list.add(s_inward_type);
                    no_of_samples_list.add(s_noofsamples);
                    status_list.add(s_inward_status);
                    Requestno_list.add(s_reqno);
                    testNames.add(new PendingInwardModel( date_list, type_list, no_of_samples_list, status_list, Requestno_list,inwardno_list,accesscode_list));
                    Log.i("date_list", String.valueOf(date_list));
                    Log.i("type_list",String.valueOf(type_list));
                    Log.i("no_of_samples_list",String.valueOf(no_of_samples_list));
                    Log.i("status_list",String.valueOf(status_list));
                    Log.i("Requestno_list",String.valueOf(Requestno_list));


                }
                RecyclerInwardPending recyclerInwardPending=new RecyclerInwardPending(context,testNames);
                recy_inward.setAdapter(recyclerInwardPending);
        } catch (JSONException e) {
                re.setVisibility(View.VISIBLE);
            Toast.makeText(PendingInwards.this, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }

    }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            loading.setVisibility(View.INVISIBLE);
            Toast.makeText(PendingInwards.this, "my error :" + error, Toast.LENGTH_LONG).show();
            Log.i("My error", "" + error);
        }
    }) {
        @Override

        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();
            map.put("custid", cusid);
            map.put("to_date",todate);
            map.put("from_date",from_date);
            map.put("inwardno",search);



            return map;
        }
    };
                queue.add(request);




        }
    public void showwdialog_from(){
        Activity activity = null;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        DatePicker datePicker;

        Button ok,cancel;

        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater factory = LayoutInflater.from(this);
        final View v = factory.inflate(R.layout.alert_date_picker, null);
        datePicker=(DatePicker)v.findViewById(R.id.date_picker);
        ok=(Button)v.findViewById(R.id.ok);
        cancel=(Button)v.findViewById(R.id.cancel);

        Calendar calendar=Calendar.getInstance();
        currentt[0]=""+calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+ calendar.get(Calendar.DAY_OF_MONTH);

        currentt_date=current[0];
        fro=currentt_date;
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int mon=monthOfYear+1;
                from[0] =dayOfMonth+"-"+mon+"-"+year;
                fro=from[0];
                Log.i("from", String.valueOf(from));
                Log.i("fro", fro);

                dummy_from=dayOfMonth+"/"+mon+"/"+year;


            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromm.setText(dummy_from);
                tooo.setText("TO");
                todate="";
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromm.setText("FROM");
                tooo.setText("TO");

                alertDialog.cancel();
            }
        });





        alertDialog.setView(v);
        alertDialog.show();
        alertDialog.setCancelable(true);




    }
    public void showwdialog_to(){
        final Activity activity = null;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        DatePicker datePicker;
        final TextView date;
        Button ok,cancel;
        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater factory = LayoutInflater.from(this);
        final View v = factory.inflate(R.layout.alert_date_picker, null);
        datePicker=(DatePicker)v.findViewById(R.id.date_picker);
        ok=(Button)v.findViewById(R.id.ok);
        cancel=(Button)v.findViewById(R.id.cancel);

        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH)+1;
        current[0]=""+calendar.get(Calendar.DAY_OF_MONTH)+"-"+month+"-"+calendar.get(Calendar.YEAR) ;
        current_date=current[0];
        to=current_date;
        Log.i("currentdate",current_date);

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int mon=monthOfYear+1;
                too[0] =dayOfMonth+"-"+mon+"-"+year;
                 to=too[0];
                Log.i("too", String.valueOf(too));
                Log.i("to", to);
                dummyto=dayOfMonth+"/"+mon+"/"+year;
                try {
                    Daybetween(fro, to, current_date, "dd-mm-yyyy");
                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tooo.setText(dummyto);
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialog.cancel();
            }
        });




        alertDialog.setView(v);
        alertDialog.show();
        alertDialog.setCancelable(false);




    }
    public void Daybetween(String date1,String date2,String date3,String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date Date1 = null,Date2 = null,Date3=null;
        try{
            Date1 = sdf.parse(date1);
            Date2 = sdf.parse(date2);
            Date3 = sdf.parse(date3);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        long val=(Date2.getTime() - Date1.getTime())/(24*60*60*1000);
        long to_val=(Date3.getTime() - Date2.getTime())/(24*60*60*1000);
        long from_val=(Date3.getTime() - Date1.getTime())/(24*60*60*1000);
        long froto=(Date2.getTime()-Date1.getTime())/(24*60*60*1000);
        Log.i("val",""+val);
        Log.i("from_val",""+from_val);
        Log.i("froto",""+froto);

        if(val>90){

            Toast.makeText(this, "please enter the value within 90 days", Toast.LENGTH_LONG).show();
//        }
//        else if(to_val<0||from_val<0){
//            Toast.makeText(this, "please nter the valid date", Toast.LENGTH_LONG).show();
//        } else if(froto<0){
//            Toast.makeText(this, "please enter the valid date", Toast.LENGTH_LONG).show();
        }
        else {
            from_date=date1;
            todate=date2;
            fromm.setText(dummy_from);
            tooo.setText(dummyto);
            send();
        }
    }

}
