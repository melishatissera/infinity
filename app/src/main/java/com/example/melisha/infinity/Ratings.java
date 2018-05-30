package com.example.melisha.infinity;

import android.media.Rating;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Ratings {

    float RatingValue;
    String RatedBy;
    String RatedTask;

    public float getRatingValue() {
        return RatingValue;
    }

    public void setRatingValue(float ratingValue) {
        RatingValue = ratingValue;
    }

    public String getRatedBy() {
        return RatedBy;
    }

    public void setRatedBy(String ratedBy) {
        RatedBy = ratedBy;
    }

    public String getRatedTask() {
        return RatedTask;
    }

    public void setRatedTask(String ratedTask) {
        RatedTask = ratedTask;
    }

    public Ratings( float ratingValue, String ratedBy, String ratedTask){
        this.RatingValue = ratingValue;
        this.RatedBy = ratedBy;
        this.RatedTask = ratedTask;
    }
}
