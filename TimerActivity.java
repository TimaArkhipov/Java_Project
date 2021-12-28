package com.example.timetracker;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetracker.core.Deal;
import com.example.timetracker.core.TaskReport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TimerActivity extends AppCompatActivity implements SaveLoadToFile{

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        List<Deal> dealList = SaveLoadToFile.loadDealListInFile(TimerActivity.this);
        List<String> nameDealList = new ArrayList<>();
        TextView buttonTimerSec = (TextView) findViewById(R.id.textNameT);

        for(int i = 0; i < dealList.size(); i++) {
            nameDealList.add(dealList.get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameDealList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        //spinner.setPrompt("title");
        //spinner.setSelection(2);

        /*
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
        buttonTimerSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimerActivity.this, SecundomerActivity.class);
                startActivity(intent);
            }
        });

        Button startTimer = (Button) findViewById(R.id.button);
        startTimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Date dateStart = new Date();
                EditText hour = (EditText) findViewById(R.id.editTextHours);
                EditText min = (EditText) findViewById(R.id.editTextMinuts);
                EditText sec = (EditText) findViewById(R.id.editTextSeconds);
                long H, M, S;
                try {
                    H = Integer.parseInt(hour.getText().toString());
                } catch (Exception e) {
                    H = 0;
                }
                try {
                    M = Integer.parseInt(min.getText().toString());
                } catch (Exception e) {
                    M = 0;
                }
                try {
                    S = Integer.parseInt(sec.getText().toString());
                } catch (Exception e) {
                    S = 0;
                }


                if (M <= 60 && S <= 60) {
                    long deltaDate = H * 60 * 60 * 1000 + M * 60 * 1000 + S * 1000;
                    Date dateEnd = new Date(dateStart.getTime() + deltaDate);

                    String nameSelectedDeal = spinner.getSelectedItem().toString();
                    Intent intentTimeRun = new Intent(TimerActivity.this, TimeRunActivity.class);
                    TaskReport taskReport = new TaskReport(dateStart,dateEnd,deltaDate);
                    intentTimeRun.putExtra("TaskReport",taskReport);
                    intentTimeRun.putExtra("Deal",dealList.get(spinner.getSelectedItemPosition()));
                    //intent.putExtra("timeScip",deltaDate);
                    for(int i = 0; i < dealList.size(); i++) {
                        if (dealList.get(i).getName().equals(nameSelectedDeal)) {
                            //saveSharedPreferences(dealList.get(i));

                            FileOutputStream fileOutputStream = null;
                            try {
                                fileOutputStream = openFileOutput(dealList.get(i).getName() + ".bin",MODE_PRIVATE);
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                                objectOutputStream.writeObject(dealList.get(i));
                                objectOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    startActivity(intentTimeRun);
                }
                else
                {
                    if (S > 60)
                    {
                        sec.setText(new Integer((int)S%60).toString());
                        min.setText(new Integer((int)S/60).toString());
                    }
                    if (M > 60)
                    {
                        min.setText(new Integer((int)M%60).toString());
                        hour.setText(new Integer((int)M/60).toString());
                    }


                    Toast.makeText(getBaseContext(), "Введите корректное время", Toast.LENGTH_SHORT).show();
                }
                //Intent intent = new Intent(TimerActivity.this, ReportActivity.class);
                //intent.putExtra("TaskReport", taskReport);


                //intent.putExtra("nameDeal", nameSelectedDeal);

                /*
                try {
                    FileOutputStream fos = openFileOutput(nameSelectedDeal + ".bin",MODE_PRIVATE);
                    fos.write(().getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */


            }
        });
        // letter "T" at the end of the variable name means "Timer"
        Button newDealButtonT = (Button) findViewById(R.id.newDealButtonT);
        newDealButtonT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog newDealDialog = new Dialog(TimerActivity.this);
                newDealDialog.setContentView(R.layout.new_deal_layout);
                newDealDialog.show();

                EditText name = (EditText) newDealDialog.findViewById(R.id.editTextName);
                EditText description = (EditText) newDealDialog.findViewById(R.id.editTextDescription);
                Button createNewDeal = (Button) newDealDialog.findViewById(R.id.createDeal);
                createNewDeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Deal thing2 = new Deal(name.getText().toString(),description.getText().toString());
                        nameDealList.add(thing2.getName());
                        dealList.add(thing2);
                        SaveLoadToFile.saveDealListInFile(dealList,TimerActivity.this);
                        newDealDialog.cancel();
                    }
                });
            }
        });


    }

    /*
    private void saveSharedPreferences(Deal deal)
    {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("ID",deal.getId());
        prefEdit.putString("Name", deal.getName());
        prefEdit.putString("Description",deal.getDescription());
        prefEdit.apply();

        // Теперь сам пример
        File myPath = new File(Environment.getExternalStorageDirectory().toString());
        File myFile = new File(myPath, "MySharedPreferences");

        try
        {
            FileWriter fw = new FileWriter(myFile);
            PrintWriter pw = new PrintWriter(fw);

            Map<String,?> prefsMap = prefs.getAll();

            for(Map.Entry<String,?> entry : prefsMap.entrySet())
            {
                pw.println(entry.getKey() + ": " + entry.getValue().toString());
            }

            pw.close();
            fw.close();
        }
        catch (Exception e)
        {
            Log.wtf(getClass().getName(), e.toString());
        }
    }
    */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TimerActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
