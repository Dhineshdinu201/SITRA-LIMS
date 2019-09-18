package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListData {
    public static HashMap<String , List<String>>getData(){
        HashMap<String,List<String>> expandableListDetail=new HashMap<String, List<String>>();


        List<String>Yarn=new ArrayList<>();
        Yarn.add("\u2022\tUster Unevenness Tester UT-4 & UT-5");
        Yarn.add("\u2022\tUsterTenso- Rapid UTR-4");
        Yarn.add("\u2022\tUster Tensojet – 4");
        Yarn.add("\u2022\tZweigle Hairiness Tester (565 & 566)");
        Yarn.add("\u2022\tUster HL 400");
        Yarn.add("\u2022\tKeissoki Hairiness Tester (Laserspot)");
        Yarn.add("\u2022\tLawson and Hemphill Constant Tension Transport System ");
        Yarn.add("\u2022\tSTATEX CSP Tester ");
        Yarn.add("\u2022\tMAG Yarn Abrasion Tester");
        Yarn.add("\u2022\tElectronic Wrap Reel");
        Yarn.add("\u2022\tYarn Appearance board winder/ ASTM Yarn Appearance Boards");
        Yarn.add("\u2022\tMAG Twist Tester ");
        Yarn.add("\u2022\tUster Yarn Classimat Faults -3");
        Yarn.add("\u2022\tUster Yarn Classimat Fault – 5");



        List<String>Fibre=new ArrayList<>();


        Fibre.add("\u2022\tUster HVI -1000");
        Fibre.add("\u2022\tPremier ART 3");
        Fibre.add("\u2022\tUster - AFIS  ");
        Fibre.add("\u2022\tUster – AFIS PRO");
        Fibre.add("\u2022\tUster – AFIS PRO 2");
        Fibre.add("\u2022\tLenzing- Vibroskop, Vibrodyn");
        Fibre.add("\u2022\tProjection Microscope");
        Fibre.add("\u2022\tShirley Trash Analyzer");
        Fibre.add("\u2022\tStatex Trash Separator");
        Fibre.add("\u2022\tMesdan Moisture Oven");



        List<String>Fabric=new ArrayList<>();


        Fabric.add("\u2022\tDak Tensile Strength Tester – 5 KN");
        Fabric.add("\u2022\tZwickRoell Universal Strength Tester – 5 KN" );
        Fabric.add("\u2022\tDillionQuantrol  Circular Bending Tester  ");
        Fabric.add("\u2022\tTextestFx 3300 - III Air Permeability Tester  ");
        Fabric.add("\u2022\tParamount Elmendorf Tear Tester");
        Fabric.add("\u2022\tMAG Crease Recovery Tester");
        Fabric.add("\u2022\tShirley Stiffness Tester");
        Fabric.add("\u2022\tMAG Ana thick thickness Tester");
        Fabric.add("\u2022\tICI Pill Box ");
        Fabric.add("\u2022\tEureka Drape Tester");
        Fabric.add("\u2022\tShimadzu Seisakusho Flex Abrasion Tester");
        Fabric.add("\u2022\tShirley Fabric Friction Tester");
        Fabric.add("\u2022\tMAG Bursting Strength Tester -Digital (upto 70 kg), Analog (upto 28 kg)");
        Fabric.add("\u2022\tShirley Bending Length Tester ");
        Fabric.add("\u2022\tCrimp Tester");


        expandableListDetail.put("Fabric",Fabric);
        expandableListDetail.put("Fibre",Fibre);
        expandableListDetail.put("Yarn",Yarn);
        return expandableListDetail;
    }
}
