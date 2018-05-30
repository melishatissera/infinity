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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
    CustomOnItemSelectedListener customOnItemSelectedListener = new CustomOnItemSelectedListener();
    Button insertBtn;
    Spinner assignee;
    EditText taskNo;
    EditText taskHead;
    EditText taskDetails;
    EditText dueOn;
   public String assgnee;

    EditText taskStatus;
    EditText selectDate;
    private int mYear, mMonth, mDay;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_task);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        insertBtn = findViewById(R.id.insertBtn);
        assignee = findViewById(R.id.spinner);
        taskNo = findViewById(R.id.taskNo);
        taskHead = findViewById(R.id.taskHead);
        taskDetails = findViewById(R.id.taskDetails);
        taskStatus = findViewById(R.id.taskStatus);
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
        final String assigneeField = customOnItemSelectedListener.getAssignee();
        final String taskNoField = taskNo.getText().toString();
        final String taskHeadField = taskHead.getText().toString();
        final String taskDetailsField = taskDetails.getText().toString();
        final String dueOnField = dueOn.getText().toString();
        final String taskStatusField = taskStatus.getText().toString();

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

                        Query myTopPostsQuery = mDatabase.child("users")
                                .orderByChild("starCount");
                        Spinner spinner = (Spinner) findViewById(R.id.spinner);
                        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.planets_array, android.R.layout.simple_spinner_item);
                        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                                  adapter.add(postSnapshot.getKey());
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        spinner = (Spinner) findViewById(R.id.spinner);
                        spinner.setOnItemSelectedListener( customOnItemSelectedListener);

                        setEditingEnabled(true);
                        finish();

                        Tasks tasksInsert = new Tasks(taskNoField, taskHeadField, taskDetailsField, dueOnField, assigneeField, taskStatusField);
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
        String key = database.child("tasks").push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        database.updateChildren(childUpdates);
    }
}


class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
String assignee="";
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
        assignee =  parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public String getAssignee(){
        return this.assignee;
    }

}
