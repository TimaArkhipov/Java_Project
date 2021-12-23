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
        saveDealListInFile();
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

    public void saveDealListInFile() {
        FileOutputStream fileOutputStream = null;
        List<Deal> dealList = new ArrayList<>(
                Arrays.asList(
                        new Deal("Программирование", "Я делаю программу"),
                        new Deal("Прогулка", "Я гуляю со своей собакой"),
                        new Deal("Учеба", "Я делаю уроки")));

        if (dealList.size() >= 2) {
            dealList.add(new Deal("Все дела", "бла"));
        }

        //TextView textView = (EditText) findViewById(R.id.textView4);

        try {
            fileOutputStream = openFileOutput("Deals.bin", MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(dealList.size());
            //int i = 0;
            StringBuilder s = new StringBuilder();
            for (Deal item : dealList) {
                //i++;
                //String toS = "Это " + i + "-й id(" + item.getName() + "): " + item.getId() + '\n';
                //s.append(toS);
                objectOutputStream.writeObject(item);
            }
            //textView.setText(s.toString());
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
