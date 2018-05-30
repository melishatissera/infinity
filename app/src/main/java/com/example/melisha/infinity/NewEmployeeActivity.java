package com.example.melisha.infinity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NewEmployeeActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    Button insertBtn;
    EditText EmpName;
    EditText EmpEmail;
    EditText EmpPassword;
    Switch EmpType;

    Boolean isChecked;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        insertBtn = findViewById(R.id.insertBtn);
        EmpName = findViewById(R.id.empName);
        EmpEmail = findViewById(R.id.empEmail);
        EmpPassword = findViewById(R.id.empPassword);
        EmpType = findViewById(R.id.empType);

        isChecked = EmpType.isChecked();

        if(isChecked == true){
type = "admin";
        }
        else {
            type ="emp";
        }
        insertBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v) {
                insertTask();
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

    private void insertTask() {
        final String Email = EmpEmail.getText().toString();
        final String Name = EmpName.getText().toString();
        final String Password = EmpPassword.getText().toString();
        final String Type = type;

        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        final String taskNoGenerated ="123";
        database.child("users").child(taskNoGenerated).addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Users users = dataSnapshot.getValue(Users.class);

                        if (users == null) {
                            Toast.makeText(NewEmployeeActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                        }
                        setEditingEnabled(true);
                        finish();

                        Users NewUser = new Users( Name, Email, Type, Password,"id");
                        database.child("employees").child(Name).setValue(NewUser);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        setEditingEnabled(true);
                    }
                });
        // [END single_value_read]
    }

    private void setEditingEnabled(boolean enabled) {
        EmpName.setEnabled(enabled);
        EmpEmail.setEnabled(enabled);
        EmpType.setEnabled(enabled);
        EmpPassword.setEnabled(enabled);

        if (enabled) {
            insertBtn.setVisibility(View.VISIBLE);
        } else {
            insertBtn.setVisibility(View.GONE);
        }
    }

    private void createNewTask(Integer taskNo, String taskName, String taskDescription, String dueOn, String assignee) {
        String key = database.child("users").push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        database.updateChildren(childUpdates);
    }

}
