package com.Ceri.youtube_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThumbnailYT {
    //This model class is created to fetch important data from
//youtube API
    @SerializedName("medium")
    @Expose
    private MediumThumb medium;


    public ThumbnailYT() {
    }

    public ThumbnailYT(MediumThumb medium) {
        this.medium = medium;
    }

    public MediumThumb getMedium() {
        return medium;
    }

    public void setMedium(MediumThumb medium) {
        this.medium = medium;
    }

    public class MediumThumb {
        @SerializedName("url")
        @Expose
        private String url;

        public MediumThumb() {

        }

        public MediumThumb(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
