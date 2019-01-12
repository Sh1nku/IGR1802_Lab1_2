package com.andy.lab12;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String DATETIME_FORMAT = "dd.MM.yyyy HH:mm";
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATETIME_FORMAT);
    Calendar calendarNow;
    String fromDateText;
    TextView calculatingText;
    TextView timeDifference;
    EditText dateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get current time
        calendarNow = Calendar.getInstance();
        fromDateText = dateTimeFormat.format(calendarNow.getTime());

        setContentView(R.layout.activity_main);

        //Set top text to show the time used for calculating
        calculatingText = findViewById(R.id.calculatingText);
        calculatingText.setText(getString(R.string.calculatingTextStr) + " " + fromDateText);
        timeDifference = findViewById(R.id.result);
        dateEdit = findViewById(R.id.dateEdit);
    }

    public void calculateTimeDifference(View view) {
        try {
            Date fromDate = dateTimeFormat.parse(fromDateText);
            Date toDate = dateTimeFormat.parse(dateEdit.getText().toString() + " 00:00");
            boolean negative = false;
            long difference;
            if(fromDate.getTime() > toDate.getTime()) {
                negative = true;
                difference = fromDate.getTime() - toDate.getTime();
            }
            else {
                difference = toDate.getTime() - fromDate.getTime();
            }
            long seconds = difference / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            timeDifference.setText(String.format("%dd %dh %dm",days,hours % 24,minutes % 60));
            if(negative) {
                timeDifference.setBackgroundColor(Color.RED);
            }
            else {
                timeDifference.setBackgroundColor(Color.GREEN);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            timeDifference.setText("Error in input");
        }

    }
}
