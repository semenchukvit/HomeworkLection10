package com.example.vsemenchuk.homeworklection10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddGroupActivity extends AppCompatActivity {

    EditText etGroupNameAdd;
    Button bntSaveGroupAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        etGroupNameAdd = findViewById(R.id.etGroupNameAdd);
        bntSaveGroupAdd = findViewById(R.id.bntSaveGroupAdd);
        bntSaveGroupAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String groupName = etGroupNameAdd.getText().toString();
                Group group = new Group(groupName);

                Intent result = new Intent();
                result.putExtra(MainActivity.EXTRA_GROUP, group);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
