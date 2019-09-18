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

public class Fragment_chemistry_fac extends Fragment {

    AdapterViewFlipper adapterViewFlipper;
    int[] images={
            R.drawable.edit_che_one,
            R.drawable.edit_che_two,
            R.drawable.edit_che_three,
            R.drawable.edit_che_four,
            R.drawable.edit_che_five,

    };
    String[] text={
            "ATOMIC ABSROPTION SPECTROSCOPY",
            "FT-IR SPECTROSCOPY",
            "GASS CHROMATOGRAPHY",
            "HIGH PERFORMANCE LIQUID CHROMATOGRAPHY-MASS SPECTROSCOPY",
            "UV-VIS SPECTROSCOPY",
            };
    String[] details={
            "Identify the heavy metals represent in the complex matrix by atomic absorption spectroscopy. The result explains the level of toxic metals present in the samples.the textile materials,plant powders,alloys,environmental samples etc,are detected for their presence up tyo ppt level.",
            "In the Infra Red spectroscopy IR radiation passes through the substance,some wavelength of the Radiation absorbed/transmitted by the substance depending on the spectra derived.The resulting spectrum represents the molecular absorption/transmission,creating a molecular fingerprint of the sample.this makes Ir spectroscopy useful for several type of analysis.",
            "Gas chromatography specifically gas-liquid chromatography involves a sample being injected and vaporised on to the head of the chromatographic column.The sample is transported through the column by the flow of insert,gaseous mobile phase.The analytes are eluted at the particular temperature.The eluted analyteds are analysed by mass spectroscopy and combined with in-build library.plants,environmental,microbial extracts can be analysed for unknown components",
            "HPLC is specially made for separating the analytes by liquid-liquid separation.In this technique,the analytes are represents in liquid form,the mobile phases is also a liquid,neither be isocratic or gradient type.The stationary phase is a packed column and it varies based on the application.The equipment is very much suitable for quantification upto nanogram level",
            "UV-VIS works on basis od absorption and transmittance.By this Technique,we can analyse the samples through the spectrum obtained and qualitiative/quantitative results can be evolved.",
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
        CusAdap cusAdap=new CusAdap(getActivity(),images,details,text);
        adapterViewFlipper.setAdapter(cusAdap);
        adapterViewFlipper.setFlipInterval(3000);
        adapterViewFlipper.setAutoStart(true);


        expandableListView=(ExpandableListView)view.findViewById(R.id.expListView_phy);
        listChild=ExpandableListdata_che.getData();
        listHeader=new ArrayList<String>(listChild.keySet());
        expandable_phy_adapter=new Expandable_phy_adapter(getActivity(),listHeader,listChild);
        expandableListView.setAdapter(expandable_phy_adapter);
        return view;
    }



}
class CusAdap extends BaseAdapter {
    Context context;
    int[] images;
    String[] s_text;
    String[] s_head;
    LayoutInflater inflater;

    public CusAdap(Context applicationContext, int[] images,String[] s_text,String[] s_head) {
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

            head.setText(s_head[position]);
            text.setText(s_text[position]);
            image.setImageResource(images[position]);

        return view;
    }
}