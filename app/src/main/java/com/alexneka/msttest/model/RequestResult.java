package com.alexneka.msttest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResult {

    @SerializedName("results")
    private List<ITunesContent> listResult;

    public RequestResult() {
    }

    public List<ITunesContent> getListResult() {
        return listResult;
    }

    public void setListResult(List<ITunesContent> listResult) {
        this.listResult = listResult;
    }
}
