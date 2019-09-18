package com.example.myapplication;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Activity_testing extends AppCompatActivity {
    //declaration
ImageView testchem,testphy,testweav,test_engg,test_coe;
Bundle bundle = new Bundle();
    private ActionBar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        //********************************initialization*******************************
        testchem=(ImageView)findViewById(R.id.test_chem);
        testphy=(ImageView)findViewById(R.id.test_phy);
        testweav=(ImageView)findViewById(R.id.test_weaving);
        test_engg=(ImageView)findViewById(R.id.test_engg);
        test_coe=(ImageView)findViewById(R.id.test_coe);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationView);



        toolbar = getSupportActionBar();








        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        testchem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_testing.this,home2.class);
                bundle.putString("position", "0");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");

            }
        });
        testphy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_testing.this,home2.class);
                bundle.putString("position", "1");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");
            }
        });
        testweav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_testing.this,home2.class);
                bundle.putString("position", "2");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");
            }
        });
        test_engg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_testing.this,home2.class);
                bundle.putString("position", "88");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");
            }
        });

        test_coe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_testing.this,home2.class);
                bundle.putString("position", "98");
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("123", "123");
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    Intent intent = new Intent(Activity_testing.this, HomeScreen.class);
                    startActivity(intent);
                }
                    return true;
                case R.id.navigation_login: {
                    Intent intent = new Intent(Activity_testing.this, home2.class);
                    bundle.putString("position", "6");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Log.i("123", "123");
                }
                    return true;
                case R.id.navigation_result:
                {
                    Intent intent = new Intent(Activity_testing.this, home2.class);
                    bundle.putString("position", "5");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Log.i("123", "123");
                }
                    return true;

            }
            return false;
        }
    };
    @Override
    public void onBackPressed() {

            Intent intent = new Intent(Activity_testing.this, HomeScreen.class);
            startActivity(intent);
    }
}
