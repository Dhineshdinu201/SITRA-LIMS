package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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


    public class HorizontalRecyclerAdapter_pending extends
            RecyclerView.Adapter<HorizontalRecyclerAdapter_pending.HorizontalViewHolder> {
        Constant constant=new Constant();
        String GET_URL = constant.ip+"get_pending_test_details";
        private Context mContext;
        ArrayList<String>s_no_list=new ArrayList<>();
        ArrayList<String>status_list=new ArrayList<>();
        private ArrayList<Horizondal_model_pending> mArrayList;

        public HorizontalRecyclerAdapter_pending(Context mContext,
                                                 ArrayList<Horizondal_model_pending> mArrayList) {
            this.mContext = mContext;
            this.mArrayList = mArrayList;
        }

        @Override
        public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_horizontal_pending, parent, false);
            return new HorizontalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HorizontalViewHolder holder, final int position) {
            final Horizondal_model_pending current = mArrayList.get(position);
            holder.txtTitle.setText(current.getName());
            if(current.getId().equals("4")) {
                holder.stat.setImageResource(R.drawable.completed);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    s_no_list=new ArrayList<>();
                    status_list=new ArrayList<>();
                    s_no_list.clear();
                    status_list.clear();

                    RequestQueue queue = Volley.newRequestQueue(mContext);
                    StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            Log.i("My success", response);
                            try {
                                JSONObject jo = new JSONObject(response);
                                JSONObject jsonObject = jo.getJSONObject("header");
                                String test_name=jsonObject.getString("test_name");
                                String test_std=jsonObject.getString("test_std");
                                JSONArray samples=jo.getJSONArray("samples");
                                Log.i("samples", String.valueOf(samples));
                                for(int i=0;i<samples.length();i++){

                                    JSONObject js=samples.getJSONObject(i);
                                    String sampleno=js.getString("sampleno");
                                    String test_status=js.getString("test_status");
                                    s_no_list.add(sampleno);
                                    status_list.add(test_status);

                                }
                                showwdialog(test_name,test_std);


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
                            map.put("testid", current.getTestid().get(current.getPosition()).get(position));
                            Log.i("testid", current.getTestid().get(current.getPosition()).get(position));
                            map.put("inwardno", current.getInwardno());

                            return map;
                        }
                    };
                    queue.add(request);
                }
            });

        }
        public void showwdialog(String sample,String test_standard){
            Activity activity = null;
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            ListView a_listview ;
            TextView testname,teststandard;


            final AlertDialog alertDialog = dialogBuilder.create();
            LayoutInflater factory = LayoutInflater.from(mContext);
            final View v = factory.inflate(R.layout.alert_view_pending, null);
            testname=(TextView) v.findViewById(R.id.testname);
            teststandard=(TextView) v.findViewById(R.id.teststandard);

            a_listview=(ListView)v.findViewById(R.id.a_listview);
            testname.setText(sample);
            teststandard.setText(test_standard);
            list_alert_view_result listAlertViewResult=new list_alert_view_result(mContext,s_no_list,status_list);
            a_listview.setAdapter(listAlertViewResult);

            alertDialog.setView(v);
            alertDialog.show();
            alertDialog.setCancelable(true);




        }
        @Override
        public int getItemCount() {
            return mArrayList.size();
        }

        class HorizontalViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.testname)
            TextView txtTitle;
            @BindView(R.id.stat)
            ImageView stat;

            public HorizontalViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }

        }
    }

