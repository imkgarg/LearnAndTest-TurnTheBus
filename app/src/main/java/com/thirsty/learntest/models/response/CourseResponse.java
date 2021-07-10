package com.thirsty.learntest.models.response;

import com.thirsty.learntest.models.Course;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private List<Course> courses;

}
