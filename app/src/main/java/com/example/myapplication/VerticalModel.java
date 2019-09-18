package com.example.myapplication;


import java.util.ArrayList;

public class VerticalModel {

    private String title,desc,stat,sample_count,sample_type;
    private ArrayList<HorizontalModel> arrayList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSampleCount() {
        return sample_count;
    }

    public void setSampleCount(String sample_count) {
        this.sample_count = sample_count;
    }
    public String getSampleType() {
        return sample_type;
    }

    public void setSampleType(String sample_type) {
        this.sample_type = sample_type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public ArrayList<HorizontalModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<HorizontalModel> arrayList) {
        this.arrayList = arrayList;
    }
}

