package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_engg_fac extends Fragment {

    AdapterViewFlipper adapterViewFlipper;
    int[] images={
            R.drawable.eng_one,

            R.drawable.eng_two,
            R.drawable.eng_three,
            R.drawable.eng_four,

    };
    String[] text={"USTER AFIS PRO2",

            "",
            "",
            "",
            "",
    };
    String[] detail={"",
            "",
            "",
            "",
            "",

    };
    ExpandableListView expandableListView;
    HashMap<String, List<String>> listChild;
    List<String>listHeader;
    Expandable_engg_adapter expandable_engg_adapter;



    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancetate) {
        View view = inflater.inflate(R.layout.fragment_physics_fac, container, false);
        adapterViewFlipper=(AdapterViewFlipper)view.findViewById(R.id.adapterViewFlipper_phy);
        CustomAdapterr customAdapter=new CustomAdapterr(getActivity(),images,detail,text);
        adapterViewFlipper.setAdapter(customAdapter);
        adapterViewFlipper.setFlipInterval(3000);
        adapterViewFlipper.setAutoStart(true);


        expandableListView=(ExpandableListView)view.findViewById(R.id.expListView_phy);
        listChild=ExpandableListDataCoe.getData();
        listHeader=new ArrayList<String>(listChild.keySet());
        expandable_engg_adapter=new Expandable_engg_adapter(getActivity(),listHeader,listChild);
        expandableListView.setAdapter(expandable_engg_adapter);
        return view;
    }



}
class CustomAdapterr extends BaseAdapter {
    Context context;
    int[] images;
    String[] s_text;
    String[] s_head;
    LayoutInflater inflater;

    public CustomAdapterr(Context applicationContext, int[] images,String[] s_text,String[] s_head) {
        this.context = applicationContext;
        this.images = images;
        this.s_text=s_text;
        this.s_head=s_head;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.adapter_list_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView text=(TextView)view.findViewById(R.id.text_slider);
        TextView head=(TextView)view.findViewById(R.id.slide_head);
        head.setText(s_head[position
                ]);
        text.setText(s_text[position]);
        image.setImageResource(images[position]);
        return view;
    }
}

