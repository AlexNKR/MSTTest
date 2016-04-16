package com.alexneka.msttest.api;

import com.alexneka.msttest.model.RequestResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITunesService {

    @GET("search")
    public Call<RequestResult> listITunes(@Query("term") String term);
}
