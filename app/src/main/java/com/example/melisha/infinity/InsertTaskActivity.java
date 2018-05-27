package com.example.melisha.infinity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class InsertTaskActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference tasks;

    Button insertBtn;
    EditText assignee;
    EditText taskNo;
    EditText taskHead;
    EditText taskDetails;
    EditText dueOn;

    EditText selectDate;
    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        insertBtn = findViewById(R.id.insertBtn);
        assignee = findViewById(R.id.assignee);
        taskNo = findViewById(R.id.taskNo);
        taskHead = findViewById(R.id.taskHead);
        taskDetails = findViewById(R.id.taskDetails);
        dueOn = findViewById(R.id.dueOn);

        selectDate=(EditText)findViewById(R.id.dueOn);
        selectDate.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v) {
                timePicker();
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v) {
                insertTask();
            }
        });
    }

    public void timePicker() {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
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

    private void insertTask() {
        final String assigneeField = assignee.getText().toString();
        final String taskNoField = taskNo.getText().toString();
        final String taskHeadField = taskHead.getText().toString();
        final String taskDetailsField = taskDetails.getText().toString();
        final String dueOnField = dueOn.getText().toString();

        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        final String taskNoGenerated ="123";
        database.child("tasks").child(taskNoGenerated).addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Tasks tasks = dataSnapshot.getValue(Tasks.class);

                        if (tasks == null) {
                            Toast.makeText(InsertTaskActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                        }
                        setEditingEnabled(true);
                        finish();

                        Tasks tasksInsert = new Tasks(taskNoField, taskHeadField, taskDetailsField, dueOnField, assigneeField);
                        database.child("tasks").child(taskNoField).setValue(tasksInsert);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                      setEditingEnabled(true);
                    }
                });
        // [END single_value_read]
    }

    private void setEditingEnabled(boolean enabled) {
        assignee.setEnabled(enabled);
        taskNo.setEnabled(enabled);
        taskHead.setEnabled(enabled);
        taskDetails.setEnabled(enabled);
        dueOn.setEnabled(enabled);

        if (enabled) {
            insertBtn.setVisibility(View.VISIBLE);
        } else {
            insertBtn.setVisibility(View.GONE);
        }
    }

    private void createNewTask(Integer taskNo, String taskName, String taskDescription, String dueOn, String assignee) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = database.child("tasks").push().getKey();
     //   Tasks post = new Tasks(taskNo, taskName, taskDescription, dueOn, assignee);
      //  Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
      //  childUpdates.put("/posts/" + key, postValues);
      //  childUpdates.put("/user-posts/" + taskNo + "/" + key, postValues);

        database.updateChildren(childUpdates);
    }
}
