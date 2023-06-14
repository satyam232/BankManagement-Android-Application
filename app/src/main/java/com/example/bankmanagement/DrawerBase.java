package com.example.bankmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class DrawerBase extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    AlertDialog.Builder builder,builder2;


    @Override
    public void setContentView(View view) {


        drawerLayout= (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container=drawerLayout.findViewById(R.id.container);
        container.addView(view);
        super.setContentView(drawerLayout);
        toolbar=drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        NavigationView navigationView=drawerLayout.findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        toggle.syncState();




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        builder=new AlertDialog.Builder(DrawerBase.this);

        switch ((item.getItemId())){

            case R.id.logoutitem:

                builder.setTitle("Alert!").setMessage("are you sure, you want to logout?").setCancelable(true)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
                                SharedPreferences.Editor ed=sp.edit();
                                ed.clear();
                                ed.apply();
                                Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                                startActivity(intent);
                                finish();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                overridePendingTransition(0,0);
                break;
            case R.id.contactus:
                Intent intent=new Intent(DrawerBase.this,contactus.class);
                startActivity(intent);


                overridePendingTransition(0,0);
                break;
            case R.id.home:
                Intent intent1=new Intent(DrawerBase.this,mainpage.class);
                startActivity(intent1);
                finish();
                overridePendingTransition(0,0);
                break;

            case R.id.optsettings:
                Intent intent2=new Intent(DrawerBase.this,settings.class);
                startActivity(intent2);


                overridePendingTransition(0,0);
                break;

        }


        return false;


    }
    protected void allocateActivityTitle(String titleString){
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(titleString);
        }
    }



}

