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

public class Fragment_physics_fac extends Fragment {

    AdapterViewFlipper adapterViewFlipper;
    int[] images={
            R.drawable.edit_phy_one,

            R.drawable.edit_phy_three,
            R.drawable.edit_phy_four,
            R.drawable.edit_phy_five,
            R.drawable.edit_phy_six,

    };
    String[] text={"USTER AFIS PRO2",

            "USTER HVI 1000",
            "USTER QUANTUM2",
            "USTER TENSOJET 4",
            "USTER TENSORAPID 4",
    };
    String[] detail={"USTER AFIS PRO2 is the indutry standard for process optimization in spinning mills.It provide all the required data,with accuracy and speed.It measures neps,seed-coat neps,short fibre content,fineness,maturity,trash and dust-all of which influence yarn performance in manufacturing",
            "USTER HVI 1000 rapidly provides full reports on 11 important characteristics describing the length,strength,fineness,maturity and moisture content of the cotton fibre tested.Iw will also provide color grade in terms of the Rd and +b values.",
            "USTER QUANTUM 2.For the first time ever,The system shows the Full yarn body,A new foriegn matter sensor uses high light sources in combination with a new capacitive sensor to sortout color foriegn fibres from nondidtributing vegetable matter.And the contamination package is completed by a brand new PP clearing module.",
            "Yarn must be strong enough to stand-up to downstream process without causing stippages or downtime.the USTER TENSOJET 4 has the testing power to forcast yarn performance accurately.It measures the tensile strength and elongation at the rate of 30.000 breaks per hour.Detection of seldom-occuring yarn faults or isolated weak place is possible with the evaluations.",
            "Precise information on yarn Strength,elongation and tenacity is provided by the USTER TENSORAPID 4.The instruments shows the mean values and co-efficient of variation of strength and elongation characteristics of textile yarns which determines their usability",

    };
    ExpandableListView expandableListView;
    HashMap<String, List<String>> listChild;
    List<String>listHeader;
    Expandable_phy_adapter expandable_phy_adapter;



    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancetate) {
        View view = inflater.inflate(R.layout.fragment_physics_fac, container, false);
        adapterViewFlipper=(AdapterViewFlipper)view.findViewById(R.id.adapterViewFlipper_phy);
        CustomAdapter customAdapter=new CustomAdapter(getActivity(),images,detail,text);
        adapterViewFlipper.setAdapter(customAdapter);
        adapterViewFlipper.setFlipInterval(3000);
        adapterViewFlipper.setAutoStart(true);


        expandableListView=(ExpandableListView)view.findViewById(R.id.expListView_phy);
        listChild=ExpandableListData.getData();
        listHeader=new ArrayList<String>(listChild.keySet());
        expandable_phy_adapter=new Expandable_phy_adapter(getActivity(),listHeader,listChild);
        expandableListView.setAdapter(expandable_phy_adapter);
        return view;
    }



}
class CustomAdapter extends BaseAdapter {
    Context context;
    int[] images;
    String[] s_text;
    String[] s_head;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, int[] images,String[] s_text,String[] s_head) {
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
