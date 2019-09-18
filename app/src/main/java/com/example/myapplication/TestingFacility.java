package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestingFacility extends AppCompatActivity {
    Fragment fragment_fac=null;
    TextView title;
    FloatingActionMenu menu;
    FloatingActionButton b2,b3,b4,b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_facility);



        title = (TextView) findViewById(R.id.title_fac);


        fragment_fac = new Fragment_physics_fac();
        title.setText("Testing Facility-Physics");
        // bottomnavigationView.inflateMenu(R.menu.chemistry_navigation);
        loadFragment(fragment_fac);
        menu = (FloatingActionMenu) findViewById(R.id.menu);
        menu.setClosedOnTouchOutside(true);
        menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                if (opened) {
                    if (vibrator != null) {
                        vibrator.vibrate(50);
                    }
                } else {
                    if (vibrator != null) {
                        vibrator.vibrate(50);
                    }
                }
            }
        });

        b2 = (FloatingActionButton) findViewById(R.id.b2);
        b3 = (FloatingActionButton) findViewById(R.id.b3);
        b4 = (FloatingActionButton) findViewById(R.id.b4);
        b5 = (FloatingActionButton) findViewById(R.id.b5);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_fac = new Fragment_physics_fac();
                title.setText("Testing Facility-Physics");
                loadFragment(fragment_fac);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_fac = new Fragment_chemistry_fac();
                title.setText("Testing Facility-Chemistry");
                loadFragment(fragment_fac);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_fac = new Fragment_engg_fac();
                title.setText("Testing Facility-Engineering");
                loadFragment(fragment_fac);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_fac = new Fragment_coe_fac();
                title.setText("Testing Facility-Center Of Excellence");
                loadFragment(fragment_fac);
            }
        });


        //**********************bottom nav click listener************************


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(TestingFacility.this,HomeScreen.class);
        startActivity(intent);
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_fac,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
