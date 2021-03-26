package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recycler_payment_mode extends RecyclerView.Adapter<Recycler_payment_mode.TestViewHolder> {
    Constant constant=new Constant();
    String GET_URL=constant.ip+"get_pending_document_lists_byid";
    List<PaymentModeModel> clubss;
    ArrayAdapter arrayAdapter;
    ArrayList<String>doc_name,doc_path;
    Context mContext;
    Recycler_payment_mode(Context context,List<PaymentModeModel>clubss){
        this.clubss=clubss;
        this.mContext=context;
    }
public static class TestViewHolder extends RecyclerView.ViewHolder {
    TextView date,value,ref,mode,status,view_image,remarks;




    public TestViewHolder(@NonNull View itemView) {
        super(itemView);
        date=(TextView)itemView.findViewById(R.id.date);
        mode=(TextView)itemView.findViewById(R.id.mode);
        ref=(TextView)itemView.findViewById(R.id.ref);
        value=(TextView)itemView.findViewById(R.id.value);
        status=(TextView)itemView.findViewById(R.id.status);
        view_image=(TextView)itemView.findViewById(R.id.view);
        remarks=(TextView)itemView.findViewById(R.id.remarks);


    }
}
    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_payment_mode,viewGroup,false);
        TestViewHolder tvh=new TestViewHolder(view);
        return tvh;
    }



    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, final int i) {
        final PaymentModeModel current=clubss.get(i);

            holder.date.setText("Date : " + current.date.get(i));
            holder.ref.setText("Reference : " +clubss.get(i).ref.get(i));
            holder.value.setText("Value : " + clubss.get(i).value.get(i));
            holder.mode.setText("Mode : " + clubss.get(i).mode.get(i));
            holder.status.setText("Doc Count : " + clubss.get(i).count.get(i));
            holder.remarks.setText("Remarks : " + clubss.get(i).remarks.get(i));

                holder.view_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("id","++++++"+current.id.get(i));
                        send(""+current.id.get(i));
                    }
                });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.i("clubsize",""+clubss.size());
        return clubss.size();
    }
    public void show_dialog(){

      Activity activity = null;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        ListView a_listview ;

        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater factory = LayoutInflater.from(mContext);
        final View v = factory.inflate(R.layout.alert_payment_mode_image, null);
        String[] dept={"asd","asd","asd","asd","asd","asd","asd","asd","asd","asd","asd"};

        a_listview=(ListView)v.findViewById(R.id.list_item);
        Log.i("mmm","++"+doc_name);
        arrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, doc_name );
        a_listview.setAdapter(arrayAdapter);
        a_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("intent","intent");
                            Intent intent=new Intent(mContext,webview.class);
                            intent.putExtra("c","http://lab.sitraonline.org.in/mobile_images/"+doc_path.get(position));
                            mContext.startActivity(intent);



                        }
                    });

        alertDialog.setView(v);
        alertDialog.show();
        alertDialog.setCancelable(true);

    }
    public void send(final String payid){
        doc_name=new ArrayList<>();
        doc_name.clear();
        doc_path=new ArrayList<>();
        doc_path.clear();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest request = new StringRequest(Request.Method.POST, GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("My success", "++" + response);

                try{


                    JSONArray js=new JSONArray(response);
                    for(int i=0;i<js.length();i++) {
                        JSONObject jo = js.getJSONObject(i);
                        String s_doc_name=jo.getString("document_name");
                        String s_doc_path=jo.getString("document_path");
                        doc_name.add(s_doc_name);
                        doc_path.add(s_doc_path);

                    }
                    Log.i("doc_name", String.valueOf(doc_name));
                    Log.i("doc_path", String.valueOf(doc_path));
                    show_dialog();

                } catch (
                        JSONException e) {
                    Toast.makeText(mContext, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(mContext, "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("customer_payment_upload_id", payid);




                return map;
            }
        };
        queue.add(request);


    }
}
