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

public class DeleteTaskActivity extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference("tasks");

    Button deleteBtn;
    EditText taskId;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        taskId = findViewById(R.id.taskID);
        deleteBtn = findViewById(R.id.btnDelete);
        taskId.setText(id);

        deleteBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v) {
                deleteTask(taskId.getText().toString().trim());
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

    public void deleteTask(String id) {

        database.child(id).removeValue();
        finish();
        Toast.makeText(this, "DELETED..", Toast.LENGTH_SHORT).show();
    }
}
