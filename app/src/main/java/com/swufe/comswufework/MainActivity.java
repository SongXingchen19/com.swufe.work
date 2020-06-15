package com.swufe.comswufework;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

import com.swufe.comswufework.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button showdailogTwo;
    private Button showdailog;
    private Button time;
    //选择日期Dialog
    private DatePickerDialog datePickerDialog;
    //选择时间Dialog
    private TimePickerDialog timePickerDialog;

    private Calendar calendar;
    private final String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showdailogTwo = (Button) findViewById(R.id.showdailogTwo);
        showdailog = (Button) findViewById(R.id.showdailog);
        time = (Button) findViewById(R.id.time);

        time.setOnClickListener(this);
        showdailogTwo.setOnClickListener(this);
        showdailog.setOnClickListener(this);
        calendar = Calendar.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showdailog:
                showDailog();
                break;
            case R.id.showdailogTwo:
                showDialogTwo();
                break;
            case R.id.time:
                showTime();
                break;
        }
    }



    private void showDailog() {
        datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //monthOfYear 得到的月份会减1所以我们要加1
                String time = String.valueOf(year) + "　" + String.valueOf(monthOfYear + 1) + "  " + Integer.toString(dayOfMonth);
                Log.d(TAG, time);
                //day.setText(time);

                //将时间保存到SP里
                SharedPreferences sharedPreferences = getSharedPreferences("time", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("time_you_pick",time);
                editor.commit();
                Log.i(TAG, "时间已保存到SharedPreferences");


            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        //自动弹出键盘问题解决
        datePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    private void showDialogTwo() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_date, null);
        final DatePicker startTime = (DatePicker) view.findViewById(R.id.st);
        final DatePicker endTime = (DatePicker) view.findViewById(R.id.et);
        startTime.updateDate(startTime.getYear(), startTime.getMonth(), 01);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择时间");
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int month = startTime.getMonth() + 1;
                String st = "" + startTime.getYear() + month + startTime.getDayOfMonth();
                int month1 = endTime.getMonth() + 1;
                String et = "" + endTime.getYear() + month1 + endTime.getDayOfMonth();
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //自动弹出键盘问题解决
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void showTime() {
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, Integer.toString(hourOfDay));
                Log.d(TAG, Integer.toString(minute));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
        timePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}