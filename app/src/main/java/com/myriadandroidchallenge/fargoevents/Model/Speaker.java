package com.myriadandroidchallenge.fargoevents.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by disen on 2/11/2019.
 */

public class Speaker implements ISpeaker {


    @SerializedName("first_name")
    String first_name;
    @SerializedName("last_name")
    String last_name;
    @SerializedName("bio")
    String bio;
    public Speaker(String fname,String lname, String bio){
        this.first_name = fname;
        this.last_name = lname;
        this.bio = bio;
    }
    @Override
    public String getFirstName() {
        return first_name;
    }

    @Override
    public String getLastName() {
        return last_name;
    }

    @Override
    public String getBio() {
        return bio;
    }
}
