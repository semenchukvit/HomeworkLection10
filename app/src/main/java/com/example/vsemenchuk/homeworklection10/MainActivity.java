package com.example.vsemenchuk.homeworklection10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_STUDENT = 1;
    public static final int REQUEST_CODE_ADD_GROUP = 2;

    public static final String EXTRA_STUDENT = "com.example.vsemenchuk.homeworklection10.extra.student";
    public static final String EXTRA_GROUP = "com.example.vsemenchuk.homeworklection10.extra.group";

    ExpandableListView expListView;
    Button btnAddStudent, btnAddGroup;
    Group group;
    Student student;
    DataBaseHelper dbHelper;
    ExpandableAdapter adapter;
    ArrayList<Group> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = findViewById(R.id.expListView);
        btnAddGroup = findViewById(R.id.btnAddGroup);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        groups = getGroupsWithStudents();

        adapter = new ExpandableAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                android.R.layout.simple_list_item_1,
                groups);

        expListView.setAdapter(adapter);
    }

    public void onClickAdd(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.btnAddGroup:
                intent = new Intent(MainActivity.this, AddGroupActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_GROUP);
                break;
            case R.id.btnAddStudent:
                intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ADD_GROUP) {
            if (resultCode == RESULT_OK) {
                group = data.getParcelableExtra(EXTRA_GROUP);

                dbHelper = new DataBaseHelper(MainActivity.this);
                long id = dbHelper.insertGroup(group);
                groups = getGroupsWithStudents();
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Group add " + id, Toast.LENGTH_SHORT).show();
            }
        } else  if (requestCode == REQUEST_CODE_ADD_STUDENT) {
            if (resultCode == RESULT_OK) {
                group = data.getParcelableExtra(EXTRA_GROUP);
                student = data.getParcelableExtra(EXTRA_STUDENT);

                dbHelper = new DataBaseHelper(MainActivity.this);
                long id = dbHelper.insertStudent(student, group);
                groups = getGroupsWithStudents();
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Student add" + id, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<Group> getGroupsWithStudents(){
        dbHelper = new DataBaseHelper(MainActivity.this);

        ArrayList<Group> groups = dbHelper.getGroups();

        for (int i = 0; i < groups.size(); i++) {
            ArrayList<Student> students = dbHelper.getStudents(groups.get(i));

            groups.get(i).setStudents(students);
        }

        return groups;
    }
}
