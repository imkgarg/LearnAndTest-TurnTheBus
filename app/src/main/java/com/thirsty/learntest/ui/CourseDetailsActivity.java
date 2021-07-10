package com.thirsty.learntest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thirsty.learntest.R;

public class CourseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button learnButton, testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        testButton = findViewById(R.id.buttonTest);
        learnButton = findViewById(R.id.buttonLearn);

        testButton.setOnClickListener(this);
        learnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLearn:
                Intent i = new Intent(CourseDetailsActivity.this, LearnActivity.class);
                startActivity(i);
                break;
            case R.id.buttonTest:
                Intent intent = new Intent(CourseDetailsActivity.this, TestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
