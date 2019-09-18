package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class Activity_view_report extends AppCompatActivity {
    //Declaration
    Bundle bundle;
    AppController appController;
    String sampleno;
    String s_tests_id,pdf1_url,pdf2_url,test_standard;
    String s_test_name;
    String type;
    String description;
    ArrayList<String> inner;
    ArrayList<String>inner1;
    ArrayList<ArrayList<String>>outer,outer1;
    String sample_status;
    GifImageView loading;
    ArrayList<VerticalModel> mArrayList = new ArrayList<>();
    String custid;
    private String url_reg = "http://lab.sitraonline.org/index.php/api/validate_device_registration";
    private String url_mob_track = "http://lab.sitraonline.org/index.php/api/mobile_app_tracking";
    String deviceid="";
    String android_id;
    VerticalRecyclerViewAdapter mAdapter;
    TextView inward_no, status, inward_date, num_of_sample,no_record;
    Button btn_view_report,seemore;
    RecyclerView recyclerView_status;
    RecyclerView recy_test_name;
    private List<Tracking_Adapter> clubs;
    private String url;
    ArrayList<String> testname;
    ArrayList<ArrayList<String>> pdf1holder;
    ArrayList<String>pdf2holder;
    ImageView refresh;
    String no_of_samples;
    String inwardno,password;
    EditText et_search;
    ImageView img_search;
    ArrayList<String> sample_list;
    ArrayList<String>testid_list,test_id_list;
    ArrayList<ArrayList<String>>pdf1_url_list_all,pdf2_url_list_all,testname_list_all,test_id_listall,test_standard_list_all;
    ArrayList<String>testname_list,test_standard_list;
    ArrayList<String>pdf1_url_list,sample_type_list,sample_count_list;
    ArrayList<String>pdf2_url_list;
    ArrayList<String>sample_no=new ArrayList<>();
    String dummy,search_text="";

    ArrayList<String>sample_desc=new ArrayList<>();
    private Map <String, ArrayList<String>> product1;
    private Map <String, ArrayList<String>> product2;
    ArrayList<String>sample_status_list=new ArrayList<>();
    // List<TestName>clubss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        refresh=(ImageView)findViewById(R.id.refresh);
        Context context;
        appController=new AppController();
        sendd();
        //initialization

        url = "http://lab.sitraonline.org/index.php/api/getInwardDetails";
        inward_no = (TextView) findViewById(R.id.inward_no);
        status = (TextView) findViewById(R.id.status);
        //seemore=(Button)findViewById(R.id.seemore);
        inward_date = (TextView) findViewById(R.id.inward_date);

        num_of_sample = (TextView) findViewById(R.id.num_of_sample);
        img_search=(ImageView)findViewById(R.id.search);
        et_search=(EditText)findViewById(R.id.et_search);
        no_record=(TextView)findViewById(R.id.no_record);
        loading=(GifImageView)findViewById(R.id.loading);
        pdf1holder=new ArrayList<ArrayList<String>>();
        pdf2holder=new ArrayList<>();


        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add",android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen
        deviceid=android_id;


        //******************************passed datas
//        Bundle bundle = getIntent().getExtras();
        try {
            inwardno = getIntent().getStringExtra("inwardno");
            password = getIntent().getStringExtra("password");
            type=getIntent().getStringExtra("type");
        }catch (NullPointerException e){
            e.printStackTrace();
        }

//        String inwarddate=bundle.getString("inward_date");
//        String inward_status=bundle.getString("inward_status");
//        no_of_samples=bundle.getString("no_of_samples");
//        String last_publish_date=bundle.getString("last_publish_date");
//
//        sample_no=bundle.getStringArrayList("sample_no_list");
//        sample_desc=bundle.getStringArrayList("sample_description");
//
//        sample_status_list=bundle.getStringArrayList("sample_status_list");
//        inward_no.setText(inwardno);
//        inward_date.setText(inwarddate);
//        status.setText(inward_status);
//        publish_date.setText(last_publish_date);
//        num_of_sample.setText(no_of_samples);

//        dummy=appController.getTestname().get(2);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
                search_text="";
                send();
            }
        });
        send();


        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                search_text=et_search.getText().toString();
                send();

            }
        });








        appController = new AppController();
        recyclerView_status = (RecyclerView) findViewById(R.id.recyclerview_status);




        recyclerView_status.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new VerticalRecyclerViewAdapter(this, mArrayList);

        recyclerView_status.setAdapter(mAdapter);

//        setDataOnVerticalRecyclerView();
    }


    //**************************assign data to text view



    private void setDataOnVerticalRecyclerView() {
//        if(sample_no.size()==0){
//            no_record.setVisibility(View.VISIBLE);
//        }
//        Log.i("setDataOnVerticalRecycl",""+sample_no.size());
//        mArrayList.clear();
//
//        for (int i = 0; i <sample_no.size() ; i++) {
//
//            VerticalModel mVerticalModel;
//            mVerticalModel= new VerticalModel();
//
//
//
//            mVerticalModel.setTitle(sample_no.get(i));
//            mVerticalModel.setDesc(sample_desc.get(i));
//            mVerticalModel.setStat(sample_status_list.get(i));
//
//            ArrayList<HorizontalModel> arrayList = new ArrayList<>();
//
//            for (int j = 0; j <= 5; j++) {
//                HorizontalModel mHorizontalModel = new HorizontalModel();
//                mHorizontalModel.setDescription("Description : " + j);
//                mHorizontalModel.setName("Name : " + j);
//            arrayList.add(mHorizontalModel);
//        }
//
//            mVerticalModel.setArrayList(arrayList);
//
//            mArrayList.add(mVerticalModel);
//
//        }
//        mAdapter.notifyDataSetChanged();
    }



    //*******************************json for searched sample***********************************



    public void send(){




        product1 = new <String, ArrayList<String>> HashMap();
        product2 = new <String, ArrayList<String>> HashMap();
        outer=new ArrayList<>();
        inner=new ArrayList<String>();
        outer1=new ArrayList<>();
        inner1=new ArrayList<>();
       sample_no.removeAll(sample_no);
    RequestQueue queue = Volley.newRequestQueue(this);
    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Log.i("My success", "" + response);
            loading.setVisibility(View.INVISIBLE);


            try {



                sample_list=new ArrayList<>();
                testid_list=new ArrayList<>();
                test_id_list=new ArrayList<>();
                test_id_listall=new ArrayList<>();
                pdf1_url_list_all=new ArrayList<>();
                pdf2_url_list_all=new ArrayList<>();
                testname_list=new ArrayList<>();
                testname_list_all=new ArrayList<>();
                pdf1_url_list=new ArrayList<>();
                pdf2_url_list=new ArrayList<>();
                sample_count_list=new ArrayList<>();
                sample_type_list=new ArrayList<>();
                test_standard_list=new ArrayList<>();
                test_standard_list_all=new ArrayList<>();



                testname_list_all.clear();
                test_standard_list_all.clear();
                test_id_listall.clear();
                mArrayList.clear();
                sample_count_list.clear();
                sample_type_list.clear();
                test_standard_list.clear();
                sample_list.clear();
                testid_list.clear();
                test_id_list.clear();
                test_id_listall.clear();
                pdf1_url_list_all.clear();
                pdf2_url_list_all.clear();
                testname_list.clear();
                testname_list_all.clear();
                pdf1_url_list.clear();
                pdf2_url_list.clear();


                //+++++++++++++++++++++++++parsing response*************
                JSONObject object=new JSONObject(response);
                //+++++++++++++++++++++++++parsing common data*************
                JSONObject ob=object.getJSONObject("common_data");
                String inwardno=ob.getString("inwardno");

                String s_inward_date=ob.getString("inward_date");
                String inward_status=ob.getString("inwardstatus");
                String no_of_samples=ob.getString("noofsamples");
                String last_publish_date=ob.getString("last_test_published_date");

        inward_no.setText(inwardno);
        inward_date.setText(s_inward_date);
        status.setText(inward_status);
        num_of_sample.setText(no_of_samples);

//*****************************************parsing json array sample formatted************

                JSONArray sample=ob.getJSONArray("samples_formatted");
                for(int i=0;i<sample.length();i++){
                    String sample_no=sample.getString(i);
                    Log.i("sample_no",sample_no);
                    sample_list.add(sample_no);
                }

//******************************************parsing sample object


                JSONObject obj_sample=object.getJSONObject("samples");
                testid_list.clear();
                test_id_listall=new ArrayList<>();
                for(int i=0;i<obj_sample.length();i++) {

                    testid_list = new ArrayList<>();
                    testname_list = new ArrayList<>();
                    pdf2_url_list = new ArrayList<>();
                    pdf1_url_list = new ArrayList<>();
                    JSONObject obj_sample_data = obj_sample.getJSONObject(sample_list.get(i));

                    JSONObject testno = ob.getJSONObject("test_formatted");
                    Log.i("jsonnnnnnnnn", "" + testno.length());

                    JSONArray sampleobj = testno.getJSONArray(sample_list.get(i));

                    Log.i("testid", "" + sampleobj.length());

                    for (int j = 0; j < sampleobj.length(); j++) {
                        String testid = sampleobj.getString(j);
                        Log.i("testid", "" + testid);
                        testid_list.add(testid);
                        Log.i("testidlist", String.valueOf(testid_list));

                    }
                    test_id_listall.add(testid_list);
                    Log.i("testid_list_all", String.valueOf(test_id_listall));


                    sampleno = obj_sample_data.getString("sampleno");
                    Log.i("sampleno", sampleno);
                    sample_no.add(sampleno);
                    description = obj_sample_data.getString("description");
                    Log.i("description", description);
                    sample_desc.add(description);
                    sample_status = obj_sample_data.getString("sample_status");
                    Log.i("sample_status", sample_status);
                    sample_status_list.add(sample_status);
                    String sample_count=obj_sample_data.getString("sample_count");
                    String sample_type=obj_sample_data.getString("sample_type");
                    sample_count_list.add(sample_count);
                    sample_type_list.add(sample_type);
                    JSONObject objtest = obj_sample_data.getJSONObject("tests");

                    for (int k = 0; k < testid_list.size(); k++) {
                        //*******************testis and name parsing******************
                        JSONObject objtestid = objtest.getJSONObject(test_id_listall.get(i).get(k));
                        s_tests_id = objtestid.getString("testid");
                        Log.i("testid", s_tests_id);
                        s_test_name = objtestid.getString("testname");
                        Log.i("s_test_name", s_test_name);
                        testname_list.add(s_test_name);
                        test_standard=objtestid.getString("test_standards");
                        test_standard_list.add(test_standard);
                        Log.i("testname_list", String.valueOf(testname_list));
                        pdf1_url = objtestid.getString("pdf1");
//
                        Log.i("pdf1_url", "pf" + pdf1_url);
                        pdf1_url_list.add(pdf1_url);


                        pdf2_url = objtestid.getString("pdf2");
                        Log.i("pdf2_url", "pf" + pdf2_url);
                        pdf2_url_list.add(pdf2_url);


                    }
                    testname_list_all.add(testname_list);
                    Log.i("testname_listall", String.valueOf(testname_list_all));
                    test_standard_list_all.add(test_standard_list);
                    pdf1_url_list_all.add(pdf1_url_list);
                    pdf2_url_list_all.add(pdf2_url_list);
                    Log.i("pdf1_url_list_all", String.valueOf(pdf1_url_list_all));
                    Log.i("pdf2_url_list_all", String.valueOf(pdf2_url_list_all));

                }
                    for (int d = 0; d < sample_no.size(); d++) {

                        VerticalModel mVerticalModel;
                        mVerticalModel = new VerticalModel();


                        mVerticalModel.setTitle(sample_no.get(d));
                        mVerticalModel.setSampleCount(sample_count_list.get(d));
                        mVerticalModel.setSampleType(sample_type_list.get(d));
                        mVerticalModel.setDesc(sample_desc.get(d));
                        mVerticalModel.setStat(sample_status_list.get(d));

                        ArrayList<HorizontalModel> arrayList = new ArrayList<>();
                        arrayList.clear();

                        for (int h = 0; h < testname_list_all.get(d).size(); h++) {
                            HorizontalModel mHorizontalModel = new HorizontalModel();
                            mHorizontalModel.setTestname(testname_list_all);
                            mHorizontalModel.settest_standard(test_standard_list_all);
                            mHorizontalModel.setPdf1(pdf1_url_list_all);
                            mHorizontalModel.setPdf2(pdf2_url_list_all);
                            mHorizontalModel.setTestid(test_id_listall);
                            mHorizontalModel.setPosition(d);
                            mHorizontalModel.setSampleno(sample_list);
                            mHorizontalModel.setDescription(description);
                            mHorizontalModel.setInwardno(inwardno);
                            mHorizontalModel.setS_testname(testname_list_all.get(d).get(h));
                            arrayList.add(mHorizontalModel);
                        }


                        mVerticalModel.setArrayList(arrayList);

                        mArrayList.add(mVerticalModel);

                    }
                    mAdapter.notifyDataSetChanged();



//               JSONObject obj_testname=obj_sample_data.getJSONObject("")


                Log.i("inwardno",inwardno);
                Log.i("inward_date",s_inward_date);
                Log.i("inward_status",inward_status);
                Log.i("no_of_samples",no_of_samples);
                Log.i("last_publish_date",last_publish_date);



                } catch (JSONException e) {
                Toast.makeText(Activity_view_report.this, "Credentials Error", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            loading.setVisibility(View.INVISIBLE);

            Toast.makeText(Activity_view_report.this, "Please Check Connectivity :" + error, Toast.LENGTH_LONG).show();
            Log.i("My error", "" + error);
        }
    }) {
        @Override

        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();

            map.put("condition",search_text);
            map.put("inwardno",inwardno);
            map.put("accesscode",password);
            map.put("type",type);




            return map;
        }
    };
    queue.add(request);

}

    @Override
    public void onBackPressed() {

        if(type.equals("1")){
            super.onBackPressed();

        }else {
            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Logout");
            builder.setMessage("Are you want to logout?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendd_track();
                    Intent intent=new Intent(Activity_view_report.this,HomeScreen.class);
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

                Toast.makeText(Activity_view_report.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Activity_view_report.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("device_id", deviceid);
                map.put("custid", custid);
                map.put("message", "view report logout");
                map.put("input_values", "Inward no : "+""+" accesscode : "+"");
                Log.i("device id",deviceid);




                return map;
            }
        };
        queue.add(request);


    }


}





