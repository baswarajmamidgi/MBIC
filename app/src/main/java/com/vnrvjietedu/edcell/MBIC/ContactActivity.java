package com.vnrvjietedu.edcell.MBIC;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class ContactActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.vnrvjiet.edcell.MBIC.R.layout.activity_contact);
        Toolbar toolbar= (Toolbar) findViewById(com.vnrvjiet.edcell.MBIC.R.id.toolbar);
        toolbar.setTitle("Contact");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final ImageView phone= (ImageView) findViewById(com.vnrvjiet.edcell.MBIC.R.id.phone);
        ImageView email= (ImageView) findViewById(com.vnrvjiet.edcell.MBIC.R.id.email);
        ImageView facebook= (ImageView) findViewById(com.vnrvjiet.edcell.MBIC.R.id.facebook);


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","9490746990",null));
                startActivity(i);

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","edcell@vnrvjiet.in",null));
                startActivity(i);

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/Entrepreneurship-Development-Cell-vnrvjiet-1406731889581180/"));
                startActivity(i);
            }
        });
    }
}
