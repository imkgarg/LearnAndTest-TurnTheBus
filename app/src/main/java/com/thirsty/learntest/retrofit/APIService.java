package com.thirsty.learntest.retrofit;

import com.thirsty.learntest.models.request.CourseRequest;
import com.thirsty.learntest.models.request.LearningRoutineRequest;
import com.thirsty.learntest.models.response.CourseResponse;
import com.thirsty.learntest.models.response.LearningRoutingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @POST("courses")
    Call<CourseResponse> getCourses(@Body() CourseRequest request);

    @POST("learning_routine")
    Call<LearningRoutingResponse> getLearningRoutine(@Body() LearningRoutineRequest request);

}
