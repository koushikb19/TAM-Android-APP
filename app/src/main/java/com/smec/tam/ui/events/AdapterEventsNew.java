package com.smec.tam.ui.events;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smec.tam.InterClgReg;
import com.smec.tam.R;
import com.smec.tam.Registration;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class AdapterEventsNew extends RecyclerView.Adapter<AdapterEventsNew.MyViewHolder> {

    private static final int UNSELECTED = -1;
    RecyclerView recyclerView;
    List<EventsModel> mDataset;
    Context context;
    private int selectedItem = UNSELECTED;

    public AdapterEventsNew(RecyclerView recyclerView, List<EventsModel> mDataset, Context context) {
        this.recyclerView = recyclerView;
        this.mDataset = mDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_events2, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.title.setText(mDataset.get(position).name);
        Glide.with(context).load(mDataset.get(position).image).into(holder.image);
        holder.date.setText(mDataset.get(position).date);
        holder.time.setText(mDataset.get(position).time);
        holder.desc.setText(mDataset.get(position).content);
        holder.arrow.setImageResource(R.drawable.expand_arrow_24px);

        holder.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDataset.get(position).interClg) {
                    Intent newIntent = new Intent(context, InterClgReg.class);
                    newIntent.putExtra("title", mDataset.get(position).name);
                    newIntent.putExtra("isFirst", mDataset.get(position).isfirstYear);
                    context.startActivity(newIntent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
                } else {
                    Intent newIntent = new Intent(context, Registration.class);
                    newIntent.putExtra("title", mDataset.get(position).name);
                    newIntent.putExtra("isFirst", mDataset.get(position).isfirstYear);
                    context.startActivity(newIntent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
                }

            }
        });

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder != null) {
                    holder.up.setSelected(false);
                    holder.expandableLayout.collapse();
                    holder.arrow.setImageResource(R.drawable.expand_arrow_24px);
                }

                if (position == selectedItem) {
                    selectedItem = UNSELECTED;
                } else {
                    holder.up.setSelected(true);
                    holder.expandableLayout.expand();
                    holder.arrow.setImageResource(R.drawable.collapse_arrow_24px);
                    selectedItem = position;
                }
            }
        };

        holder.cardView.setOnClickListener(ocl);

        /*holder.up.setOnClickListener(ocl);
        holder.down.setOnClickListener(ocl);*/

        //holder.image.setOnClickListener(ocl);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements ExpandableLayout.OnExpansionUpdateListener{

        ImageView image;
        ImageView arrow;
        TextView title;
        TextView date;
        TextView time;
        TextView desc;
        Button register;
        ConstraintLayout up;
        ConstraintLayout down;
        ExpandableLayout expandableLayout;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView_events2);
            arrow = itemView.findViewById(R.id.img_event2_arrow);
            title = itemView.findViewById(R.id.txt_event2_name);
            date = itemView.findViewById(R.id.txt_event2_date);
            time = itemView.findViewById(R.id.txt_event2_time);
            desc = itemView.findViewById(R.id.txt_event2_desc);
            register = itemView.findViewById(R.id.btn_event2_register);
            up = itemView.findViewById(R.id.constLyt_events2_up);
            down = itemView.findViewById(R.id.constLyt_events2_down);
            cardView = itemView.findViewById(R.id.cardView_events2);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_events2);
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            Log.d("ExpandableLayout", "State: " + state);
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }
}
