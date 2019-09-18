package com.example.myapplication;
import android.util.Log;

import java.util.ArrayList;
public class Horizondal_model_pending {
    private String name;
    private String description;
    private String id;
    int position;
    private String inwardno;
    private String sampleno;
    ArrayList<ArrayList<String>> testid;
    private String pdf2_url;
    private String count;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInwardno() {
        return inwardno;
    }

    public void setInwardno(String inwardno) {
        this.inwardno = inwardno;
    }

    public ArrayList<ArrayList<String>> getTestid() {
        return testid;
    }

    public void setTestid(ArrayList<ArrayList<String>> testid) {
        Log.i("setTestid", String.valueOf(testid));
        this.testid = testid;
    }

}