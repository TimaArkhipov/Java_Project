package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.example.timetracker.core.TaskReport;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class TimeRunActivity extends AppCompatActivity {
    long sec=0;
    private Button pause;
    boolean stop_start=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_run);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            TaskReport taskReport = (TaskReport) arguments.getSerializable("TaskReport");
            String dealName = arguments.getString("Deal");
            sec= taskReport.getWorkTime();




            Handler handler=new Handler();
            TextView timeText = (TextView) findViewById(R.id.textTime);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int h=(int)sec/3600000;
                    int m=(int)sec%3600000/60000;
                    int s=(int)sec%60000/1000;
                    String time= String.format("%02d:%02d:%02d",h,m,s);
                    timeText.setText(time);
                    if (stop_start)
                        sec=sec-1000;
                    handler.postDelayed(this,1000);
                    if (sec==0)
                    {

                        Intent intent = new Intent(TimeRunActivity.this, ReportActivity.class);
                        intent.putExtra("TaskReport",taskReport);
                        intent.putExtra("Deal",dealName);
                        startActivity(intent);

                    }

                }
            });

            Button end=(Button) findViewById(R.id.endButton);
            end.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    TaskReport tr = new TaskReport(taskReport.getDateStart(),
                            taskReport.getDateStop(),
                            sec);
                    Intent intent = new Intent(TimeRunActivity.this, ReportActivity.class);
                    intent.putExtra("TaskReport",tr);
                    intent.putExtra("Deal",dealName);
                    startActivity(intent);
                }
            });

            pause=(Button) findViewById(R.id.pauseButtonTimer);
            pause.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (stop_start)
                    {
                        stop_start = false;
                        pause.setText( "ПРОДОЛЖИТЬ" );


                    }
                    else
                    {

                        stop_start = true;
                        pause.setText( "ПАУЗА" );

                    }
                }
            });
        }
        else{
            Intent intent = new Intent(TimeRunActivity.this, TimerActivity.class);
            startActivity(intent);
        }
    }
}