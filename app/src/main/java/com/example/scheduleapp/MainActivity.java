package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private String ProgramValue;
    private int YearValue;
    private String YearValueString;
    private Spinner dropdown;
    public static String EXTRA_TEXT2="com.example.ScheduleApp.EXTRA_TEXT2";
    public static String EXTRA_TEXT3="com.example.ScheduleApp.EXTRA_TEXT3";

    /*
    private class AsyncLoadSchedule extends AsyncTask<Void, Void, Boolean>{


        @Override
        protected void onPreExecute() {
            htmlString=null;
            super.onPreExecute();

        }


        protected Boolean doInBackground(Void... aVoid) {
            getScheduleAPI();

            if(htmlString == null){
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean == true){

            }
            else{

            }
        }
    }


    public void loadSchedulePage(View v){
        Intent i = new Intent(this, ScheduleActivity.class);
        if(htmlString != null){
            i.putExtra(EXTRA_TEXT, htmlString);
            startActivity(i);
        }
    }
*/

    //Launches the ScheduleActivity
    public void displaySchedule(View v){
     //   System.out.println("*************************");
     //   System.out.println("program: " + ProgramValue);
     //   System.out.println("year: " + YearValue);
        Intent i = new Intent(this, ScheduleActivity.class);
       // Toast.makeText(this, "p: " + ProgramValue + " y: " + YearValue, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();

        i.putExtra(EXTRA_TEXT2, ProgramValue);
        YearValueString = Integer.toString(YearValue);
        i.putExtra(EXTRA_TEXT3, YearValueString);
        startActivity(i);
    }

    public void displayAbout(View v){
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }

    // --------- Tidigare funktioner ------------//

    @Override
    public void onClick(View v) {
        System.out.println("View: " + v.getId());
        switch(v.getId()) {
            case R.id.btnSearch:
            //    Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    protected void dropDownYearEntries(){
        dropdown = findViewById(R.id.spinnerYear);

        final ArrayList<Integer> yearList = new ArrayList<>();
        yearList.add(1);
        yearList.add(2);
        yearList.add(3);
        yearList.add(4);
        yearList.add(5);

        ArrayAdapter<Integer> programAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                yearList
        );
        dropdown.setAdapter(programAdapter);

        //Saves the selected spinner value to variable ProgramValue
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                YearValue = Integer.parseInt(parent.getItemAtPosition(pos).toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //populates the dropdown menu and gets the selected value
    protected void dropDownEntries(){
        dropdown = findViewById(R.id.spinnerPrograms);

        final ArrayList<String> programList = new ArrayList<>();
        programList.add("Select Program");
        programList.add("Datateknik - Högskoleingenjör");
        programList.add("Byggteknik - Högskoleingenjör");
        programList.add("Maskinteknik - Högskoleingenjör");
        programList.add("Industriell ekonomi - Högskoleingenjör");
        programList.add("Ind design och produktutv - Högskoleingenjör");
        programList.add("Datateknik - Civilingenjör");
        programList.add("industriell ekonomi - Civilingenjör");
        programList.add("Analysvetenskap. pgm i kemi med inr. forensik");
        programList.add("Matematikerprogrammet");
        programList.add("Måltidsekologprogrammet");
        programList.add("Tekniskt basår");
        programList.add("Masterprogram i kemi med inrikt. miljöforensik");


        ArrayAdapter<String> programAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                programList
        );
        dropdown.setAdapter(programAdapter);

        //Saves the selected spinner value to variable ProgramValue
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ProgramValue = parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /*
    //Launches the ScheduleActivity
    public void displaySchedule(View v){
        System.out.println("program: " + ProgramValue);
        System.out.println("year: " + YearValue);

       // getScheduleAPI();

        Intent i = new Intent(this, ScheduleActivity.class);
        Toast.makeText(this, "html: " + htmlString + "p: " + ProgramValue + " y: " + YearValue, Toast.LENGTH_SHORT).show();

        if(htmlString != null){
            i.putExtra(EXTRA_TEXT, htmlString);
            htmlString=null;
            startActivity(i);
        }
    }*/

    /*
    public String GetStringPicker(){
        GetEndDate();

        if(ProgramValue == "Datateknik - Högskoleingenjör"){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Datateknik+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue == "Byggteknik - Högskoleingenjör"){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Byggteknik+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue == "Maskinteknik - Högskoleingenjör"){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&islutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Maskinteknik+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue == "Industriell ekonomi - Högskoleingenjör"){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Industriell+ekonomi+%C3%A5k+"+ YearValue +"-";
        }
        else if(ProgramValue == "Ind design och produktutv - Högskoleingenjör"){
            return "https://kronox.oru.se/setup/jsp/Schema.jsp?" +
                    "startDatum=idag&slutDatum="+ endDate +"&sprak=SV" +
                    "&sokMedAND=true&forklaringar=false&resurser=p.H%C3%B6gskoleingenj%C3%B6r+-+" +
                    "Ind+design+och+produktutv+%C3%A5k+"+ YearValue +"-";
        }
        else{
            return "";
        }

    }
    */

    /*
    public void getScheduleAPI(){
        String url = GetStringPicker();
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
    */

    /*
    private void cleanHtml(){
        Document doc = Jsoup.parse(htmlString);
        Element parsed = doc.select("table[class=schematabell]").first();
      //  htmlString = parsed.toString() + "<style type=\"text\\css\">p{color:red;}</style>";
      //  System.out.println(htmlString);

    }

    private void GetEndDate(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 30);
        date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        endDate = df.format(date);
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dropDownEntries();
        dropDownYearEntries();
    }
}