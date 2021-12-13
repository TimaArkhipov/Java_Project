package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonTime = (Button) findViewById(R.id.button1);
        Button buttonStat = (Button) findViewById(R.id.button2);
        Button buttonNote = (Button) findViewById(R.id.button3);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                switch (view.getId()) {
                    case R.id.button1:
                        intent = new Intent(MenuActivity.this, TimerActivity.class);
                        break;
                    case R.id.button2:
                        intent = new Intent(MenuActivity.this,StatActivity.class);
                        break;
                    case R.id.button3:
                        intent = new Intent(MenuActivity.this,NoteActivity.class);
                        intent.putExtra("whatToDo","Nothing");
                        break;
                }
                startActivity(intent);
            }
        };
        buttonTime.setOnClickListener(onClickListener);
        buttonStat.setOnClickListener(onClickListener);
        buttonNote.setOnClickListener(onClickListener);
    }
}