package com.example.melisha.infinity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateTaskActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference("tasks");

    Button updateBtn;

    EditText taskId;
    EditText assignee;
    EditText taskHead;
    EditText taskDetails;
    EditText dueOn;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("EXTRA_SESSION_ID");
        taskId = findViewById(R.id.taskID);
        assignee = findViewById(R.id.taskAssignee);
        taskHead = findViewById(R.id.taskHeading);
        taskDetails = findViewById(R.id.taskDetails);
        dueOn = findViewById(R.id.taskDueOn);

        updateBtn = findViewById(R.id.btnUpdate);
        taskId.setText(id);


        updateBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v) {
                updateTask(taskId.getText().toString().trim());
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


    public void updateTask(String id){
        database.child(id).child("taskAssignee").setValue(assignee.getText().toString().trim());
       database.child(id).child("taskName").setValue(taskHead.getText().toString().trim());
       database.child(id).child("taskDesc").setValue(taskDetails.getText().toString().trim());
       database.child(id).child("taskDueOn").setValue(dueOn.getText().toString().trim());
        finish();
        Toast.makeText(this, "Updated..", Toast.LENGTH_SHORT).show();
    }
}
