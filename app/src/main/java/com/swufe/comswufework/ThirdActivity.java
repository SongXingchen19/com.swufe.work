package com.swufe.comswufework;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "song";

    private Button buttonTime, buttonCalender;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //日历初始化
        calendar = Calendar.getInstance();

        buttonTime = findViewById(R.id.btTime);
        buttonCalender = findViewById(R.id.btCalender);

        buttonTime.setOnClickListener(this);
        buttonCalender.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btTime:
                showTimeDialog();
                break;
            case R.id.btCalender:
                showCalenderDialog();
                break;
        }
    }

    private void showTimeDialog() {
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + "时" + minute + "分";
                Log.e(TAG, "time : " + time);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void showCalenderDialog() {
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String calender = year + "年" + (month + 1) + "月" + dayOfMonth + "日";
                Log.e(TAG, "calender : " + calender);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}