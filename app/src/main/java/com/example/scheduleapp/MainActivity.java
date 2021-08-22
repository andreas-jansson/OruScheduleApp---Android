package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private String ProgramValue;
    private int YearValue;
    private String YearValueString;
    private String DateValueString;
    private Spinner dropdownProgram;
    private Spinner dropdownYear;
    public static String EXTRA_TEXT2="com.example.ScheduleApp.EXTRA_TEXT2";
    public static String EXTRA_TEXT3="com.example.ScheduleApp.EXTRA_TEXT3";
    public static String EXTRA_TEXT4="com.example.ScheduleApp.EXTRA_TEXT4";
    private String savedProgram;
    private Integer savedYear;
    private boolean savedIsChecked=false;
    private Integer savedDate=2; //0 today, 1 week, 2 30 days
    private boolean isChecked=false;
    RadioGroup radioGroup;
    RadioButton btnDate;


    SharedPreferences sp;

    //Launches the ScheduleActivity
    public void displaySchedule(View v){
      //  isChecked = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
        System.out.println("****** Searching ********");
        System.out.println("Program: " + ProgramValue);
        System.out.println("Year: " + YearValue);

        saveSettings(v);
        Intent i = new Intent(this, ScheduleActivity.class);
        Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();

        i.putExtra(EXTRA_TEXT2, ProgramValue);
        YearValueString = Integer.toString(YearValue);
        DateValueString= Integer.toString(savedDate);
        i.putExtra(EXTRA_TEXT3, YearValueString);
        i.putExtra(EXTRA_TEXT4, DateValueString);

        startActivity(i);
    }

    public void displayAbout(View v){
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }

    public void saveSettings(View v){
        isChecked = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if(!ProgramValue.equals("Select Program") && ProgramValue != null && isChecked == true){
            savedProgram = ProgramValue;
            savedYear = YearValue;
            savedIsChecked = isChecked;
            savedDate=checkDateRadio(v);
            editor.putString("program", savedProgram);
            editor.putInt("year", savedYear);
            editor.putBoolean("checked", savedIsChecked);
            editor.putInt("date", savedDate);

            editor.commit();

            System.out.println("******SAVING!******");
            System.out.println("program: " + savedProgram);
            System.out.println("year: " + savedYear);
            System.out.println("checked: " + savedIsChecked);
            System.out.println("date: " + savedDate);

        }
        else{
            savedIsChecked = false;
            savedYear = 0;
            savedProgram = "";
            savedDate=2;
            editor.putString("program", savedProgram);
            editor.putInt("year", savedYear);
            editor.putBoolean("checked", savedIsChecked);
            editor.putInt("date", savedDate);
            editor.commit();

            System.out.println("******SAVING! FALSE ******");
            System.out.println("program: " + savedProgram);
            System.out.println("year: " + savedYear);
            System.out.println("checked: " + savedIsChecked);
            System.out.println("date: " + savedDate);

        }
    }

    public void loadSettings() {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String program = sp.getString("program", "");
        Integer year = sp.getInt("year", 0);
        Boolean checked = sp.getBoolean("checked", false);
        Integer date = sp.getInt("date", 2);
        System.out.println("******LOADING!******");
        System.out.println("program: " + savedProgram);
        System.out.println("year: " + savedYear);
        System.out.println("checked: " + savedIsChecked);
        System.out.println("date: " + date);

        if(!program.equals("")){
            savedProgram = program;
            savedYear = year;
            savedIsChecked = checked;
            savedDate = date;
            if(savedIsChecked == true){

                for (int i=0;i<dropdownProgram.getCount();i++){
                    if (dropdownProgram.getItemAtPosition(i).toString().equalsIgnoreCase(savedProgram)){
                        dropdownProgram.setSelection(i);
                        break;
                    }
                }

                for (int i=0;i<dropdownYear.getCount();i++){
                    if (dropdownYear.getItemAtPosition(i) == savedYear){
                        dropdownYear.setSelection(i);
                        break;
                    }
                }
                CheckBox box = ((CheckBox) findViewById(R.id.checkBox));
                box.setChecked(true);

              //  setDateRadio();
                ((RadioButton) radioGroup.getChildAt(savedDate)).setChecked(true);


            }

        }
    }

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
        dropdownYear = findViewById(R.id.spinnerYear);

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
        dropdownYear.setAdapter(programAdapter);

        //Saves the selected spinner value to variable ProgramValue
        dropdownYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                YearValue = Integer.parseInt(parent.getItemAtPosition(pos).toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //populates the dropdown menu and gets the selected value
    protected void dropDownEntries(){
        dropdownProgram = findViewById(R.id.spinnerPrograms);

        final ArrayList<String> programList = new ArrayList<>();
        programList.add("Select Program");
        programList.add("Analysvetenskap. pgm i kemi med inr. forensik");
        programList.add("Byggteknik - Högskoleingenjör");
        programList.add("Datateknik - Högskoleingenjör");
        programList.add("Industriell ekonomi - Högskoleingenjör");
        programList.add("Ind design och produktutv - Högskoleingenjör");
        programList.add("Maskinteknik - Högskoleingenjör");
        programList.add("Datateknik - Civilingenjör");
        programList.add("Industriell ekonomi - Civilingenjör");
        programList.add("Masterprogram i kemi med inrikt. miljöforensik");
        programList.add("Matematikerprogrammet");
        programList.add("Måltidsekologprogrammet");
        programList.add("Tekniskt basår");


        ArrayAdapter<String> programAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                programList
        );
        dropdownProgram.setAdapter(programAdapter);

        //Saves the selected spinner value to variable ProgramValue
        dropdownProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ProgramValue = parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    protected Integer checkDateRadio(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();
        btnDate = findViewById(radioID);
        System.out.println("**** btnDate: " + btnDate.getText());
        if(btnDate.getText().equals("Today           ")){
            return 0;
        }
        else if(btnDate.getText().equals("This Week")){
            return 1;
        }
        else if(btnDate.getText().equals("30 days upcoming")){
            return 2;
        }
        return 2;
    }

    protected void setDateRadio() {
        ((RadioButton) radioGroup.getChildAt(savedDate)).setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);

        dropDownEntries();
        dropDownYearEntries();
        loadSettings();
       // setDateRadio();
    }
}