package com.thirsty.learntest.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.thirsty.learntest.R;
import com.thirsty.learntest.adapters.CourseRecyclerViewAdapter;
import com.thirsty.learntest.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private List<Course> courseList;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CourseRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerCourse);
        layoutManager = new LinearLayoutManager(CourseActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        animator.setAddDuration(3000);
        animator.setRemoveDuration(3000);

        getCourseList();

        recyclerViewAdapter = new CourseRecyclerViewAdapter(
                CourseActivity.this, courseList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void getCourseList() {
        courseList = new ArrayList<>();
        courseList.add(new Course(1, "English"));
        courseList.add(new Course(2, "Hindi"));
        courseList.add(new Course(3, "Maths"));
    }
}