package com.myriadandroidchallenge.fargoevents.Model;

import java.util.ArrayList;

/**
 * Created by disen on 2/9/2019.
 */

public interface IEvents {
    int getID();
    String getTitle();
    String getImage_url();
    String getStartDate();
    String getDescription();
    String getEndDate();
    String getLocation();
    Boolean getFeatured();
    ArrayList<Events> getSpeakers();
}
