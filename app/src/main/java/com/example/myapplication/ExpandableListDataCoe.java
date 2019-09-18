package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataCoe {
    public static HashMap<String , List<String>> getData(){
        HashMap<String,List<String>> expandableListDetail=new HashMap<String, List<String>>();


        List<String> Dimension_Pressure_Calibration = new ArrayList<>();
        Dimension_Pressure_Calibration.add("\u2022\tCaliper Checker");
        Dimension_Pressure_Calibration.add("\u2022\tGuage Block Set");
        Dimension_Pressure_Calibration.add("\u2022\tLong Slip Gauges");
        Dimension_Pressure_Calibration.add("\u2022\tElectronic Dial Calibration Tester With  DRO");
        Dimension_Pressure_Calibration.add("\u2022\tElectronic Probe With DRO");
        Dimension_Pressure_Calibration.add("\u2022\tTape & Scale Calibrator");
        Dimension_Pressure_Calibration.add("\u2022\tGranite Surface Plate");
        Dimension_Pressure_Calibration.add("\u2022\tHygrometer");
        Dimension_Pressure_Calibration.add("\u2022\tDigital Micrometer");
        Dimension_Pressure_Calibration.add("\u2022\tPlunger dial");
        Dimension_Pressure_Calibration.add("\u2022\tDigital Vernier Caliper");
        Dimension_Pressure_Calibration.add("\u2022\tComparator Stand");
        Dimension_Pressure_Calibration.add("\u2022\tDigital Pressure Calibrator");
        Dimension_Pressure_Calibration.add("\u2022\tProcess datalogger with(Temperature & RH) Sensor");


        List<String> Speed_Calibration = new ArrayList<>();
        Speed_Calibration.add("\u2022\tDigital Tachometer (non contact)");


        List<String> Balance_mass_Calibration = new ArrayList<>();
        Balance_mass_Calibration.add("\u2022\tAustenitic Stainless Steel Wire Weights ");
        Balance_mass_Calibration.add("\u2022\tIntegral Knob Cylindrical Stainless Steel Weights");
        Balance_mass_Calibration.add("\u2022\tStainless Steel Weights ");
        Balance_mass_Calibration.add("\u2022\tDead weights");
        Balance_mass_Calibration.add("\u2022\tElectronic Weighing Balance(Twin range) ");
        Balance_mass_Calibration.add("\u2022\tHumidity/Baro/Temp.Data recorder");



        List<String> Force_Calibration = new ArrayList<>();
        Force_Calibration.add("\u2022\tForce Proving Instrument");


        List<String>Other_Instruments = new ArrayList<>();
        Other_Instruments.add("\u2022\tSteel Sclae");
        Other_Instruments.add("\u2022\tMeasuring Tape");
        Other_Instruments.add("\u2022\tLoad Cell Indicator SI 478U Indicator 12129");
        Other_Instruments.add("\u2022\tRubber  Hardness Tester Calibrator");
        Other_Instruments.add("\u2022\tDigital Lux Meter");
        Other_Instruments.add("\u2022\tInfrared Thermometer");
        Other_Instruments.add("\u2022\tDigital Stop Watch");
        Other_Instruments.add("\u2022\tFeeler Gauge (Thickness gauge)");
        Other_Instruments.add("\u2022\tPneumatic Pump");
        Other_Instruments.add("\u2022\tHydraulic Pump");
        Other_Instruments.add("\u2022\tPneumatic Pump");
        Other_Instruments.add("\u2022\tRotational Speed Source");





        expandableListDetail.put("Dimension & Pressure Calibration",Dimension_Pressure_Calibration);
        expandableListDetail.put("Speed Calibration",Speed_Calibration);
        expandableListDetail.put("Balance & mass Calibration",Balance_mass_Calibration);
        expandableListDetail.put("Force Calibration",Force_Calibration);
        expandableListDetail.put("Other Instruments",Other_Instruments);






        return expandableListDetail;
    }
}

