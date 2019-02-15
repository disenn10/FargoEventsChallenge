package com.myriadandroidchallenge.fargoevents.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by disen on 2/9/2019.
 */

public class Events implements IEvents {
    int id;
    String title,image_url,start_date_time,end_date_time,location;
    @SerializedName("event_description")
    String description;
    ArrayList<Events> speakers;
    Boolean featured;

    public Events(int id){
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getImage_url() {
        return image_url;
    }

    @Override
    public String getStartDate() {
        return start_date_time;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getEndDate() {
        return end_date_time;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public Boolean getFeatured() {
        return featured;
    }

    @Override
    public ArrayList<Events> getSpeakers() {
        return speakers;
    }
}
