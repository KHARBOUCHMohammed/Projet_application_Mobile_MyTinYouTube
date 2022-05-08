package com.Ceri.youtube_api;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.Ceri.youtube_api.adapter.Fragment_Pager_Adapter;
import com.Ceri.youtube_api.databinding.ActivityMainscreenBinding;

public class Mainscreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //creating important variables
    private ActivityMainscreenBinding binding;
    public TextView HomeFragement, FavouriteFragement, ConfigurationFragement;
    ViewPager viewPager;
    Fragment_Pager_Adapter pagerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Setting up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting up Navigation menu
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.cardview_light_background));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(Mainscreen.this);


//Three different functions
        //this function initialise all variables
        initialise();
        //this function is use to make changes in tab while moving from
        //one fragment to another
        method_call();
        //this method setup onclick listener so that we can move
        //clicking on buttons
        start_tab_working();

    }


    private void start_tab_working() {

        //Setting up click listeners on textviews
        HomeFragement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        FavouriteFragement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        ConfigurationFragement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });

    }

    //handling changes while moving the fragments
    private void method_call() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                if (position == 0) {
                    HomeFragement.setTextColor(Color.parseColor("#D20336"));
                    FavouriteFragement.setTextColor(Color.parseColor("#2D2C2C"));
                    ConfigurationFragement.setTextColor(Color.parseColor("#2D2C2C"));
                }
                if (position == 1) {
                    FavouriteFragement.setTextColor(Color.parseColor("#D20336"));
                    HomeFragement.setTextColor(Color.parseColor("#2D2C2C"));
                    ConfigurationFragement.setTextColor(Color.parseColor("#2D2C2C"));


                }
                if (position == 2) {
                    ConfigurationFragement.setTextColor(Color.parseColor("#D20336"));
                    FavouriteFragement.setTextColor(Color.parseColor("#2D2C2C"));
                    HomeFragement.setTextColor(Color.parseColor("#2D2C2C"));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }

        });

    }

    // intialising all important variables
    private void initialise() {
        HomeFragement = findViewById(R.id.home);
        FavouriteFragement = findViewById(R.id.favourite);
        ConfigurationFragement = findViewById(R.id.configuration);
        viewPager = findViewById(R.id.fragment_container);
        pagerViewAdapter = new Fragment_Pager_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);


    }

    //this method will call after changing settings
    //so that all changes will apply on search result
    public void refreshUI() {
        finish();
        overridePendingTransition(-1, -1);
        startActivity(getIntent());
        overridePendingTransition(-1, -1);
    }

    //This function is for navigation items
    //Like share , setting etc
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.favourite) {
            viewPager.setCurrentItem(1);
        }
        if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        if (id == R.id.settings) {
            viewPager.setCurrentItem(2);
        }
        if (id == R.id.exit) {
            //This section is use to close the app
            //after clicking on exit app in navigation menu
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Mainscreen.this);
            alertDialogBuilder.setTitle("Exit Application?");
            alertDialogBuilder
                    .setMessage("Click yes to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


        return false;
    }
}