package com.vnrvjietedu.edcell.MBIC;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    private ArrayList<String> notifications;
    ListView listView;
    Mydatabase mydatabase;
    ArrayList<String> messages;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Notifications");

        listView= (ListView) findViewById(R.id.notifications_list);
        mydatabase=new Mydatabase(this);

        final Cursor cursor = mydatabase.getMessages();
        String[] from = {Databasehelper.TITLE, Databasehelper.CONTENT, Databasehelper.DATETIME};
        int[] to = {R.id.notification_title, R.id.notification_content, R.id.notification_date};
        CursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.notificationlistitem, cursor, from, to, 0);
        listView.setAdapter(adapter);
        messages = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            messages.add(cursor.getString(cursor.getColumnIndex(Databasehelper.CONTENT)) + "@!@" + cursor.getString(cursor.getColumnIndex(Databasehelper.DATETIME)));
            cursor.moveToNext();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = messages.get(position);
                //do on listview  click listener
            }
        });
        registerForContextMenu(listView);
        adapter.notifyDataSetChanged();

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }






}
