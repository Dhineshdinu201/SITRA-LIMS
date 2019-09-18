package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerPaymentHistory extends RecyclerView.Adapter<RecyclerPaymentHistory.TestViewHolder> {
    List<PaymentHistoryModel> clubss;
    RecyclerPaymentHistory(List<PaymentHistoryModel>clubss){
        this.clubss=clubss;
    }
    public static class TestViewHolder extends RecyclerView.ViewHolder {
        TextView refno,date,mode,cash,remarks;
        ImageView status;




        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            refno=(TextView)itemView.findViewById(R.id.ref_no);
            date=(TextView)itemView.findViewById(R.id.date);
            status=(ImageView)itemView.findViewById(R.id.status);
            mode=(TextView)itemView.findViewById(R.id.cash_type);
            cash=(TextView)itemView.findViewById(R.id.amount);
            remarks=(TextView)itemView.findViewById(R.id.remarks);



        }
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_payment_history,viewGroup,false);
        TestViewHolder tvh=new TestViewHolder(view);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int i) {
        holder.refno.setText(clubss.get(i).refno);
        holder.date.setText(clubss.get(i).date);
        holder.status.setImageResource(clubss.get(i).status);
        holder.mode.setText(clubss.get(i).mode);
        holder.cash.setText(clubss.get(i).amount);
        holder.remarks.setText(clubss.get(i).remark);

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

