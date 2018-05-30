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

public class DashboardActivityManger extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_manger);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout task_assign = findViewById(R.id.task_assign);
        LinearLayout task_non_allocated = findViewById(R.id.task_non_allocated);
        LinearLayout task_completed = findViewById(R.id.task_completed);
        LinearLayout task_ongoing = findViewById(R.id.task_ongoing);


        String userType = getIntent().getStringExtra("userType");

        task_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),TaskListActivity.class);
                startActivity(intent);
            }
        });

        task_non_allocated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),NonAssignTaskListActivity.class);
                startActivity(intent);
            }
        });

        task_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),CompletedTaskListMangerActivity.class);
                startActivity(intent);
            }
        });

        task_ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),OnGoingMangerTaskListActivity.class);
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

        if (id == R.id.nav_add_task) {
            Intent intent = new Intent(getApplicationContext(),InsertTaskActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_update_task) {
            Intent intent = new Intent(getApplicationContext(),UpdateTaskActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_delete_task) {
            Intent intent = new Intent(getApplicationContext(),DeleteTaskActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_task_list) {
            Intent intent = new Intent(getApplicationContext(),TaskListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_allocated) {
            Intent intent = new Intent(getApplicationContext(),ViewMangerAssignTaskListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_non_allocated) {
            Intent intent = new Intent(getApplicationContext(),NonAssignTaskListActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_ongoing_list) {
            Intent intent = new Intent(getApplicationContext(),OnGoingMangerTaskListActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_completed_list) {
            Intent intent = new Intent(getApplicationContext(),CompletedTaskListMangerActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_employee_list) {
            Intent intent = new Intent(getApplicationContext(),EmployeeListActivity.class);
            startActivity(intent);

        }


        else if (id == R.id.nav_about) {
            Intent intent = new Intent(getApplicationContext(),AboutUs.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_contact) {
            Intent intent = new Intent(getApplicationContext(),ContactUs.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_completed_list){
            Intent intent = new Intent(getApplicationContext(), CompletedTaskListMangerActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
