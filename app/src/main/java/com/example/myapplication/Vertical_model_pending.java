package com.example.myapplication;

import java.util.ArrayList;

public class Vertical_model_pending {

    private String title;
    private String desc;
    private String stat;

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    private String pending;
    private ArrayList<Horizondal_model_pending> arrayList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public ArrayList<Horizondal_model_pending> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Horizondal_model_pending> arrayList) {
        this.arrayList = arrayList;
    }
}

