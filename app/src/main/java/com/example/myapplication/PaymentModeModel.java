package com.example.myapplication;

import java.util.ArrayList;

public class PaymentModeModel {
    ArrayList<String> date,mode,ref,value,id,remarks,count;
    PaymentModeModel(ArrayList<String> date,ArrayList<String> mode,ArrayList<String> ref,ArrayList<String> value,ArrayList<String> id,ArrayList<String> remarks,ArrayList<String> count){
        this.date=date;
        this.mode=mode;
        this.ref=ref;
        this.value=value;
        this.id=id;
        this.remarks=remarks;
        this.count=count;
    }
}
