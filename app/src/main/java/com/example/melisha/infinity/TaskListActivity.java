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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    List<Tasks> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),InsertTaskActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(TaskListActivity.this));
        progressDialog = new ProgressDialog(TaskListActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tasks");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Tasks taskList = dataSnapshot.getValue(Tasks.class);
                        list.add(taskList);
                    }
                    catch (Exception ex){

                    }
                }

                adapter = new ListingAdapter(TaskListActivity.this, list);
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

    private class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {

        Context context;
        List<Tasks> TasksList;

        TaskListActivity taskListActivity = new TaskListActivity();

        public ListingAdapter(Context context, List<Tasks> TempList) {

            this.TasksList = TempList;
            this.context = context;
        }

        @Override
        public ListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_listing, parent, false);
            ListingAdapter.ViewHolder viewHolder = new ListingAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListingAdapter.ViewHolder holder, int position) {

            final Tasks taskData = TasksList.get(position);
            holder.taskNo.setText(taskData.getTaskName());
            holder.taskDue.setText(taskData.getTaskDueOn());
            holder.btnDone.setTag(taskData.getTaskID());
            holder.btnDelete.setTag(taskData.getTaskID());
            holder.btnEdit.setTag(taskData.getTaskID());

            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editTask(taskData.getTaskID());
                }
            });

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteTask(taskData.getTaskID());
                }
            });


        }

        @Override
        public int getItemCount() {

            return TasksList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public TextView taskNo;
            public TextView taskDue;
            public ImageButton btnDelete;
            public ImageButton btnEdit;
            public Button btnDone;

            public ViewHolder(View itemView) {

                super(itemView);

                taskNo = (TextView) itemView.findViewById(R.id.taskID);
                taskDue = (TextView) itemView.findViewById(R.id.taskDueOn);
                btnDelete = itemView.findViewById(R.id.btnDelete);
                btnDone = itemView.findViewById(R.id.btnDone);
                btnEdit = itemView.findViewById(R.id.btnEdit);
            }
        }


    }
    private void editTask(String Value){
        Toast.makeText(getApplicationContext(), Value, Toast.LENGTH_SHORT).show();
        String getValue = Value;
        Intent intent = new Intent(getApplicationContext(),UpdateTaskActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", getValue);
        startActivity(intent);
    }

    private void deleteTask(String Value){
        Toast.makeText(getApplicationContext(), Value, Toast.LENGTH_SHORT).show();
        String getValue = Value;
        Intent intent = new Intent(getApplicationContext(),DeleteTaskActivity.class);
        intent.putExtra("ID", getValue);
        startActivity(intent);
    }
}