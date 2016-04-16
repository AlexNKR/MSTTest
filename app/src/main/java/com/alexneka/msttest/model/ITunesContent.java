package com.alexneka.msttest.model;

import com.google.gson.annotations.SerializedName;

public class ITunesContent {

    @SerializedName("trackName")
    private String track;

    @SerializedName("artistName")
    private String artist;

    @SerializedName("artworkUrl100")
    private String image;

    public ITunesContent() {
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
