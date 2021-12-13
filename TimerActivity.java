package com.example.timetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.timetracker.core.Database;
import com.example.timetracker.core.Deal;
import com.example.timetracker.core.TaskReport;

import android.app.Dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TimerActivity extends AppCompatActivity implements SLDeal{

    //TextView deals;
    Database data;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        List<Deal> dealList = Arrays.asList(
            new Deal("Program job", "I make program"),
            new Deal("Walk", "I walk with my dog"),
            new Deal("Workout", "I train at the gym"));
        List<String> nameDealList = new ArrayList<>();
        data = new Database(dealList);
        //deals = (TextView) findViewById(R.id.deal);
        TextView tim = (TextView) findViewById(R.id.textNameT);


        for(int i = 0; i < dealList.size(); i++) {
            String thing = dealList.get(i).getName();
            nameDealList.add(thing);
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
        tim.setOnClickListener(new View.OnClickListener() {
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
                TaskReport taskReport = new TaskReport();
                Date dateStart = new Date();
                taskReport.setDateStart(dateStart);
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
                long deltaDate = H * 60 * 60 * 1000 + M * 60 * 1000 + S * 1000;
                Date dateEnd = new Date(dateStart.getTime() + deltaDate);
                taskReport.setDateStop(dateEnd);

                /*while(!dateEnd.equals(new Date()))
                {
                   System.out.println("loop");

                }*/

                Intent intent = new Intent(TimerActivity.this, TimeRunActivity.class);
                intent.putExtra("TaskReport",taskReport);
                intent.putExtra("Deal",deals.getText());
                intent.putExtra("timeScip",timeScip);
                startActivity(intent);
                
                //Intent intent = new Intent(TimerActivity.this, ReportActivity.class);
                //intent.putExtra("TaskReport", taskReport);
                //String nameSelectedDeal = spinner.getSelectedItem().toString();
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


                for(int i = 0; i < dealList.size(); i++) {
                    if (dealList.get(i).getName().equals(nameSelectedDeal)) {
                        //saveSharedPreferences(dealList.get(i));

                        FileOutputStream fileOutputStream = null;
                        try {
                            fileOutputStream = openFileOutput(dealList.get(i).getName() + ".bin",MODE_PRIVATE);
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                            objectOutputStream.writeObject(dealList.get(i));
                            objectOutputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                startActivity(intent);
            }
        });
        Button newDealButtonT=(Button) findViewById(R.id.newDealButtonT);
        newDealButtonT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog newDealDialog= new Dialog(TimerActivity.this);
                newDealDialog.setContentView(R.layout.new_deal_layout);
                newDealDialog.show();

                EditText name=(EditText) newDealDialog.findViewById(R.id.editTextName);
                EditText description=(EditText) newDealDialog.findViewById(R.id.editTextDescription);
                Button createNewDeal =(Button) newDealDialog.findViewById(R.id.createDeal);
                createNewDeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Deal thing2=new Deal(name.getText().toString(),description.getText().toString());
                        data.getDeals().add(thing2);
                        newDealDialog.cancel();
                    }
                });
            }
        });


    }



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


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
           for(Deal d: data.getDeals())
            {
                menu.add(0,d.getId(),0,d.getName());
            }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        //deals.setText(item.getTitle().toString() + item.getItemId());
        return super.onContextItemSelected(item);
    }
}
