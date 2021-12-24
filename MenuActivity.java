package com.example.timetracker;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timetracker.core.Deal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SaveLoadToFile.saveDealListInFileBegin(MenuActivity.this);
        Button buttonTime = (Button) findViewById(R.id.button1);
        Button buttonStat = (Button) findViewById(R.id.button2);
        Button buttonNote = (Button) findViewById(R.id.button3);
        Button buttonMemory = (Button) findViewById(R.id.button4);

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
                    case R.id.button4:
                        intent = new Intent(MenuActivity.this,DealMemoryActivity.class);
                        break;
                }
                startActivity(intent);
            }
        };
        buttonTime.setOnClickListener(onClickListener);
        buttonStat.setOnClickListener(onClickListener);
        buttonNote.setOnClickListener(onClickListener);
        buttonMemory.setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
