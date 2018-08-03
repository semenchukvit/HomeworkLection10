package com.example.vsemenchuk.homeworklection10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    EditText etFirstNameAdd, etLastNameAdd, etAgeAdd;
    Button btnSaveStudentAdd;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etFirstNameAdd = findViewById(R.id.etFirstNameAdd);
        etLastNameAdd = findViewById(R.id.etLastNameAdd);
        etAgeAdd = findViewById(R.id.etAgeAdd);
        btnSaveStudentAdd = findViewById(R.id.btnSaveStudentAdd);
        spinner = findViewById(R.id.spinner);

        DataBaseHelper dbHelper = new DataBaseHelper(AddStudentActivity.this);
        ArrayList<Group> groups = dbHelper.getGroups();

        ArrayAdapter<Group> adapter = new ArrayAdapter<Group>(
                AddStudentActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                groups
        );

        spinner.setAdapter(adapter);

        btnSaveStudentAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = etFirstNameAdd.getText().toString();
                String lastName = etLastNameAdd.getText().toString();
                int age = Integer.valueOf(etAgeAdd.getText().toString());

                Group group = (Group) spinner.getSelectedItem();
                Student student = new Student(firstName, lastName, age);

                Intent result = new Intent();
                result.putExtra(MainActivity.EXTRA_STUDENT, student);
                result.putExtra(MainActivity.EXTRA_GROUP, group);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
