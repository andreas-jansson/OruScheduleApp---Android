package com.example.scheduleapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ScheduleActivity extends AppCompatActivity {
    private String endDate;
    private String ProgramValue;
    private Integer YearValue;
    public String htmlString=null;


    public String GetStringPicker(){
        System.out.println("****####*****");
        System.out.println("GetStringPicker");
        GetEndDate();
        System.out.println("program: " + ProgramValue);
        System.out.println("endDate: " + endDate);


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
                    "startDatum=idag&islutDatum="+ endDate +"&sprak=SV" +
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
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.Civilingenj%C3%B6r+datateknik+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue.equals("industriell ekonomi - Civilingenjör")){
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

    public void getScheduleAPI(String url){
        System.out.println("****####*****");
        System.out.println("GetScheduleAPI");
     //   String url = GetStringPicker();
        System.out.println("****####*****");
        System.out.println("API TIME - url: " + url);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("failure!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    htmlString = response.body().string();
                    // System.out.println(htmlString);
                    System.out.println("success!");
                    cleanHtml();
                }
                else{
                    System.out.println("no success!");
                }
            }
        });
    }

    private void cleanHtml(){
        System.out.println("****####*****");
        System.out.println("cleanHtml");
        Document doc = Jsoup.parse(htmlString);
        Element parsed = doc.select("table[class=schematabell]").first();
        htmlString = parsed.toString();
        //  System.out.println(htmlString);

    }

    private void GetEndDate(){
        System.out.println("****####*****");
        System.out.println("GetEndDate");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 30);
        date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        endDate = df.format(date);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();

        //   System.out.println("****####*****");
        Intent intent = getIntent();
        ProgramValue = intent.getStringExtra(MainActivity.EXTRA_TEXT2);
        YearValue = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_TEXT3));
     //   System.out.println("ProgramValue: " + ProgramValue);
     //   System.out.println("YearValue: " + YearValue);

        String url = GetStringPicker();
     //   System.out.println("***url: " + url);

        if(url.equals("No program selected")){
            //needs clean up
        }
        else if(url.equals("No schedule found")){
            //needs clean up

        }
        else{
            getScheduleAPI(url);
        }

        int timer = 0;

        while((htmlString==null && !url.equals("No program selected") && !url.equals("No schedule found")) || timer > 300){
            //waits for API or 3s, super bad solution :))
            try {
                timer += 10;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

      //  System.out.println("***After while");


        WebView wv = findViewById(R.id.htmlWebView);
        wv.getSettings().setUseWideViewPort(true);
        WebSettings ws = wv.getSettings();

        if(url.equals("No program selected")){
            Toast.makeText(this, "No program selected", Toast.LENGTH_SHORT).show();
            htmlString="<link rel=\"stylesheet\" type=\"text/css\" href=\"styleHtml.css\" /><body><h1 class=\"errorMsg\">No program selected :(</body>";
            wv.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "UTF-8", null);
        }
        else if(url.equals("No schedule found")){
            Toast.makeText(this, "No schedule found", Toast.LENGTH_SHORT).show();
            htmlString="<link rel=\"stylesheet\" type=\"text/css\" href=\"styleHtml.css\" /><body><h1 class=\"errorMsg\">No schedule found :(</body>";
            wv.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "UTF-8", null);
        }
        else{
            cleanHtml();
         //   System.out.println("***htmlString: " + htmlString);
         //   WebView wv = findViewById(R.id.htmlWebView);
         //   wv.getSettings().setUseWideViewPort(true);
            htmlString = "<link rel=\"stylesheet\" type=\"text/css\" href=\"styleHtml.css\" /><body><h1>" + ProgramValue + " year: " + YearValue + "</h1>" + htmlString + "</body>";
          //  System.out.println(htmlString);
            wv.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "UTF-8", null);
           // WebSettings ws = wv.getSettings();
            //ws.setJavaScriptEnabled(true);
        }
        ws.setJavaScriptEnabled(true);




    }
}