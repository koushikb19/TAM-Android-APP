package com.smec.tam;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.smec.tam.ui.events.EventsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventDetailsActivity extends AppCompatActivity {
    ImageView img;
    TextView date;
    TextView time;
    TextView tit;
    TextView desc;
    Context context;
    EventsModel eventInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        // set an exit transition
        getWindow().setExitTransition(new Fade());
        getSupportActionBar().setTitle("");
        setContentView(R.layout.activity_event_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        img = findViewById(R.id.img_eventDetails);
        date = findViewById(R.id.txt_event2_date);
        time = findViewById(R.id.txt_event2_time);
        tit = findViewById(R.id.txt_eventDetails_title);
        desc = findViewById(R.id.txt_event2_desc);
        context = this;


        final Intent myIntent = getIntent();
        String type = myIntent.getStringExtra("type");
        final int position = myIntent.getIntExtra("position", 0);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventRef = rootRef.child(type);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                DataSnapshot childDsNew = null;
                for(DataSnapshot childDS : dataSnapshot.getChildren()) {
                    if(count == position) {
                        childDsNew = childDS;
                        break;
                    }
                    count++;
                }
                eventInfo = childDsNew.getValue(EventsModel.class);

                Glide.with(context).load(eventInfo.image).into(img);
                date.setText(eventInfo.date);
                time.setText(eventInfo.time);
                tit.setText(eventInfo.name);
                desc.setText(eventInfo.content);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        });

        Button button = findViewById(R.id.btn_eventDetails_register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFirst = myIntent.getBooleanExtra("isFirst", false);
                Intent newIntent = new Intent(context, Registration.class);
                newIntent.putExtra("title", eventInfo.name);
                newIntent.putExtra("isFirst", isFirst);
                context.startActivity(newIntent, ActivityOptions.makeSceneTransitionAnimation(EventDetailsActivity.this).toBundle());
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
