package com.example.melisha.infinity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    List<EmployeesRatings> employeeList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewEmployeeActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEmployee);
      recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeListActivity.this));
        progressDialog = new ProgressDialog(EmployeeListActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("employees");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        EmployeesRatings empList = dataSnapshot.getValue(EmployeesRatings.class);
                        Toast.makeText(EmployeeListActivity.this, "" +empList, Toast.LENGTH_SHORT).show();
                        employeeList.add(empList);

                        if(employeeList == null ){
                            Toast.makeText(EmployeeListActivity.this, "No data", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception ex){

                    }

                }


                adapter = new EmployeeListingAdapter(EmployeeListActivity.this, employeeList);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private class EmployeeListingAdapter extends RecyclerView.Adapter<EmployeeListingAdapter.ViewHolder> {

        Context context;
        List<EmployeesRatings> Employees;


        public EmployeeListingAdapter(Context context, List<EmployeesRatings> TempList) {

            this.Employees = TempList;
            this.context = context;
        }

        @Override
        public EmployeeListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
            EmployeeListingAdapter.ViewHolder viewHolder = new EmployeeListingAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(EmployeeListingAdapter.ViewHolder holder, int position) {

            final EmployeesRatings empData = Employees.get(position);
            holder.empName.setText(empData.getEmployeeName());
        }

        @Override
        public int getItemCount() {

            return Employees.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public TextView empName;

            public ViewHolder(View itemView) {

                super(itemView);

                empName = (TextView) itemView.findViewById(R.id.taskID);
            }
        }


    }
    }