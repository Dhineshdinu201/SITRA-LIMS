package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerInwardPending extends RecyclerView.Adapter<RecyclerInwardPending.TestViewHolder> {
    List<PendingInwardModel> clubss;
    Context mContext;
    RecyclerInwardPending(Context context,List<PendingInwardModel>clubss){
        this.clubss=clubss;
        this.mContext=context;
    }
    public static class TestViewHolder extends RecyclerView.ViewHolder {
        TextView sample_no,status_pending,no_sample,sample_date,pending_type,pending_req_no;
        ImageView view_pending;




        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            sample_no=(TextView)itemView.findViewById(R.id.sample_no);
            status_pending=(TextView)itemView.findViewById(R.id.status_pending);
            no_sample=(TextView)itemView.findViewById(R.id.no_sample);
            sample_date=(TextView)itemView.findViewById(R.id.sample_date);
            pending_type=(TextView)itemView.findViewById(R.id.pending_type);
            pending_req_no=(TextView)itemView.findViewById(R.id.pending_req_no);
            view_pending=(ImageView) itemView.findViewById(R.id.view_pending);

        }
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_mem_view_rep,viewGroup,false);
        TestViewHolder tvh=new TestViewHolder(view);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, final int i) {
        final PendingInwardModel current=clubss.get(i);
        holder.sample_no.setText(current.inwardno_list.get(i));
        holder.status_pending.setText(current.status.get(i));
        holder.sample_date.setText(current.date.get(i));
        holder.pending_type.setText(current.type.get(i));
        holder.pending_req_no.setText(current.Requestno.get(i));
        holder.no_sample.setText(current.no_of_samples.get(i));
        holder.view_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,Activity_view_report.class);
                intent.putExtra("inwardno",current.inwardno_list.get(i));
                Log.i("ineardno",""+current.inwardno_list.get(i));
                Log.i("password",current.access_code_list.get(i));
                intent.putExtra("password",current.access_code_list.get(i));
                intent.putExtra("type","1");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return clubss.size();
    }
}
