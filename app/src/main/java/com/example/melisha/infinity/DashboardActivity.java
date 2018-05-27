package com.example.melisha.infinity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout task_list_finish_today = findViewById(R.id.task_list_finish_today);
        LinearLayout task_list_on = findViewById(R.id.task_list_on);
        LinearLayout task_list_all = findViewById(R.id.task_list_all);
        LinearLayout rating_list = findViewById(R.id.rating_list);


        String userType = getIntent().getStringExtra("userType");

        if(userType == "admin"){
            rating_list.setVisibility(View.GONE);
        }
        else if(userType == "emp"){
            task_list_all.setVisibility(View.GONE);
            task_list_on.setVisibility(View.GONE);
        }

        task_list_finish_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),TaskListActivity.class);
                startActivity(intent);
            }
        });

        task_list_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),TaskListActivity.class);
                startActivity(intent);
            }
        });

        task_list_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),TaskListActivity.class);
                startActivity(intent);
            }
        });

        rating_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),RatingListActivity.class);
                startActivity(intent);
            }
        });
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
        getMenuInflater().inflate(R.menu.dashboard, menu);
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
        int id = item.getItemId();

        if (id == R.id.nav_add) {
            Intent intent = new Intent(getApplicationContext(),InsertTaskActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_update) {
            Intent intent = new Intent(getApplicationContext(),UpdateTaskActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_delete) {
            Intent intent = new Intent(getApplicationContext(),DeleteTaskActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_task_list) {
            Intent intent = new Intent(getApplicationContext(),TaskListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_allocated) {
            Intent intent = new Intent(getApplicationContext(),TaskListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_non_allocated) {
            Intent intent = new Intent(getApplicationContext(),TaskListActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_employee_list) {
            Intent intent = new Intent(getApplicationContext(),EmployeeListActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_rating_list) {
            Intent intent = new Intent(getApplicationContext(),RatingListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_about) {
            Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_contact) {
            Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
