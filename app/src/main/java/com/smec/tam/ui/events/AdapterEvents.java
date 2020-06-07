package com.smec.tam.ui.events;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smec.tam.EventDetailsActivity;
import com.smec.tam.R;

import java.util.List;

public class AdapterEvents extends RecyclerView.Adapter<AdapterEvents.MyViewHolder> {

    List<EventsModel> mDataset;
    Context context;

    public AdapterEvents(List<EventsModel> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_events, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.eventName.setText(mDataset.get(position).name);
        Glide.with(context).load(mDataset.get(position).image).into(holder.imageView);
        final String type;
        if(mDataset.get(position).man==1) {
            type = "events";
        }
        else {
            type = "QuizesList";
        }

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mDataset.get(position).interClg) {
                    Intent intent = new Intent(context, EventDetailsActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("type", type);
                    intent.putExtra("isFirst", mDataset.get(position).isfirstYear);
                /*ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) context, holder.imageView, "image");
                context.startActivity(intent, optionsCompat.toBundle());*/
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
                } else {
                    Intent intent = new Intent(context, EventDetailsActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("type", type);
                    intent.putExtra("isFirst", mDataset.get(position).isfirstYear);
                /*ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) context, holder.imageView, "image");
                context.startActivity(intent, optionsCompat.toBundle());*/
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView eventName;
        View v;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.eventName = itemView.findViewById(R.id.txt_event2_name);
            this.imageView = itemView.findViewById(R.id.imageView_events2);
            v = itemView;
        }
    }
}
