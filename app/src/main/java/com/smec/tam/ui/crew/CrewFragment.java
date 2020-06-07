package com.smec.tam.ui.crew;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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

public class CrewFragment extends Fragment {

    private CrewViewModel crewViewModel;
    List<Department> departments = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter = null;

    DatabaseReference rootRef, demoRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        crewViewModel =
                ViewModelProviders.of(this).get(CrewViewModel.class);
        View root = inflater.inflate(R.layout.fragment_crew, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        crewViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootRef = FirebaseDatabase.getInstance().getReference();

        recyclerView = view.findViewById(R.id.rcyview_crew);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final ProgressDialog progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading");
        progressDialog.show();




        rootRef.child("crew").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot newDS = noteDataSnapshot.child("members");
                    List<CrewMember> crewMembers = new ArrayList<>();
                    for(DataSnapshot memDS : newDS.getChildren()) {
                        CrewMember crewMember = memDS.getValue(CrewMember.class);
                        crewMembers.add(crewMember);
                    }
                    DataSnapshot deptDS = noteDataSnapshot.child("department");
                    Object dept = deptDS.getValue();
                    String deptName = dept.toString();
                    Department department = new Department(deptName, crewMembers);
                    departments.add(department);
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

        adapter = new AdapterCrew(departments, this.getContext());
        recyclerView.setAdapter(adapter);
    }
}