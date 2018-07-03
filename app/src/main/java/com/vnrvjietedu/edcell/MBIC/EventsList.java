package com.vnrvjietedu.edcell.MBIC;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventsList extends AppCompatActivity {
    private EventAdapter adapter;
    private DatabaseReference mDatabase;
    private ValueEventListener valueEventListener;
    private RecyclerView recyclerView;
    private List<EventClass> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.vnrvjiet.edcell.MBIC.R.layout.activity_eventslist);
        Toolbar toolbar= (Toolbar) findViewById(com.vnrvjiet.edcell.MBIC.R.id.toolbar);
        toolbar.setTitle("Upcoming Events");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase=database.getReference("events");
        final ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        final boolean isconnected = info != null && info.isConnectedOrConnecting();
        final ProgressDialog progressDialog=new ProgressDialog(EventsList.this);
        progressDialog.setMessage(getString(com.vnrvjiet.edcell.MBIC.R.string.LOADING_DATA));
        progressDialog.show();
        recyclerView= (RecyclerView) findViewById(com.vnrvjiet.edcell.MBIC.R.id.recycler_view);
        list=new ArrayList<>();
        adapter=new EventAdapter(this,list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        valueEventListener=mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    Map<String, String> map = (Map)postsnapshot.getValue();
                    EventClass eventClass = new EventClass(map.get("title"), map.get("content"));
                    list.add(eventClass);
                    adapter.notifyDataSetChanged();
                    //progressDialog.dismiss();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView.setAdapter(adapter);
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setMessage("No Upcoming Events");
        if(list.isEmpty()){
            //alertDialog.show();
        }

        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(!list.isEmpty()){
                    progressDialog.dismiss();
                    handler.removeCallbacks(this);
                }
                handler.post(this);

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapter.getItemCount()==0){
                    if(!isconnected) {
                        Toast.makeText(EventsList.this, com.vnrvjiet.edcell.MBIC.R.string.NO_INTERNET, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(EventsList.this, "No Events Found", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(EventsList.this,MainActivity.class));
                        finishAffinity();
                    }

                }
            }
        },3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabase.removeEventListener(valueEventListener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
