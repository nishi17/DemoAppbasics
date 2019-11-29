package com.demo.demoappbasic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.demoappbasic.R;
import com.demo.demoappbasic.fragment.FirstFragment;
import com.demo.demoappbasic.fragment.SecondRecyclerFragment;
import com.demo.demoappbasic.menu.Context_menuActivity;
import com.demo.demoappbasic.menu.Option_menuActivity;
import com.demo.demoappbasic.menu.Popup_menuActivity;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout dLayout;
    private ImageView ev_navIcon;
    private NavigationView navigationView;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        init();

    }

    private void init() {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ev_navIcon = (ImageView) findViewById(R.id.iv_navicon);
        ev_navIcon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (R.id.iv_navicon) {

            case R.id.iv_navicon:
                setNavigationDrawer();
                dLayout.openDrawer(Gravity.LEFT);


                break;
        }


    }

    private void setNavigationDrawer() {

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment frag = null; // create a Fragment Object
                int itemId = item.getItemId(); // get selected menu item's id
// check selected menu item's id and replace a Fragment Accordingly
                if (itemId == R.id.it_add) {
                    //frag = new SecondListFragment();
                    frag = new SecondRecyclerFragment();


                } else if (itemId == R.id.it_list) {

                    frag = new FirstFragment();

                } else if (itemId == R.id.it_logout) {

                    Intent a = new Intent(NavigationActivity.this, Option_menuActivity.class);
                    startActivity(a);
                } else if (itemId == R.id.it_context_menu) {

                    Intent a = new Intent(NavigationActivity.this, Context_menuActivity.class);
                    startActivity(a);
                } else if (itemId == R.id.it_popup_menu) {

                    Intent a = new Intent(NavigationActivity.this, Popup_menuActivity.class);
                    startActivity(a);
                }else if (itemId == R.id.it_service) {

                    Intent a = new Intent(NavigationActivity.this, Service_Activity.class);
                    startActivity(a);
                }


                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                if (frag != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
                    transaction.addToBackStack(null);
                    transaction.commit(); // commit the changes
                    dLayout.closeDrawers(); // close the all open Drawer Views
                    return true;
                }

                return false;
            }
        });


    }


    @Override
    public void onBackPressed() {

      /*  int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 1000);
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }*/

        if (dLayout.isDrawerOpen(Gravity.LEFT)) {

            dLayout.closeDrawers();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }

    }


}
