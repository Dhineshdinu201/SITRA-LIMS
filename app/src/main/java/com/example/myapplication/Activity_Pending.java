package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class Activity_Pending extends AppCompatActivity {
    Context context=this;
    String stat="0";
    EditText et_search;
    ImageView search,refresh;
    TextView no_record;
    String search_condition="";
    GifImageView loading;
    ArrayList<String>InwardList,testid_list,test_name_list,samplecount_list,status_list,inwardnolist,inwarddate_list,noofsamples_list,stat_value_list,pending_list;
    ArrayList<ArrayList<String>>test_id_listall,testname_list_all,sample_count_list_all,status_list_all,stat_value_list_all;
    String url = " http://lab.sitraonline.org/index.php/api/get_customer_pending_inwards";
    RecyclerView recyclerView_status;
    ArrayList<Vertical_model_pending> mArrayList = new ArrayList<>();
    VerticalRecyclerAdapter_pending mAdapter;
    String cusid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__pending);
        no_record=(TextView)findViewById(R.id.no_record);
        et_search=(EditText)findViewById(R.id.et_search);
        loading=(GifImageView)findViewById(R.id.loading);
        recyclerView_status = (RecyclerView) findViewById(R.id.rv_vertical);

        recyclerView_status.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new VerticalRecyclerAdapter_pending(this, mArrayList);

        recyclerView_status.setAdapter(mAdapter);
        cusid = getIntent().getStringExtra("cusid");
        search=(ImageView)findViewById(R.id.search);
        refresh=(ImageView)findViewById(R.id.refresh);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                search_condition=et_search.getText().toString();
                Log.i("search",search_condition);
                mArrayList.clear();
                send();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_condition="";
                mArrayList.clear();
                send();
            }
        });

send();

}


    public void send(){
        et_search.setText("");
        Log.i("search",search_condition);
        inwardnolist=new ArrayList<>();
        inwarddate_list=new ArrayList<>();
        noofsamples_list=new ArrayList<>();
        InwardList=new ArrayList<>();
        testid_list=new ArrayList<>();
        test_name_list=new ArrayList<>();
        samplecount_list=new ArrayList<>();
        status_list=new ArrayList<>();
        stat_value_list_all=new ArrayList<>();
        pending_list=new ArrayList<>();

        inwardnolist.clear();
        inwarddate_list.clear();
        noofsamples_list.clear();
        InwardList.clear();
        testid_list.clear();
        test_name_list.clear();
        samplecount_list.clear();
        status_list.clear();
        stat_value_list_all.clear();
        test_id_listall=new ArrayList<>();
        testname_list_all=new ArrayList<>();
        sample_count_list_all=new ArrayList<>();
        status_list_all=new ArrayList<>();
        stat_value_list_all=new ArrayList<>();
        pending_list=new ArrayList<>();
        test_id_listall.clear();
        testname_list_all.clear();
        sample_count_list_all.clear();
        status_list_all.clear();
        stat_value_list_all.clear();
        mArrayList.clear();
        InwardList.removeAll(InwardList);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My dhinesh", "" + response);
                loading.setVisibility(View.INVISIBLE);


                try {

                    JSONObject object=new JSONObject(response);
                    JSONArray sample=object.getJSONArray("inward_lists");
                    for(int i=0;i<sample.length();i++){
                        String inward_no=sample.getString(i);
                        Log.i("sample_no",inward_no);
                        InwardList.add(inward_no);
                    }

                    test_id_listall=new ArrayList<>();
                    testid_list.clear();

                    JSONObject inward_test_lists = object.getJSONObject("inward_test_lists");
                    Log.i("inward_test_lists", "*******" + String.valueOf(inward_test_lists));
                    for(int j=0;j<InwardList.size();j++) {
                        testid_list = new ArrayList<>();


                        JSONArray j_testid = inward_test_lists.getJSONArray(InwardList.get(j));
                        Log.i("j_testid", "*******" + String.valueOf(j_testid));

                        for (int k = 0; k < j_testid.length(); k++) {
                            String testid = j_testid.getString(k);
                            Log.i("testid", "" + testid);
                            testid_list.add(testid);
                            Log.i("testidlist", String.valueOf(testid_list));

                        }
                        test_id_listall.add(testid_list);
                        Log.i("testid_list_all", String.valueOf(test_id_listall));
                    }
                    test_name_list.clear();
                    samplecount_list.clear();
                    status_list.clear();
                    testname_list_all=new ArrayList<>();
                    sample_count_list_all=new ArrayList<>();
                    status_list_all=new ArrayList<>();
                    stat_value_list_all=new ArrayList<>();
                JSONObject ob=object.getJSONObject("inward_details");
                    Log.i("inward_details","++++"+ob);
                    for(int i=0;i<InwardList.size();i++){
                        test_name_list=new ArrayList<>();
                        samplecount_list=new ArrayList<>();
                        status_list=new ArrayList<>();
                        stat_value_list=new ArrayList<>();
                        JSONObject inwardno=ob.getJSONObject(InwardList.get(i));
                        String s_inwardno=inwardno.getString("inwardno");
                        String s_inwarddate=inwardno.getString("inward_date");
                        String s_noofsamples=inwardno.getString("noofsamples");
                        String s_pending=inwardno.getString("pending_tests");
                        inwardnolist.add(s_inwardno);
                        inwarddate_list.add(s_inwarddate);
                        noofsamples_list.add(s_noofsamples);
                        pending_list.add(s_pending);
                        JSONObject tests=inwardno.getJSONObject("tests");

                        for(int j=0;j<test_id_listall.get(i).size();j++) {
                            JSONObject j_testid = tests.getJSONObject(test_id_listall.get(i).get(j));
                            String s_testid=j_testid.getString("testid");
                            String s_testname=j_testid.getString("testname");
                            String s_sample_count=j_testid.getString("sample_count");
                            String s_status=j_testid.getString("status");
                            Log.i("s_testid",s_testid);
                            Log.i("s_sample_count",s_sample_count);
                            Log.i("s_status",s_status);
                            Log.i("s_testname",s_testname);
                            test_name_list.add(s_testname);
                            samplecount_list.add(s_sample_count);
                            status_list.add(s_status);
                            JSONObject j_stat=j_testid.getJSONObject("status");
                            try {
                                 stat = j_stat.getString("W");
                                 stat_value_list.add(stat);
                            }catch (JSONException e){
                                e.printStackTrace();
                                stat="0";
                                stat_value_list.add(stat);
                            }

                        }
                        testname_list_all.add(test_name_list);
                        sample_count_list_all.add(samplecount_list);
                        status_list_all.add(status_list);
                        stat_value_list_all.add(stat_value_list);
                        Log.i("testname_list_all","(((((((((("+testname_list_all);
                        Log.i("sample_count_list_all","(((((((((("+sample_count_list_all);
                        Log.i("status_list_all","(((((((((("+status_list_all);
                    }
                    for (int i = 0; i < InwardList.size(); i++) {

                        Vertical_model_pending mVerticalModel = new Vertical_model_pending();

                        mVerticalModel.setTitle(inwardnolist.get(i));
                        mVerticalModel.setDesc(inwarddate_list.get(i));
                        mVerticalModel.setStat(noofsamples_list.get(i));
                        mVerticalModel.setPending(pending_list.get(i));


                        ArrayList<Horizondal_model_pending> arrayList = new ArrayList<>();
                        arrayList.clear();

                        for (int j = 0; j < testname_list_all.get(i).size(); j++) {
                            Horizondal_model_pending mHorizontalModel = new Horizondal_model_pending();
                            mHorizontalModel.setInwardno(inwardnolist.get(i));
                            mHorizontalModel.setTestid(test_id_listall);
                            mHorizontalModel.setPosition(i);
                            mHorizontalModel.setName(testname_list_all.get(i).get(j));
                            mHorizontalModel.setCount(sample_count_list_all.get(i).get(j));
                            mHorizontalModel.setValue(stat_value_list_all.get(i).get(j));

//                            mHorizontalModel.setId(status_list_all.get(i).get(j));
                            if(sample_count_list_all.get(i).get(j).equals(stat_value_list_all.get(i).get(j))){
                                mHorizontalModel.setId("4");
                            }else {
                                mHorizontalModel.setId("2");
                            }
                            arrayList.add(mHorizontalModel);
                        }

                        mVerticalModel.setArrayList(arrayList);

                        mArrayList.add(mVerticalModel);

                    }
                    mAdapter.notifyDataSetChanged();




                } catch (JSONException e) {
                    no_record.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.INVISIBLE);

                Toast.makeText(Activity_Pending.this, "Please Check Connectivity :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                Log.i("search",search_condition);
                map.put("custid",cusid);
                map.put("search_condition",search_condition);

                //search_condition




                return map;
            }
        };
        queue.add(request);

    }
    }
