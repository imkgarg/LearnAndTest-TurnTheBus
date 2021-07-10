package com.thirsty.learntest.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thirsty.learntest.R;
import com.thirsty.learntest.models.Course;
import com.thirsty.learntest.ui.CourseActivity;
import com.thirsty.learntest.ui.CourseDetailsActivity;
import com.thirsty.learntest.utils.Constants;

import java.lang.ref.WeakReference;
import java.util.List;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "CourseRecyclerViewAdapter";

    private List<Course> courseList;
    private CourseActivity courseActivity;
    private Boolean isEmptyList;

    public CourseRecyclerViewAdapter(CourseActivity courseActivity,
                                     List<Course> courseList) {
        this.courseList = courseList;
        this.courseActivity = courseActivity;
        this.isEmptyList = this.courseList.size() == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (this.isEmptyList) {
            return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.empty_layout, parent, false));
        }

        return new CourseViewHolder(courseActivity, LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (!this.isEmptyList) {
            Course course = courseList.get(position);

            CourseViewHolder reviewViewHolder = (CourseViewHolder) holder;
            reviewViewHolder.courseName.setText(course.getName());

        } else {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            emptyViewHolder.emptyTextView.setText("No Course Found!!! Pease retry");
        }
    }

    @Override
    public int getItemCount() {
        return this.isEmptyList ? 1 : courseList.size();
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {

        TextView emptyTextView;

        public EmptyViewHolder(View view) {
            super(view);
            emptyTextView = view.findViewById(R.id.tvEmpty);
        }

    }

    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        TextView courseName;

        public CourseViewHolder(final Context context, final View view) {
            super(view);
            this.context = context;

            courseName = view.findViewById(R.id.tvItemCourseName);
            courseName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tvItemCourseName) {
                Course course = courseList.get(getAdapterPosition());
                Intent intent = new Intent(context, CourseDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.INTENT_KEY_COURSE_ID, course.getId());
                bundle.putString(Constants.INTENT_KEY_COURSE_NAME, course.getName());
                intent.putExtra(Constants.INTENT_KEY_COURSE_BUNDLE, bundle);

                context.startActivity(intent);
            }
        }
    }

}


