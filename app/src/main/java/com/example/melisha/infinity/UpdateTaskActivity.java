package com.example.melisha.infinity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UpdateTaskActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference("tasks");

    Button updateBtn;
    TextView taskId;

    EditText assignee;
    EditText taskHead;
    EditText taskDetails;
    EditText dueOn;

    EditText selectDate;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("EXTRA_SESSION_ID");
        taskId = findViewById(R.id.taskID);
        taskId.setText(id);


        getData();

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

    public void getData(){

        Query query = database.orderByChild("tasks").equalTo(id);
       query.addChildEventListener(new ChildEventListener() {

           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               System.out.print(dataSnapshot.getValue());
               Map<String, Object> value =(Map<String, Object>)dataSnapshot.getValue();
               String name = String.valueOf(value.get("taskName"));

               System.out.print("Name is name " +name);
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            }
        );
    }
}
