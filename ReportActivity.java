
package com.example.timetracker;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Date;

public class ReportActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {


    RatingBar bar;
    TextView grad;
    EditText description;
    Button end;
    TaskReport taskReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        bar = (RatingBar) findViewById(R.id.ratingBar);
        bar.setOnRatingBarChangeListener( this);
        grad = (TextView) findViewById(R.id.grade);
        end = (Button)findViewById(R.id.button2);
        description =(EditText) findViewById(R.id.description);
        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            String dealName = arguments.getString("Deal");
            taskReport=(TaskReport) arguments.getSerializable("TaskReport");

        }
        end.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {

                taskReport.setGrade((int)bar.getRating());
                taskReport.setComment(description.getText().toString());

                Intent intent = new Intent(ReportActivity.this, TimerActivity.class);

                startActivity(intent);

            }


        });
    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        grad.setText(String.valueOf(rating)+"/"+String.valueOf(ratingBar.getMax()));
    }
}

