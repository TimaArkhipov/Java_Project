package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatActivity.this, StatActivityGraph.class);
                startActivity(intent);
            }
        };

        //Временное решение
        List<String> nameDealList = Arrays.asList("Программирование", "Все дела", "Прогулка", "Тренировка");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameDealList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setAdapter(adapter);
        //spinner.setPrompt("Выберите вариант"); // Не работает почему-то
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (nameDealList.get(position).equals("Все дела")) {
                    FragmentStat f1 = new FragmentStat();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayout, f1);
                    ft.commit();
                }
                else {
                    FragmentStatOneDeal f2 = new FragmentStatOneDeal();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayout, f2);
                    ft.commit();
                    //Другой фрагмент, в котором просто кнопка "Вывести статистику"
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}