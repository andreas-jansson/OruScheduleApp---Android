package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText("This app is the result of a one week spare time mini project.\n" +
                "It was created to allow easy access to the schedule\n" +
                "and probably contains a lot of bugs\n" +
                "\n:)\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
                "Created by: Andréas Jansson\n" +
                "Datateknik - Högskoleingenjör(2019-2022)\n" +
                "Contact: schedule_app_teknat@protonmail.com");
    }
}