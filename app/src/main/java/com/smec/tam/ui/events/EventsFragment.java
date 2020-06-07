package com.smec.tam.ui.events;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smec.tam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    List<EventsModel> events = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter = null;


    DatabaseReference rootRef;
    private EventsViewModel eventsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventsViewModel =
                ViewModelProviders.of(this).get(EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        return root;
    }

    public void onViewCreated(final View view, Bundle savesInstanceState) {
        super.onViewCreated(view, savesInstanceState);

        rootRef = FirebaseDatabase.getInstance().getReference();

        recyclerView = getActivity().findViewById(R.id.rcyview_event);
//        layoutManager = new GridLayoutManager(this.getContext(), 2);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        if(adapter!=null) {
            adapter.notifyDataSetChanged();
        }

        final ProgressDialog progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading");
        progressDialog.show();

        rootRef.child("events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot eventDS : dataSnapshot.getChildren()) {
                    EventsModel event = eventDS.getValue(EventsModel.class);
                    events.add(event);
                }
                if(adapter!=null)
                    adapter.notifyDataSetChanged();

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

       /* adapter = new AdapterEvents(events, this.getContext());
        recyclerView.setAdapter(adapter);*/

       adapter = new AdapterEventsNew(recyclerView, events, this.getContext());
       recyclerView.setAdapter(adapter);
    }

    public void onResume() {
        super.onResume();

    }
}