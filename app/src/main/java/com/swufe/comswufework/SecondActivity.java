package com.swufe.comswufework;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input_time;
    private Button getTime,startTime,stopTime;
    private TextView time;
    private int i = 0;
    private Timer timer = null;
    private TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
        initView();
    }

    private void initView(){
        input_time = (EditText) findViewById( R.id.input_time );
        getTime = (Button) findViewById( R.id.get_time );
        startTime = (Button) findViewById( R.id.start_time );
        stopTime = (Button) findViewById( R.id.stop_time );
        time = (TextView) findViewById( R.id.time );
        getTime.setOnClickListener( this );
        stopTime.setOnClickListener( this );
        startTime.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_time:
                time.setText( input_time.getText().toString() );
                i = Integer.parseInt( input_time.getText().toString() );
                break;
            case R.id.start_time:
                StartTime();
                break;
            case R.id.stop_time:
                StopTime();
                break;
        }
    }


    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            time.setText(msg.arg1+"");
            StartTime();
        };
    };

    public void StartTime(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                i--;
                Message message = mHandler.obtainMessage();
                message.arg1 = i;
                mHandler.sendMessage( message );
            }
        };
        timer.schedule( task, 1000 );
    }

    public void StopTime(){
        timer.cancel();
    }

}