package com.myriadandroidchallenge.fargoevents.Presenter;

import com.myriadandroidchallenge.fargoevents.Model.Events;
import com.myriadandroidchallenge.fargoevents.Model.Speaker;

import java.util.ArrayList;

/**
 * Created by disen on 2/11/2019.
 */

public class SpeakerListPresenter {
    private ArrayList<Speaker> speakersArrayList;

    public SpeakerListPresenter(ArrayList<Speaker> speakerArrayList) {
        this.speakersArrayList = speakerArrayList;
    }
    public void onBindEventRowViewAtPosition(int position, ISpeakerListPresenter rowView) {
        Speaker speaker = speakersArrayList.get(position);
        rowView.setBio(speaker.getBio());
        rowView.setName(speaker.getFirstName()+"  "+speaker.getLastName());
    }

    public int getEventsRowsCount() {
        return speakersArrayList.size();
    }
}
