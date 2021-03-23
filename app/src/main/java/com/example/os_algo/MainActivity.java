package com.example.os_algo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
Toolbar toolbar;
NavigationView navigationView;
DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent));
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    public void pagera(View view) {
        Intent i1=new Intent(this,PageReplacement.class);
        startActivity(i1);
    }

        public void processa(View view) {
            Intent i2=new Intent(this,ProcessScheduling.class);
            startActivity(i2);
    }
    public void disks(View view) {
        Intent i3=new Intent(this,DiskScheduling.class);
        startActivity(i3);
    }

    public void deadlocks(View view) {
        Intent i4=new Intent(this,ConcurrencyDeadlock.class);
        startActivity(i4);
    }

    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    break;

                case R.id.nav_page:
                    Intent intent1 = new Intent(MainActivity.this, PageReplacement.class);
                    startActivity(intent1);
                    break;

                case R.id.nav_disk:
                    Intent intent2 = new Intent(MainActivity.this, DiskScheduling.class);
                    startActivity(intent2);
                    break;

                case R.id.nav_concurrency:
                    Intent intent3 = new Intent(MainActivity.this, ConcurrencyDeadlock.class);
                    startActivity(intent3);
                    break;


            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    }
