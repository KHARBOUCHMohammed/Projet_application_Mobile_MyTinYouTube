package com.Ceri.youtube_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoID {
    //This model class is created to fetch important data from
//youtube API
    @SerializedName("videoId")
    @Expose
    private String videoId;

    public VideoID() {
    }

    //getters and setters
    public VideoID(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }


}
