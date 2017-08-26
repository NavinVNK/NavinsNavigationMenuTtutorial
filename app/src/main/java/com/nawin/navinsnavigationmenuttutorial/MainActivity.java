package com.nawin.navinsnavigationmenuttutorial;



import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SubjectMessage {
    private String commMsg = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getTitle()+" title selected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       Fragment fragment = new Message_Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_container, fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            fragment = new Message_Fragment();
        } else if (id == R.id.nav_gallery) {
            fragment = new SMS_Fragment();

        } else if (id == R.id.nav_slideshow) {
            fragment = new EMail_Fragment();

        }
         else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
            setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void setMessage(String str, int frag) {
        commMsg = str;
        Log.d("MainActivity", commMsg);
        Log.d("MainActivity", Integer.valueOf(frag).toString());
        switch (frag) {
            case 1:
                SMS_Fragment sms = (SMS_Fragment) getFragmentManager()
                        .findFragmentById(R.id.sms_fragmentlay);
                if (sms == null) {
                    System.out.println("Dual fragment - 1");
                    sms = new SMS_Fragment();
                    sms.getSMSMessage(commMsg);
                    // We are in dual fragment (Tablet and so on)
                    FragmentManager fm = getFragmentManager();
                    android.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frame_container, sms);
                    ft.commit();
                }

                else {
                    Log.d("SwA", "Dual Fragment update");
                    // sms.getEMailSubject(commMsg);
                }
                break;
            case 2:
                EMail_Fragment email = (EMail_Fragment) getFragmentManager()
                        .findFragmentById(R.id.email_fragmentlay);
                if (email == null) {
                    System.out.println("Dual fragment - 1");
                    email = new EMail_Fragment();
                    email.getEMailMessage(commMsg);
                    // We are in dual fragment (Tablet and so on)
                    FragmentManager fm = getFragmentManager();
                    android.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frame_container, email);
                    ft.commit();
                }

                else {
                    Log.d("SwA", "Dual Fragment update");
                    // sms.getEMailSubject(commMsg);
                }
        }

    }

    public String getMessage() {
        Log.d("MainActivity", commMsg);
        return commMsg;
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }
}
