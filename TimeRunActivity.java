package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.example.timetracker.core.TaskReport;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class TimeRunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_run);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            TaskReport taskReport = (TaskReport) arguments.getSerializable("TaskReport");
            String dealName = arguments.getString("Deal");
            long timeScip = taskReport.getWorkTime();
            new CountDownTimer(timeScip,1000 ) {
                @Override
                public void onTick(long millisUntilFinished) {
                    TextView textTime = (TextView) findViewById(R.id.textTime);
                    String time = String.format("%02d:%02d:%02d",(int) (millisUntilFinished/1000/60/60),
                            (int) (millisUntilFinished/1000/60),
                            (millisUntilFinished/1000));
                    textTime.setText(time);
                }

                @Override
                public void onFinish() {
                    Intent intent = new Intent(TimeRunActivity.this, ReportActivity.class);
                    intent.putExtra("TaskReport",taskReport);
                    intent.putExtra("Deal",dealName);
                    startActivity(intent);
                }
            }.start();
        }
        else{
            Intent intent = new Intent(TimeRunActivity.this, TimerActivity.class);
            startActivity(intent);
        }
    }
}