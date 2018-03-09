package com.vnrvjiet.edcell.MBIC;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    private ArrayList<String> notifications;
    ListView listView;
    ListView list;
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
        String[] from = {Databasehelper.TITLE,Databasehelper.CONTENT, Databasehelper.DATETIME};
        int[] to = {R.id.notification_title,R.id.notification_content, R.id.notification_date};
        list = (ListView) findViewById(R.id.notifications_list);
        CursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.notificationlistitem, cursor, from, to, 0);
        list.setAdapter(adapter);
        messages = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            messages.add(cursor.getString(cursor.getColumnIndex(Databasehelper.CONTENT)) + "@!@" + cursor.getString(cursor.getColumnIndex(Databasehelper.DATETIME)));
            cursor.moveToNext();
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = messages.get(position);
                //do on listview  click listener
            }
        });
        registerForContextMenu(list);
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
