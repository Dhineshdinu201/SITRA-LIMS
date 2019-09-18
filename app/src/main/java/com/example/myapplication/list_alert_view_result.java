package com.example.myapplication;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class list_alert_view_result extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String>parametername;
    private final ArrayList<String>results;
    public list_alert_view_result(Context context, ArrayList<String> parametername,ArrayList<String> results) {
        super(context, R.layout.listview_alert_view_result,parametername);
        this.context=context;
        this.parametername=parametername;
        this.results=results;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.listview_alert_view_result,null,true);
        TextView cus_paraname=(TextView)rowView.findViewById(R.id.cus_para_name);
        TextView cus_results=(TextView)rowView.findViewById(R.id.cus_result);
        cus_paraname.setText(Html.fromHtml(parametername.get(position)));
        cus_results.setText(Html.fromHtml(results.get(position)));
        return rowView;
    }
}
