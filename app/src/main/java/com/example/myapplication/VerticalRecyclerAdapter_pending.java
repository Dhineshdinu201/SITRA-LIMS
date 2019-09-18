package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VerticalRecyclerAdapter_pending extends
        RecyclerView.Adapter<VerticalRecyclerAdapter_pending.VerticalRecyclerViewHolder> {

    private Context mContext;
    private ArrayList<Vertical_model_pending> mArrayList = new ArrayList<>();

    public VerticalRecyclerAdapter_pending(Context mContext, ArrayList<Vertical_model_pending> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @Override
    public VerticalRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical_pending, parent, false);
        return new VerticalRecyclerViewHolder(view);
    }

    public void setList(ArrayList<Vertical_model_pending> mArrayList){
        this.mArrayList.addAll(mArrayList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(VerticalRecyclerViewHolder holder, int position) {


        final Vertical_model_pending current = mArrayList.get(position);

        final String strTitle = current.getTitle();
        final String strDesc=current.getDesc();
        final String strStatus=current.getStat();

        ArrayList<Horizondal_model_pending> singleSectionItems = current.getArrayList();


        holder.tvTitle.setText(strTitle);
        holder.date.setText("Date : " +current.getDesc());
        holder.no_sample.setText("Test Pending : "+current.getPending());
        holder.sam_num.setText("no.of samples : "+current.getStat());



        HorizontalRecyclerAdapter_pending itemListDataAdapter =
                new HorizontalRecyclerAdapter_pending(mContext, singleSectionItems);

        holder.rvHorizontal.setHasFixedSize(true);
        holder.rvHorizontal.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.VERTICAL, false));
        holder.rvHorizontal.setAdapter(itemListDataAdapter);

        holder.rvHorizontal.setNestedScrollingEnabled(false);


    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class VerticalRecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sample_des)
        TextView date;
        @BindView(R.id.no_sample)
        TextView no_sample;
        @BindView(R.id.inward_no)
        TextView tvTitle;
        @BindView(R.id.sam_num)
        TextView sam_num;

        @BindView(R.id.rvHorizontal)
        RecyclerView rvHorizontal;


        public VerticalRecyclerViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}

