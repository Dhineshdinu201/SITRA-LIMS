package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class home2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    TextView title;
    int pos;
    Fragment fragment=null;
    BottomNavigationView bottomnavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        //bottomnavigationView=(BottomNavigationView)findViewById(R.id.navigationView);
         title=(TextView)findViewById(R.id.text_title);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



//        button click by position
        Bundle bundle = getIntent().getExtras();
        String position=bundle.getString("position");
         pos= Integer.parseInt(position);

        Log.i("position",position);
        if(pos==0){
            Log.i("fragment","chemistry");
            fragment=new Fragment_chemistry();
            title.setText("Test Available-Chemistry");
           // bottomnavigationView.inflateMenu(R.menu.chemistry_navigation);
            loadFragment(fragment);
        }
        if(pos==1){
            Log.i("fragment","physics");
            fragment=new Fragment_physics();
            title.setText("Test Available-Physics");
          //  bottomnavigationView.inflateMenu(R.menu.physics_navigation);
            loadFragment(fragment);
        }
        if(pos==2){
            Log.i("fragment","weaving");
            fragment=new Fragment_Weaving();
            title.setText("Test Available-Weaving");
            loadFragment(fragment);
        }
        if(pos==3){
            Log.i("fragment","weaving");
            fragment=new Fragment_Weaving();
            title.setText("Test Available-Weaving");
            loadFragment(fragment);
        }
        if(pos==4){
            Log.i("fragment","weaving");
            fragment=new Fragment_Weaving();
            toolbar.setTitle("Chemistry");
            loadFragment(fragment);
        }if(pos==5){
            Log.i("fragment","view report");
            fragment=new Fragment_View_report();
            toolbar.setTitle("View Results");
            loadFragment(fragment);
        }if(pos==6){
            Log.i("fragment","member_login");
            fragment=new Fragment_member_login();
            title.setText("Login");
            loadFragment(fragment);
        }if(pos==7){
            Log.i("fragment","contact us");
            fragment=new Fragment_contactus();
            title.setText("Contact Us");
            loadFragment(fragment);
        }if(pos==8){
            Log.i("fragment","payment");
            fragment=new Fragment_payment();
            title.setText("Payments");
            loadFragment(fragment);
        }if(pos==9){
            Log.i("fragment","Test Report Activities");
            fragment=new Fragment_Weaving();
            toolbar.setTitle("Chemistry");
            loadFragment(fragment);
        }
        if(pos==10){
            Log.i("fragment","Update Profile");
            fragment=new Fragment_Weaving();
            toolbar.setTitle("Chemistry");
            loadFragment(fragment);
        }

        if(pos==11){
            Log.i("fragment","sitra");
            fragment=new Fragment_sitra();
            toolbar.setTitle("Chemistry");
            loadFragment(fragment);
        }

        if(pos==12){
            Log.i("fragment","meditech");
            fragment=new Fragment_meditech();
            toolbar.setTitle("Chemistry");
            loadFragment(fragment);
        }

        if(pos==88){
            Log.i("fragment","eng");
            fragment=new Fragment_engg();
            toolbar.setTitle("Engineering");
            title.setText("Test Available-Engineering");
            loadFragment(fragment);
        }
        if(pos==98){
            Log.i("fragment","coe");
            fragment=new Fragment_coe();
            toolbar.setTitle("Center Of Excellence");
            title.setText("Test Available-Center Of Excellence");

            loadFragment(fragment);
        }
    }

    @Override
    public void onBackPressed() {
        if(pos==0||pos==1||pos==2||pos==3||pos==4||pos==98||pos==88) {
            Intent intent = new Intent(home2.this, Activity_testing.class);
            startActivity(intent);
        }
        else {

            Intent intent = new Intent(home2.this, HomeScreen.class);
            startActivity(intent);
        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            Log.i("123","123456");
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            Log.i("backpressed","123456");
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.test_available) {
           Intent intent=new Intent(home2.this,Activity_testing.class);
           startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.test_fac) {
            Intent intent=new Intent(home2.this,TestingFacility.class);
            startActivity(intent);

        } else if (id == R.id.view_res) {
            Log.i("fragment","view report");
            fragment=new Fragment_View_report();
            toolbar.setTitle("View Results");
            loadFragment(fragment);
        } else if (id == R.id.mem_login) {
            Log.i("fragment","member_login");
            fragment=new Fragment_member_login();
            title.setText("Member Login");
            loadFragment(fragment);
        } else if (id == R.id.paym) {
            Log.i("fragment","payment");
            fragment=new Fragment_payment();
            title.setText("Payments");
            loadFragment(fragment);

        } else if (id == R.id.contact) {
            Log.i("fragment","contact us");
            fragment=new Fragment_contactus();
            title.setText("Contact Us");
            loadFragment(fragment);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.commit();
    }

}
