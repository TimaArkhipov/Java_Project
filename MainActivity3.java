package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Date;

public class MainActivity3 extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar bar;
    TextView grad;
    Button end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bar=(SeekBar) findViewById(R.id.seekBar);
        bar.setOnSeekBarChangeListener(this);
        grad=(TextView) findViewById(R.id.grade);
        end=(Button)findViewById(R.id.button2);
        end.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {

                Date db=new Date();

                Intent intent = new Intent(MainActivity3.this, MainActivity.class);

                startActivity(intent);

            }

        });
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        grad.setText(String.valueOf(progress)+"/"+String.valueOf(seekBar.getMax()));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}