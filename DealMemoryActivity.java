package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.timetracker.core.Deal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DealMemoryActivity extends AppCompatActivity {
    Spinner spinner;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_memory);
        List<Deal> dealList = Arrays.asList(
                new Deal("Program job", "I make program"),
                new Deal("Walk", "I walk with my dog"),
                new Deal("Workout", "I train at the gym"));
        List<String> nameDealList = new ArrayList<>();

        for(int i = 0; i < dealList.size(); i++) {
            String thing = dealList.get(i).getName();
            nameDealList.add(thing);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameDealList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.dealSpinner);
        spinner.setAdapter(adapter);

        button=(Button) findViewById(R.id.trvButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealMemoryActivity.this, ListTaskReportActicity.class);
                intent.putExtra("Deal",dealList.get(spinner.getSelectedItemPosition()));
                startActivity(intent);
            }
        });
    }
}