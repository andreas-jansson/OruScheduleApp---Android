package com.example.scheduleapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import java.time.LocalDate;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScheduleActivity extends AppCompatActivity {
    private String endDate;
    private String ProgramValue;
    private Integer YearValue;
    private Integer DateValue;
    public String htmlString=null;
    public String url=null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static class ThisLocalizedWeek {

        // Try and always specify the time zone you're working with
        private final static ZoneId TZ = ZoneId.of("Europe/Stockholm");

        private final Locale locale;
        private final DayOfWeek firstDayOfWeek;
        private final DayOfWeek lastDayOfWeek;

        public ThisLocalizedWeek(final Locale locale) {
            this.locale = locale;
            this.firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
            this.lastDayOfWeek = DayOfWeek.of(((this.firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
        }

        public LocalDate getFirstDay() {
            return LocalDate.now(TZ).with(TemporalAdjusters.previousOrSame(this.firstDayOfWeek));
        }

        public LocalDate getLastDay() {
            return LocalDate.now(TZ).with(TemporalAdjusters.nextOrSame(this.lastDayOfWeek));
        }

        @Override
        public String toString() {
            return String.format(   "The %s week starts on %s and ends on %s",
                    this.locale.getDisplayName(),
                    this.firstDayOfWeek,
                    this.lastDayOfWeek);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String GetStringPicker(){
        GetEndDate();

        if(ProgramValue.equals("Datateknik - Högskoleingenjör") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Datateknik+%C3%A5k+"+ YearValue + "-";
        }
        else if(ProgramValue.equals("Byggteknik - Högskoleingenjör") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Byggteknik+%C3%A5k+"+ YearValue + "-";
        }
        else if(ProgramValue.equals("Maskinteknik - Högskoleingenjör") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Maskinteknik+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue.equals("Industriell ekonomi - Högskoleingenjör") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Industriell+ekonomi+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue.equals("Ind design och produktutv - Högskoleingenjör") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Ind+design+och+produktutv+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue.equals("Datateknik - Civilingenjör")){
            if(YearValue == 1 || YearValue == 2){
                return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                        "&sokMedAND=true&forklaringar=false&resurser=p.Civilingenj%C3%B6r+datateknik+%C3%A5k+"+ YearValue;
            }
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.Civilingenj%C3%B6r+datateknik+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue.equals("Industriell ekonomi - Civilingenjör")){

            if(YearValue == 1 || YearValue == 2) {
                return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                        "&sokMedAND=true&forklaringar=false&resurser=p.Civilingenj%C3%B6r+industriell+ekonomi+%C3%A5k+"+ YearValue;
            }
                return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.Civilingenj%C3%B6r+industriell+ekonomi+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue.equals("Biologiprogrammet") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate + "&sprak=SV" +
                    "&sokMedAND=true&forklaringar=true&resurser=p.Biologiprogrammet+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue.equals("Analysvetenskap. pgm i kemi med inr. forensik") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.Analysvetenskap.+pgm+i+kemi+med+inr.+forensik+%C3%A5k+" + YearValue;
        }
        else if(ProgramValue.equals("Matematikerprogrammet") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.Matematikerprogrammet+%C3%A5k+" + YearValue;
        }
        else if(ProgramValue.equals("Måltidsekologprogrammet") && (YearValue == 1 || YearValue == 2 || YearValue == 3)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.M%C3%A5ltidsekologprogrammet+%C3%A5k+" + YearValue;
        }
        else if(ProgramValue.equals("Tekniskt basår") && YearValue == 1){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.Tekniskt+bas%C3%A5r";
        }
        else if(ProgramValue.equals("Masterprogram i kemi med inrikt. miljöforensik") && (YearValue == 1 || YearValue == 2)){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=true&resurser=p.Masterprogram+i+kemi+med+inrikt.+milj%C3%B6forensik+%C3%A5k"+ YearValue;
        }
        else if(ProgramValue.equals("Select Program")){
            return "No program selected";
        }
        else{
            return "No schedule found";
        }

    }

    class Async extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    return;
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()){
                        htmlString = response.body().string();
                    }
                    else{
                    //    System.out.println("no success!");
                    }
                }
            });
            return null;
        }
    }

    public void getScheduleAPI2(String url){
    //    System.out.println("****####*****");
     //   System.out.println("GetScheduleAPI");
     //   String url = GetStringPicker();
     //   System.out.println("****####*****");
     //   System.out.println("API TIME - url: " + url);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
       //        System.out.println("failure!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    htmlString = response.body().string();
                   // cleanHtml();
                }
                else{
               //     System.out.println("no success!");
                }
            }
        });
    }

    private void cleanHtml(){
     //   System.out.println("****####*****");
     //   System.out.println("cleanHtml");
        Document doc = Jsoup.parse(htmlString);
        Element parsed = doc.select("table[class=schematabell]").first();
        if(parsed != null) {
            htmlString = parsed.toString();
            cleanHtmlPretty();

        }
        else{
    //        System.out.println("***Setting null");
            htmlString=null;
            return;
        }
        //  System.out.println(htmlString);
    }

    private void cleanHtmlPretty(){
        boolean sameDay=false;
    //    System.out.println("****####*****");
    //    System.out.println("PrettyHtml");

        Document doc = Jsoup.parse(htmlString);
        doc.select("td[class=blank]").remove();
        doc.select("td[class=vecka data]").remove();
        Elements parsed = doc.select("td");

        Integer index = 1;

        String customHtml="<table class=\"schemaTabell\" cellspacing=\"0\" cellpadding=\"0\" >";

        for (Element element : parsed) {
        if(index == 1){
            //do nothing
        }
        else if(index == 2){
            if(element.text().equals("")){
         //       System.out.println("dag e: " + element.text());
          //      customHtml = customHtml + "<tr><td class=\"cell empty day-date\">" + element.text(); //day
                sameDay=true;
            }
            else{
     //           System.out.println("dag f: " + element.text());
                customHtml = customHtml + "<tr><td class=\"cell day-date\">" + element.text(); //day
            }
        }
        else if(index == 3){
     //       System.out.println("datum: " + element.text());
            customHtml = customHtml + element.text() + "</td></tr>"; //date
        }
        else if(index == 4){
      //          System.out.println("tid: " + element.text());
            if(sameDay==true){
                customHtml = customHtml + "<tr><td class=\"cell sameDay time\">" + element.text() + "</td></tr>"; //start-end time
            }
            else{
                customHtml = customHtml + "<tr><td class=\"cell time\">" + element.text() + "</td></tr>"; //start-end time
            }
            sameDay=false;
        }
        else if(index == 5){
        //        System.out.println("Klass: " + element.text());
                customHtml = customHtml + "<tr><td class=\"cell class\">" + element.text() + "</td></tr>"; //class

        }
        else if(index == 6){
            //do nothing
        }
        else if(index == 7){
   //         System.out.println("Lokal: " + element.text());
            if(element.text().equals("")){

            }
            else{
                customHtml = customHtml + "<tr><td class=\"cell location\">" + element.text() + "</td></tr>"; //location
            }
        }
        else if(index == 8){
            //Do nothing
        }
        else if(index == 9){
  //          System.out.println("Lokal: " + element.text());
            customHtml = customHtml + "<tr><td class=\"cell class-desc\">" + element.text() + "</td></tr>"; //class description
        }
        else if(index == 10){
          //  customHtml = customHtml + "<tr><td class=\"cell spacer\"></td></tr><tr><td class=\"cell spacer\"></td></tr>"; //spacing between days
            index=0;
        }
        index++;
    }
        htmlString = customHtml + "</table>";
    //    System.out.println("*** CUSTOM ***");
    //    System.out.println(htmlString);


    }

    private void GetEndDate(){
   //     System.out.println("****####*****");
    //    System.out.println("GetEndDate");
        Date date = new Date();
        // System.out.println("date: " + date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if(DateValue == 0){
            endDate = "intervallTyp=d&intervallAntal=1";
            return;
        }
        else if(DateValue == 1){
        //    System.out.println("*** 1st day ***");
            ThisLocalizedWeek test = new ThisLocalizedWeek(Locale.FRANCE);
            System.out.println("EndDate sun!: " + test.getLastDay());
            endDate=test.getLastDay().toString();
            return;
        }
        else{
            cal.setTime(date);
            cal.add(Calendar.DATE, 30);
        }

        date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        endDate = df.format(date);
   //     System.out.println("*** EndDate ***");
    //    System.out.println("enddate: " + endDate);



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void GetEndDate2(){
     //   System.out.println("****####*****");
     //   System.out.println("GetEndDate");

        LocalDate myObj = LocalDate.now();
      //  System.out.println("date now: " + myObj);


        if(DateValue == 0){
            endDate = "intervallTyp=d&intervallAntal=1";
            return;
        }
        else if(DateValue == 1){
       //     System.out.println("*** 1st day ***");

        }
        else{

        }

      /*  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        endDate = df.format();*/
     //   System.out.println("*** EndDate ***");
     //   System.out.println("enddate: " + endDate);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();

        WebView browser = (WebView) findViewById(R.id.htmlWebView);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);


        Intent intent = getIntent();
        ProgramValue = intent.getStringExtra(MainActivity.EXTRA_TEXT2);
        YearValue = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_TEXT3));
        DateValue = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_TEXT4));


        url = GetStringPicker();
        System.out.println("url: " + url);

        if(url.equals("No program selected")){
            //needs clean up
        }
        else if(url.equals("No schedule found")){
            //needs clean up

        }
        else{
            Async call = new Async();
            call.execute();

        }

        int timer = 0;
        while(((htmlString==null && !url.equals("No program selected") && !url.equals("No schedule found"))) && (timer < 300)){
            //waits for API or 3s, super bad solution :))
            try {
                timer += 10;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        WebView wv = findViewById(R.id.htmlWebView);
        wv.getSettings().setUseWideViewPort(true);
        WebSettings ws = wv.getSettings();
        if(htmlString != null){
            cleanHtml();
        }

        if(url.equals("No program selected")){
            Toast.makeText(this, "No program selected", Toast.LENGTH_SHORT).show();
            htmlString="<link rel=\"stylesheet\" type=\"text/css\" href=\"styleHtml.css\" /><body><h1 class=\"errorMsg\">No program selected :(</body>";
            wv.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "UTF-8", null);
        }
        else if(url.equals("No schedule found") || htmlString == null){
            Toast.makeText(this, "No schedule found", Toast.LENGTH_SHORT).show();
            htmlString="<link rel=\"stylesheet\" type=\"text/css\" href=\"styleHtml.css\" /><body><h1 class=\"errorMsg\">No schedule found :(</body>";
            wv.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "UTF-8", null);
        }
        else{
          //  cleanHtml();
            htmlString = "<link rel=\"stylesheet\" type=\"text/css\" href=\"styleHtml.css\" /><body><div class=\"header\"><h1>" + ProgramValue + "<br> year: " + YearValue + "</h1></div>" + htmlString + "</body>";
            wv.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "UTF-8", null);
        }
        ws.setJavaScriptEnabled(true);
    }
}