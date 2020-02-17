package com.classes.educationclasses.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrimaryModel {
    @SerializedName("RID")
    @Expose
    private Integer rID;
    @SerializedName("EduLevelID")
    @Expose
    private Integer eduLevelID;
    @SerializedName("EduLevelTitle")
    @Expose
    private String eduLevelTitle;
    @SerializedName("EduClassID")
    @Expose
    private Integer eduClassID;
    @SerializedName("ClassTitle")
    @Expose
    private String classTitle;
    @SerializedName("BoysCount")
    @Expose
    private Integer boysCount;
    @SerializedName("GirlsCount")
    @Expose
    private Integer girlsCount;
    @SerializedName("TotalCount")
    @Expose
    private Integer totalCount;

    public Integer getRID() {
        return rID;
    }

    public void setRID(Integer rID) {
        this.rID = rID;
    }

    public Integer getEduLevelID() {
        return eduLevelID;
    }

    public void setEduLevelID(Integer eduLevelID) {
        this.eduLevelID = eduLevelID;
    }

    public String getEduLevelTitle() {
        return eduLevelTitle;
    }

    public void setEduLevelTitle(String eduLevelTitle) {
        this.eduLevelTitle = eduLevelTitle;
    }

    public Integer getEduClassID() {
        return eduClassID;
    }

    public void setEduClassID(Integer eduClassID) {
        this.eduClassID = eduClassID;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public Integer getBoysCount() {
        return boysCount;
    }

    public void setBoysCount(Integer boysCount) {
        this.boysCount = boysCount;
    }

    public Integer getGirlsCount() {
        return girlsCount;
    }

    public void setGirlsCount(Integer girlsCount) {
        this.girlsCount = girlsCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
