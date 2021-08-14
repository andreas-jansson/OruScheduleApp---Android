package com.example.scheduleapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;

public class ScheduleActivity extends AppCompatActivity {

    private void displaySchedule(){

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Intent intent = getIntent();
        String htmlText = intent.getStringExtra(MainActivity.EXTRA_TEXT);
       // TextView txtView = findViewById(R.id.scheduleTxtView);
      //  txtView.setMovementMethod(new ScrollingMovementMethod());
      //  txtView.setText(Html.fromHtml(htmlText));

        WebView wv= findViewById(R.id.htmlWebView);
        wv.getSettings().setUseWideViewPort(true);

        htmlText = "<link rel=\"stylesheet\" type=\"text/css\" href=\"styleHtml.css\" /><body>" + htmlText+ "</body>";
        wv.loadDataWithBaseURL("file:///android_asset/", htmlText, "text/html", "UTF-8", null);
        WebSettings ws = wv.getSettings();
      //  wv.loadUrl("file:///android_asset/htmlFile.html");
     //   wv.loadData(htmlText, "text/html", "UTF-8");
        ws.setJavaScriptEnabled(true);

    }
}