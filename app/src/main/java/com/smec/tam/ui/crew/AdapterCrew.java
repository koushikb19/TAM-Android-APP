package com.smec.tam.ui.crew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smec.tam.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCrew extends RecyclerView.Adapter<AdapterCrew.MyViewHolder> {

    List<Department> mDataset;
    Context context;
    int numColumns = 3;

    public AdapterCrew(List<Department> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCrew.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_board, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCrew.MyViewHolder holder, int position) {

        holder.dept.setText(mDataset.get(position).department);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(holder.recyclerViewChild.getContext(), numColumns);
        holder.recyclerViewChild.setLayoutManager(layoutManager);
        holder.recyclerViewChild.setAdapter(new AdapterMem(mDataset.get(position).members, context));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dept;
        RecyclerView recyclerViewChild;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dept = itemView.findViewById(R.id.txtView_crew_dept);
            this.recyclerViewChild = itemView.findViewById(R.id.rcyview_mem);
        }
    }
}
