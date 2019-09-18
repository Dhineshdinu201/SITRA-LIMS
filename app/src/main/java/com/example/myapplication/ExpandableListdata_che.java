package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListdata_che {
    public static HashMap<String , List<String>> getData() {
    HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
    List<String> Composition_analysis = new ArrayList<>();
        Composition_analysis.add("\u2022\tBalance 0.01mg accuracy");
        Composition_analysis.add("\u2022\tHot air oven");
        Composition_analysis.add("\u2022\tImage analyser");
        Composition_analysis.add("\u2022\tProjection microscope");
        Composition_analysis.add("\u2022\tDifferential Scanning Calorimeter");

        List<String> Color_fastness = new ArrayList<>();
        Color_fastness.add("\u2022\tPerspirometer");
        Color_fastness.add("\u2022\tLaunderometer");
        Color_fastness.add("\u2022\tColour matching cabinet");
        Color_fastness.add("\u2022\tCrockmeter");
        Color_fastness.add("\u2022\tXeno tester");
        Color_fastness.add("\u2022\tScorch tester");

        List<String> Wet_processing_analysis = new ArrayList<>();
        Wet_processing_analysis.add("\u2022\tSoft overflow dyeing machine");
        Wet_processing_analysis.add("\u2022\tWinch dyeing machines");
        Wet_processing_analysis.add("\u2022\tPadding mangle");
        Wet_processing_analysis.add("\u2022\tAhiba polymat");
        Wet_processing_analysis.add("\u2022\tIR dyeing machine");
        Wet_processing_analysis.add("\u2022\tPackage dyeing machine");
        Wet_processing_analysis.add("\u2022\tRoaches jet dyeing machine");
        Wet_processing_analysis.add("\u2022\tOpen bath beaker dyeing machine");
        Wet_processing_analysis.add("\u2022\tHydro extractor");
        Wet_processing_analysis.add("\u2022\tHeat setting / steamer");
        Wet_processing_analysis.add("\u2022\tComputer colour matching system");


        List<String> General_chemical_test = new ArrayList<>();
        General_chemical_test.add("\u2022\tFlammability tester");
        General_chemical_test.add("\u2022\tUltrasonic water bath");
        General_chemical_test.add("\u2022\tQuick wash plus");
        General_chemical_test.add("\u2022\tTumble drier");
        General_chemical_test.add("\u2022\tAuto titrator");

        List<String>sizing = new ArrayList<>();
        sizing.add("\u2022\tViscosity bath");
        sizing.add("\u2022\tBrookfield viscometer");
        sizing.add("\u2022\tFlash point apparatus");
        sizing.add("\u2022\tMelting point apparatus");
        sizing.add("\u2022\tMuffle furnace");



        List<String> water = new ArrayList<>();
        water.add("\u2022\tSpectroquant 118");
        water.add("\u2022\tpH meter");
        water.add("\u2022\tThermoreactor");
        water.add("\u2022\tFlame photometer");
        water.add("\u2022\tConductivity meter");
        water.add("\u2022\tBOD incubator");
        water.add("\u2022\tUltra water purifier");
        water.add("\u2022\tTotal organic carbon analyser");
        water.add("\u2022\tDO meter");


        List<String> eco = new ArrayList<>();
        eco.add("\u2022\t High performance thin layer chromatograph (HPTLC)");
        eco.add("\u2022\t High performance liquid chromatograph (HPLC)");
        eco.add("\u2022\tGas chromatograph with mass spectrum Detector (GC-MS)");
        eco.add("\u2022\tGas chromatograph electron capture detector (GC-ECD)");
        eco.add("\u2022\tAtomic absorption spectro photometer (AAS)  ");
        eco.add("\u2022\tUV-VIS spectrophotometer (UV-VIS)");
        eco.add("\u2022\tRotary vacuum evaporator");
        eco.add("\u2022\tCentrifugal evaporator");
        eco.add("\u2022\tTurbo rotary evaporator");
        eco.add("\u2022\tAccelerated solvent extractor");
        eco.add("\u2022\tMicrowave digestor");

        expandableListDetail.put("Composition analysis",Composition_analysis);
        expandableListDetail.put("Colour fastness",Color_fastness);
        expandableListDetail.put("Wet processing analysis",Wet_processing_analysis);
        expandableListDetail.put("General chemical tests",General_chemical_test);
        expandableListDetail.put("Sizing ingredients, auxiliaries, oil & wax analysis",sizing);
        expandableListDetail.put("Water, effluent & sludge analysis",water);
        expandableListDetail.put("Eco parameters / Oeko tex 100 specification",eco);

    return expandableListDetail;
}
}

