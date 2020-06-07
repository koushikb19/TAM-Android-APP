package com.smec.tam.ui.crew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smec.tam.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMem extends RecyclerView.Adapter<AdapterMem.MyViewHolder> {

    List<CrewMember> mDataset;
    Context context;


    public AdapterMem(List<CrewMember> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_memeber, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(mDataset.get(position).name);
        if (mDataset.get(position).is_head || mDataset.get(position).is_board) {
            holder.desg.setText(mDataset.get(position).designation);
        }
        Glide.with(context).load(mDataset.get(position).img).into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView name;
        TextView desg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.txtView_mem_name);
            this.desg = itemView.findViewById(R.id.txtView_mem_desg);
            this.circleImageView = itemView.findViewById(R.id.img_crdViewMem_dp);
        }
    }
}
