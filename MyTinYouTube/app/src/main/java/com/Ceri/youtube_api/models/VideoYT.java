package com.Ceri.youtube_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoYT {
    //This model class is created to fetch important data from
//youtube API

    @SerializedName("id")
    @Expose
    private VideoID id;

    @SerializedName("snippet")
    @Expose
    private SnippetYT snippet;

    public VideoYT() {
    }

    public VideoYT(VideoID id, SnippetYT snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    //getters and setters
    public VideoID getId() {
        return id;
    }

    public void setId(VideoID id) {
        this.id = id;
    }

    public SnippetYT getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetYT snippet) {
        this.snippet = snippet;
    }
}
