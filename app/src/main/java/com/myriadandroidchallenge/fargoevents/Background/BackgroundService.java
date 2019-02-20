package com.myriadandroidchallenge.fargoevents.Background;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.myriadandroidchallenge.fargoevents.EventDetailActivity;
import com.myriadandroidchallenge.fargoevents.Model.Events;
import com.myriadandroidchallenge.fargoevents.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by disen on 2/14/2019.
 */

public class BackgroundService extends IntentService {
    EventDetailInfos eventDetailInfos;
    public static interface EventDetailInfos {
        public void getEventDetailInfos(Response<Events> eventsResponse);
    }

    public BackgroundService() {
        super("");
        eventDetailInfos = EventDetailActivity.getEventInfos;

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Call<Events> call = RetrofitClient
                .getInstance()
                .getApi()
                .GetEvent(EventDetailActivity.token,EventDetailActivity.id);
        try {
            Response<Events> event = call.execute();
            eventDetailInfos.getEventDetailInfos(event);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
