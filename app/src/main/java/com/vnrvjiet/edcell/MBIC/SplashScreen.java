package com.vnrvjiet.edcell.MBIC;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET}
                        , 10);
                return;
            }
        }

        */
        Mydatabase dataBase = new Mydatabase(this);


        if (getIntent().getExtras() != null) {

            String title = getIntent().getExtras().getString("title");
            String content = getIntent().getExtras().getString("content");
            if (title == null || content == null) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            Log.i("log", title + content);
            dataBase.insertMessage(title, content);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Permissions denied.You can't access all features ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
        }


    }
}
