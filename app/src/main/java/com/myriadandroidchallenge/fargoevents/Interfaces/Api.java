package com.myriadandroidchallenge.fargoevents.Interfaces;

import com.myriadandroidchallenge.fargoevents.Model.Event;
import com.myriadandroidchallenge.fargoevents.Model.Events;
import com.myriadandroidchallenge.fargoevents.Model.Speaker;
import com.myriadandroidchallenge.fargoevents.Model.User;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by disen on 2/6/2019.
 */

public interface Api {

    //crrate user once user successfully login
    @FormUrlEncoded
    @POST("login/")
    Call<User> CreateUser(
            @Field("Username") String Username,
            @Field("Password") String Password
    );
    //get all events
    @GET("events/")
    Call<ArrayList<Events>>GetEvents(
            @Header("authorization") String authorization
    );

    //retrieve specific event
    @GET("events/{id}/")
    Call<Events> GetEvent(
            @Header("authorization") String authorization,
            @Path("id") int id
    );
    //get the specific speaker
    @GET("speakers/{id}/")
    Call<Speaker> GetSpeaker(
            @Header("authorization") String authorization,
            @Path("id") int id
    );
}
