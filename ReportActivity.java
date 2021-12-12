package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.firstapp.core.TaskReport;

import java.util.Date;

public class MainActivity3 extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    TaskReport taskReport;
    SeekBar bar;
    TextView grade;
    Button end;
    EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bar = (SeekBar) findViewById(R.id.seekBar);
        bar.setOnSeekBarChangeListener(this);
        grade = (TextView) findViewById(R.id.grade);
        end = (Button) findViewById(R.id.button2);
        description = (EditText) findViewById(R.id.description);
        end.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                taskReport.setComment(description.getText().toString());
                /*
                taskReport.setDateStart();
                taskReport.setDateStop();
                 */
                Date db = new Date();
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        grade.setText(String.valueOf(progress) + "/" + String.valueOf(seekBar.getMax()));
        taskReport.setGrade(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
