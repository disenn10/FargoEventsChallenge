package com.myriadandroidchallenge.fargoevents.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myriadandroidchallenge.fargoevents.Presenter.EventListPresenter;
import com.myriadandroidchallenge.fargoevents.Presenter.IEventListPresenter;
import com.myriadandroidchallenge.fargoevents.R;
import com.squareup.picasso.Picasso;

/**
 * Created by disen on 2/10/2019.
 */

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.EventsHolder> {

    private final EventListPresenter eventPresenter;
    Context context;
    public IeventOnclickListener ieventOnclickListener;


    public EventsRecyclerAdapter(EventListPresenter presenter, Context context, IeventOnclickListener ieventOnclickListener) {
        this.eventPresenter = presenter;
        this.context = context;
        this.ieventOnclickListener = ieventOnclickListener;
    }
    public interface IeventOnclickListener{
        void onclicked(int position);
    }


    @Override
    public EventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_items, parent, false));
    }

    @Override
    public void onBindViewHolder(EventsHolder holder, int position) {
        eventPresenter.onBindEventRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return eventPresenter.getEventsRowsCount();
    }

    public class EventsHolder extends RecyclerView.ViewHolder implements IEventListPresenter, View.OnClickListener {
        ImageView eventImageView;
        TextView event_name;
        TextView timeInfos;
        public EventsHolder(View itemView) {
            super(itemView);
            event_name = itemView.findViewById(R.id.event_name);
            timeInfos = itemView.findViewById(R.id.event_time);
            eventImageView = itemView.findViewById(R.id.event_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void setImage(String image) {
            Picasso.with(context).load(image).into(eventImageView);
        }

        @Override
        public void setEventName(String name) {
            event_name.setText(name);
        }

        @Override
        public void setTimeInfos(String infos) {
            timeInfos.setText(infos);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ieventOnclickListener.onclicked(position);
        }
    }
}
