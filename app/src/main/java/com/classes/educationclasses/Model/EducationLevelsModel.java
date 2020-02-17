package com.classes.educationclasses.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducationLevelsModel {

    @SerializedName("EduLevelID")
    @Expose
    private Integer eduLevelID;
    @SerializedName("EduLevelTitle")
    @Expose
    private String eduLevelTitle;
    @SerializedName("DisplayOrder")
    @Expose
    private Integer displayOrder;
    @SerializedName("LevelCode")
    @Expose
    private Integer levelCode;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(Integer levelCode) {
        this.levelCode = levelCode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
