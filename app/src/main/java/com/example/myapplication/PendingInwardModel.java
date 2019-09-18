package com.example.myapplication;

import java.util.ArrayList;

public class PendingInwardModel {
    ArrayList<String> date,type,no_of_samples,status,Requestno;
    ArrayList<String>inwardno_list,access_code_list;
    PendingInwardModel(ArrayList<String> date,ArrayList<String> type,ArrayList<String> no_of_samples,ArrayList<String> status,ArrayList<String> requestno,ArrayList<String> inwardno_list,ArrayList<String>access_code_list ){
        this.date=date;
        this.type=type;
        this.no_of_samples=no_of_samples;
        this.status=status;
        this.Requestno=requestno;
        this.inwardno_list=inwardno_list;
        this.access_code_list=access_code_list;
    }

}
