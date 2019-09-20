package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;



public class HorizontalRecyclerViewAdapter extends
        RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalViewHolder> {
    Constant constant=new Constant();
    String GET_URL=constant.ip+"get_inward_test_results";
    private Context mContext;

    private ArrayList<HorizontalModel> mArrayList;
    ArrayList<String>param_list;
    ArrayList<String>para_name=new ArrayList<>();
    ArrayList<String>results=new ArrayList<>();
    String s_testname="";
    String s_result_status="";
    String s_testcompleteddate="";
    Bundle bundle = new Bundle();
    String s_publish_date="";
    String sample_count="";
    String result_comment="";
    String description="";

    public HorizontalRecyclerViewAdapter(Context mContext,ArrayList<HorizontalModel> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, final int position) {
        final HorizontalModel current = mArrayList.get(position);

            holder.txtTitle.setText(current.getS_testname());




        if(current.getPdf1().get(current.getPosition()).get(position).equals("NA")){
            holder.pdf_one.setVisibility(View.GONE);
        }else holder.pdf_one.setVisibility(View.VISIBLE);
        if(current.getPdf2().get(current.getPosition()).get(position).equals("NA")){
            holder.pdf_two.setVisibility(View.GONE);
        }else holder.pdf_two.setVisibility(View.VISIBLE);




        holder.view_image.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     param_list=new ArrayList<>();
                                                     para_name=new ArrayList<>();
                                                     results=new ArrayList<>();
                                                     RequestQueue queue = Volley.newRequestQueue(mContext);
                                                     StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
                                                         @Override
                                                         public void onResponse(String response) {
                                                             s_testname=null;
                                                             s_publish_date=null;
                                                             s_result_status=null;
                                                             s_testcompleteddate=null;
                                                             s_publish_date=null;
                                                             param_list.clear();
                                                             para_name.clear();
                                                             results.clear();


                                                             Log.i("My success", response);
                                                             try {
                                                                 JSONObject jo = new JSONObject(response);
                                                                 JSONObject jsonObject = jo.getJSONObject("header");
                                                                 String s_descriptive = jsonObject.getString("descriptive_type");
                                                                 if (s_descriptive.equals("N")) {
                                                                      result_comment = jsonObject.getString("result_comments");
                                                                     sample_count = jsonObject.getString("sample_count");
                                                                     s_testname = jsonObject.getString("testname");

                                                                     Log.i("testname", s_testname);
                                                                     s_result_status = jsonObject.getString("result_status");
                                                                     Log.i("s_result_status", s_result_status);
                                                                     s_testcompleteddate = jsonObject.getString("testcompleteddate");
                                                                     Log.i("s_testcompleteddate", s_testcompleteddate);
                                                                     s_publish_date = jsonObject.getString("publish_date");
                                                                     Log.i("s_publish_date", s_publish_date);
                                                                     JSONArray jsonArray = jo.getJSONArray("parameter_lists");
                                                                     for (int i = 0; i < jsonArray.length(); i++) {
                                                                         String parameter_list = jsonArray.getString(i);
                                                                         param_list.add(parameter_list);

                                                                     }
                                                                     JSONObject obj_results = jo.getJSONObject("results");
                                                                     for (int j = 0; j < param_list.size(); j++) {
                                                                         JSONObject obj_re_num = obj_results.getJSONObject(param_list.get(j));
                                                                         String s_para_name = obj_re_num.getString("para_name");
                                                                         para_name.add(s_para_name);
                                                                         Log.i("s_para_name", s_para_name);
                                                                         String s_result = obj_re_num.getString("result");
                                                                         results.add(s_result);
                                                                         Log.i("s_result", s_result);

                                                                     }

                                                                     showwdialog(current.getSampleno().get(current.getPosition()),current.gettest_standard().get(current.getPosition()).get(position));

                                                                 }else {
                                                                     String html = jsonObject.getString("results");
                                                                     Log.i("html",html);
                                                                     Intent intent=new Intent(mContext,WebView_pdf.class);
                                                                     intent.putExtra("html",html);
                                                                     mContext.startActivity(intent);

                                                                 }
                                                                 } catch(JSONException e){
                                                                     e.printStackTrace();
                                                                     Toast.makeText(mContext, "No Result Found", Toast.LENGTH_SHORT).show();
                                                                 }


                                                         }
                                                     }, new Response.ErrorListener() {
                                                         @Override
                                                         public void onErrorResponse(VolleyError error) {

                                                             Toast.makeText(mContext, "No Network Connection", Toast.LENGTH_LONG).show();
                                                             Log.i("My error", "" + error);
                                                         }
                                                     }) {
                                                         @Override

                                                         protected Map<String, String> getParams() throws AuthFailureError {
                                                             Map<String, String> map = new HashMap<String, String>();
                                                             Log.i("position",""+current.getPosition());
                                                             Log.i("innerpos",""+position);
                                                             Log.i("testid", String.valueOf(current.getTestid()));
                                                             map.put("testid", current.getTestid().get(current.getPosition()).get(position));
                                                             Log.i("testid", current.getTestid().get(current.getPosition()).get(position));
                                                             map.put("sampleno",current.getSampleno().get(current.getPosition()));
                                                             Log.i("sampleno",current.getSampleno().get(current.getPosition()));
                                                             map.put("inwardno", current.getInwardno());
                                                             Log.i("inwardno", current.getInwardno());


                                                             return map;
                                                         }
                                                     };
                                                     queue.add(request);
                                                 }
                                             });
        description=current.getDescription();
        holder.test_standard.setText("Test Standard : "+current.gettest_standard().get(current.getPosition()).get(position));
        holder.pdf_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.i("url","pf"+current.getPdf1());

                String url=current.getPdf1().get(current.getPosition()).get(position);
                String pdf = "http://lab.sitraonline.org/"+url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf));
                mContext.startActivity(browserIntent);


            }
        });
        holder.pdf_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url=current.getPdf2().get(current.getPosition()).get(position);
                String pdf = "http://lab.sitraonline.org/"+url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf));
                mContext.startActivity(browserIntent);





            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, current.testname()., Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.testname)
        TextView txtTitle;
        @BindView(R.id.view)
        ImageView view_image;
        @BindView(R.id.pdf_one)
        ImageView pdf_one;
        @BindView(R.id.pdf_two)
        ImageView pdf_two;
        @BindView(R.id.test_standard)
        TextView test_standard;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public void showwdialog(String head,String test_standard){
        Activity activity = null;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        TextView a_test_name,teststandard,count,desc,a_publish_date,a_head,result_comments;
        ListView a_listview ;

        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater factory = LayoutInflater.from(mContext);
        final View v = factory.inflate(R.layout.alert_view_result, null);


        a_test_name=(TextView)v.findViewById(R.id.a_test_name);
        a_head=(TextView)v.findViewById(R.id.a_head);
        teststandard=(TextView)v.findViewById(R.id.teststandard);
        count=(TextView)v.findViewById(R.id.count);
        result_comments=(TextView)v.findViewById(R.id.result_comments);
        desc=(TextView)v.findViewById(R.id.desc);
        a_listview=(ListView)v.findViewById(R.id.a_listview);
        list_alert_view_result listAlertViewResult=new list_alert_view_result(mContext,para_name,results);
        a_listview.setAdapter(listAlertViewResult);
        a_test_name.setText(s_testname);
        teststandard.setText(test_standard);
        desc.setText(description);
        a_head.setText(head);
        count.setText(sample_count);
        result_comments.setText(result_comment);




        alertDialog.setView(v);
        alertDialog.show();
        alertDialog.setCancelable(true);




    }
}
