package com.classes.educationclasses.Network;

import com.classes.educationclasses.Model.EducationLevelsModel;
import com.classes.educationclasses.Model.PrimaryModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConnectorService {

    public String BaseURL = "http://192.168.1.121/hmh/api/";

    @GET("Lups")
    Call<ArrayList<EducationLevelsModel>> getLevels();

    @GET("StCounter/{id}")
    Call<ArrayList<PrimaryModel>> getPrimaryLevel(@Path("id") String id);
}
