package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Weaving extends Fragment implements SearchView.OnQueryTextListener {


    //Declaration

    TextView labcount;
    Spinner searchableSpinner;
    ArrayAdapter adapter, adappter;

    String GET_URL = "http://lab.sitraonline.org/index.php/api/getTestDetails";



    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    private List<String> clubs;
    Context context;
    ArrayList<String> testname_list,memrate_list,nonmemrate_list,standard_list,alias_list,samplesize_list,nabl_list;
    ListView listView;
    String s_mem_rate;



    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancetate){
        View view=inflater.inflate(R.layout.fragment_weaving,container,false);


        //initialization
        searchableSpinner=(Spinner) view.findViewById(R.id.spinner_search_weav);
        listView=(ListView)view.findViewById(R.id.listview);
        labcount=(TextView)view.findViewById(R.id.lab_count);

        //spinner



        String[] chemistry={"-SELECT LAB-","Knitting"};
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,chemistry);
        searchableSpinner.setAdapter(adapter);

        //recycler view for test name


        send();
        return view;
    }
    private void send() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("My success", response);
                try {
                    JSONArray array = new JSONArray(response);
                    testname_list = new ArrayList<>();
                    memrate_list=new ArrayList<>();
                    alias_list=new ArrayList<>();
                    standard_list=new ArrayList<>();
                    nonmemrate_list=new ArrayList<>();
                    samplesize_list=new ArrayList<>();
                    nabl_list=new ArrayList<>();
                    clubs = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object;
                        object = array.getJSONObject(i);




                        String item = object.getString("testname");
                        String s_alias=object.getString("alias");
                        String s_standard=object.getString("standard");
                        String s_nonmem_rate=object.getString("nonmemrate");
                        String s_samplesize=object.getString("samplesize");
                        String s_nabl=object.getString("nabl");
                        s_mem_rate=object.getString("testid");




                        Log.i("test_name", "item" + item);





                        testname_list.add(item);
                        memrate_list.add(s_mem_rate);
                        alias_list.add(s_alias);
                        standard_list.add(s_standard);
                        nonmemrate_list.add(s_nonmem_rate);
                        samplesize_list.add(s_samplesize);
                        nabl_list.add(s_nabl);
                        labcount.setText(""+array.length());




                    }
                    adappter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,testname_list);
                    listView.setAdapter(adappter);


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            showdialog(position);

                        }
                    });

                    adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,testname_list);
                    searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            showdialog(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    searchableSpinner.setAdapter(adappter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("deptid", "7");
                map.put("labid", "100");
                map.put("testname", "NABL");


                return map;
            }
        };
        queue.add(request);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
    public int showdialog(int position){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        TextView labname,testname,allias,standard,sample,mem_rate,nonmem_rate,nabl;
        Button close;
        ImageView img_close;

        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater factory = LayoutInflater.from(getContext());
        final View v = factory.inflate(R.layout.alert_testing, null);
        testname=(TextView)v.findViewById(R.id.alert_testname);
        allias=(TextView)v.findViewById(R.id.alert_allias);
        standard=(TextView)v.findViewById(R.id.alert_standard);
        sample=(TextView)v.findViewById(R.id.alert_samplesize);
        close=(Button)v.findViewById(R.id.alert_close);
        mem_rate=(TextView)v.findViewById(R.id.alert_memrate);
        nonmem_rate=(TextView)v.findViewById(R.id.alert_nonmem_rate);
        nabl=(TextView)v.findViewById(R.id.alert_nabl);





        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

            }
        });
        testname.setText(testname_list.get(position));
        allias.setText(alias_list.get(position));
        standard.setText(standard_list.get(position));
        sample.setText(samplesize_list.get(position));
        mem_rate.setText(memrate_list.get(position));
        nonmem_rate.setText(nonmemrate_list.get(position));
        nabl.setText(nabl_list.get(position));
        alertDialog.setView(v);
        alertDialog.show();
        alertDialog.setCancelable(false);




        return position;
    }
}
