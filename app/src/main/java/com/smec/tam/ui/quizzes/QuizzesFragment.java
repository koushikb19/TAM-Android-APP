package com.smec.tam.ui.quizzes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smec.tam.R;
import com.smec.tam.ui.events.AdapterEvents;
import com.smec.tam.ui.events.AdapterEventsNew;
import com.smec.tam.ui.events.EventsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class QuizzesFragment extends Fragment {

    List<EventsModel> events = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter = null;

    DatabaseReference rootRef;


    private OnFragmentInteractionListener mListener;

    public QuizzesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quizzes, container, false);
    }


    @Override
    public  void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rootRef = FirebaseDatabase.getInstance().getReference();

        recyclerView = getActivity().findViewById(R.id.rcyview_quiz);
//        layoutManager = new GridLayoutManager(this.getContext(), 2);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        if(adapter!=null) {
            adapter.notifyDataSetChanged();
        }

        rootRef.child("QuizesList").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot eventDS : dataSnapshot.getChildren()) {
                    EventsModel event = eventDS.getValue(EventsModel.class);
                    events.add(event);
                }
                if(adapter!=null)
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //adapter = new AdapterEvents(events, this.getContext());
        adapter = new AdapterEventsNew(recyclerView, events, this.getContext());
        recyclerView.setAdapter(adapter);

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
