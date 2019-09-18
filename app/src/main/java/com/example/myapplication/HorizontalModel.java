package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

public class HorizontalModel {

   ArrayList<ArrayList<String>>testname;
    ArrayList<ArrayList<String>> testid;
    ArrayList<String> sampleno,sample_type_list,sample_count_list;
    ArrayList<ArrayList<String>> pdf1;
    ArrayList<ArrayList<String>> test_standard;
    ArrayList<ArrayList<String>> pdf2;
    int position;
    String inwardno,s_testname,description;

    public String getS_testname() {
        return s_testname;
    }

    public void setS_testname(String s_testname) {
        this.s_testname = s_testname;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public ArrayList<ArrayList<String>> getTestname() {
        return testname;
    }

    public void setTestname(ArrayList<ArrayList<String>> testname) {
        Log.i("setTestname", String.valueOf(testname));
        this.testname = testname;
    }

    public ArrayList<ArrayList<String>> getTestid() {
        return testid;
    }

    public void setTestid(ArrayList<ArrayList<String>> testid) {
        Log.i("setTestid", String.valueOf(testid));
        this.testid = testid;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    public String getInwardno() {
        return inwardno;
    }

    public void setInwardno(String inwardno) {
        this.inwardno = inwardno;
    }


    public ArrayList<String> getSampleno() {
        return sampleno;
    }
    public void setSampleno(ArrayList<String> sampleno) {
        this.sampleno = sampleno;
    }
    public ArrayList<String> getSamplelist() {
        return sample_type_list;
    }
    public void setSamplelist(ArrayList<String> sample_type_list) {
        this.sample_type_list = sample_type_list;
    }
    public ArrayList<String> getSamplecount() {
        return sample_count_list;
    }
    public void setSamplecount(ArrayList<String> sample_count_list) {
        this.sample_count_list = sample_count_list;
    }

    public ArrayList<ArrayList<String>> getPdf1() {
        return pdf1;
    }

    public void setPdf1(ArrayList<ArrayList<String>> pdf1) {
        Log.i("setpdf1", String.valueOf(pdf1));
        this.pdf1 = pdf1;
    }
    public ArrayList<ArrayList<String>> gettest_standard() {
        return test_standard;
    }

    public void settest_standard(ArrayList<ArrayList<String>> test_standard) {
        Log.i("setpdf1", String.valueOf(test_standard));
        this.test_standard = test_standard;
    }

    public ArrayList<ArrayList<String>> getPdf2() {
        return pdf2;
    }

    public void setPdf2(ArrayList<ArrayList<String>> pdf2) {
        Log.i("setpdf2", String.valueOf(pdf2));
        this.pdf2 = pdf2;
    }









}
