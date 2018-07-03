package com.vnrvjietedu.edcell.MBIC;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    EditText teamlead_name,email,phone,roll_number,branch,year,section,idea_title,idea_description,participants_names;
    Button send;
    public static final String NAME="entry.1585832008";
    public static final String EMAIL="entry.304399361";
    public static final String PHONE="entry.903858721";
    public static final String ROLL_NUMBER="entry.1729685493";
    public static final String BRANCH="entry.534319138";
    public static final String YEAR="entry.11315948";
    public static final String SECTION="entry.969258304";
    public static final String IDEA_TITLE="entry.652481584";
    public static final String IDEA="entry.684732879";
    public static final String PARTICIPANTS="entry.1426881545";
    public static final String URL="https://docs.google.com/forms/d/e/1FAIpQLSc8bq-OzHrvJieDVAdBPp5eO4Hd2Oq8tPbSrby_7DmIWamA7w/formResponse";

    AlertDialog dialog;
    ProgressDialog dialogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.vnrvjiet.edcell.MBIC.R.layout.layout_main_activity);


        Toolbar toolbar=findViewById(com.vnrvjiet.edcell.MBIC.R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("MBIC");
        }
        else {
            try {
                getActionBar().setTitle("MBIC");
            }
            catch (Exception e)
            {
                Log.i("error",e.getLocalizedMessage());
            }
        }

        teamlead_name=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.name);
        participants_names=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.name2);

        email=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.email);
        phone=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.phone);
        roll_number=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.rollno);
        branch=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.branch);
        year=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.Year);
        section=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.section);
        idea_title=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.idea_title);
        idea_description=(EditText)findViewById(com.vnrvjiet.edcell.MBIC.R.id.idea_description);
        send=(Button)findViewById(com.vnrvjiet.edcell.MBIC.R.id.send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postdata post =new postdata();ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getActiveNetworkInfo();
                final boolean isconnected = info != null && info.isConnectedOrConnecting();
                if(!isconnected){
                    Toast.makeText(MainActivity.this, "No Internet...", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (teamlead_name.getText().toString().trim().length() == 0) {
                    teamlead_name.setError("Name required");
                    return;
                }

                if (email.getText().toString().trim().length() == 0) {
                    email.setError("Email ID required");
                    return;


                }

                if (phone.getText().toString().trim().length() == 0) {
                    phone.setError("Phone Number required");
                    return;

                }

                if (roll_number.getText().toString().trim().length() == 0) {
                    roll_number.setError("Roll No required");
                    return;

                }
                if (branch.getText().toString().trim().length() == 0) {
                    branch.setError("Branch required");
                    return;

                }
                if (year.getText().toString().trim().length() == 0) {
                    year.setError("Year required");
                    return;

                }
                if (section.getText().toString().trim().length() == 0) {
                    section.setError("Section required");
                    return;

                }
                if (idea_title.getText().toString().trim().length() == 0) {
                    idea_title.setError("Idea Title  required");
                    return;

                }
                if (idea_description.getText().toString().trim().length() == 0) {
                    idea_description.setError("Idea Description required");
                    return;

                }

                String participants_name=participants_names.getText().toString();

                post.execute(URL,teamlead_name.getText().toString(),email.getText().toString(),phone.getText().toString(),roll_number.getText().toString(),branch.getText().toString(),year.getText().toString(),section.getText().toString(),idea_title.getText().toString(),idea_description.getText().toString(),participants_name);


            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(com.vnrvjiet.edcell.MBIC.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.vnrvjiet.edcell.MBIC.R.string.navigation_drawer_open, com.vnrvjiet.edcell.MBIC.R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.vnrvjiet.edcell.MBIC.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.vnrvjiet.edcell.MBIC.R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== com.vnrvjiet.edcell.MBIC.R.id.menu_contact){
            Toast.makeText(this, "No Activity available", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case com.vnrvjiet.edcell.MBIC.R.id.notifications: {
                Intent i = new Intent(MainActivity.this, Notifications.class);
                startActivity(i);
                break;
            }

            case com.vnrvjiet.edcell.MBIC.R.id.comingevent: {
                Intent i = new Intent(MainActivity.this, EventsList.class);
                startActivity(i);
                break;
            }
            case com.vnrvjiet.edcell.MBIC.R.id.about: {
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
                break;
            }
            case com.vnrvjiet.edcell.MBIC.R.id.contact: {
                Intent i = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(i);
                break;
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(com.vnrvjiet.edcell.MBIC.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }




    private  class postdata extends AsyncTask<String,Integer, Boolean>{
        String name,email,phone,roll_no,branch,year,section,idea_title,idea_description,participantsname;



        @Override
        protected void onPreExecute() {
            dialogs = new ProgressDialog(MainActivity.this);

            dialogs.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialogs.setMessage("Posting Idea , please wait.");
            dialogs.show();
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean result = false;
            this.name=strings[1];
            this.email=strings[2];
            this.phone=strings[3];
            this.roll_no=strings[4];
            this.branch=strings[5];
            this.year=strings[6];
            this.section=strings[7];
            this.idea_title=strings[8];
            this.idea_description=strings[9];
            this.participantsname = strings[10];
            String postBody="";
            try{
                postBody = NAME +"=" + URLEncoder.encode(name,"UTF-8") +
                        "&" + EMAIL+ "=" + URLEncoder.encode(email,"UTF-8") +
                        "&" + PHONE + "=" + URLEncoder.encode(phone,"UTF-8")
                        +
                        "&" + ROLL_NUMBER + "=" + URLEncoder.encode(roll_no,"UTF-8")+
                        "&" + BRANCH + "=" + URLEncoder.encode(branch,"UTF-8")+
                        "&" + YEAR + "=" + URLEncoder.encode(year,"UTF-8")+
                        "&" + SECTION + "=" + URLEncoder.encode(section,"UTF-8")+
                        "&" + IDEA_TITLE + "=" + URLEncoder.encode(idea_title,"UTF-8")+
                        "&" + IDEA + "=" + URLEncoder.encode(idea_description,"UTF-8")+
                        "&" + PARTICIPANTS + "=" + URLEncoder.encode(participantsname,"UTF-8");
            }
            catch (UnsupportedEncodingException ex){
                result=false;
            }
            try{
                //Create OkHttpClient for sending request
                OkHttpClient client = new OkHttpClient();
                //Create the request body with the help of Media Type
                RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(URL)
                        .post(body)
                        .build();
                //Send the request
                Response response = client.newCall(request).execute();
                result = response.isSuccessful();
            }catch (IOException exception){
                result=false;
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            dialogs.dismiss();
            
            if(result) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Success!");
                alertDialog.setMessage("Your idea has been submiteed");

                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finishAffinity();
                    }
                });

                dialog = alertDialog.create();
                dialog.show();
            }
            else {
                Toast.makeText(MainActivity.this, "Error Occurred, please try again ", Toast.LENGTH_SHORT).show();
            }



        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialogs!=null) {
            dialogs.dismiss();
        }
        if(dialog!=null) {
            dialog.dismiss();
        }
    }
}
