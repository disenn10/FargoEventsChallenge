package com.myriadandroidchallenge.fargoevents.Presenter;

import android.content.Context;

import com.myriadandroidchallenge.fargoevents.Model.Events;
import com.myriadandroidchallenge.fargoevents.Utilities.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by disen on 2/10/2019.
 */

public class EventListPresenter {

    private final ArrayList<Events> eventsArrayList;

    public EventListPresenter(ArrayList<Events> eventsArrayList) {
        this.eventsArrayList = eventsArrayList;
    }
    public void onBindEventRowViewAtPosition(int position, IEventListPresenter rowView) {
        Events event = eventsArrayList.get(position);
        rowView.setEventName(event.getTitle());
        rowView.setImage(event.getImage_url());
        rowView.setTimeInfos(Utils.getDate(event.getStartDate())+" - "+Utils.getDate(event.getEndDate()));
    }

    public int getEventsRowsCount() {
        return eventsArrayList.size();
    }


}
