package com.example.timetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class SecundomerActivity extends AppCompatActivity {

    Spinner spinner;
    private Button newDealButtonS;
    boolean f = true;
    int sec=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundomer);

        List<Deal> dealList = Arrays.asList(
                new Deal("Program job", "I make program"),
                new Deal("Walk", "I walk with my dog"),
                new Deal("Workout", "I train at the gym"));
        List<String> nameDealList = new ArrayList<>();
        //deals = (TextView) findViewById(R.id.deal);



        for(int i = 0; i < dealList.size(); i++) {
            String thing = dealList.get(i).getName();
            nameDealList.add(thing);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameDealList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinnerS);
        spinner.setAdapter(adapter);


        TextView tim=(TextView) findViewById(R.id.textNameSecundomer);
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecundomerActivity.this, TimerActivity.class);
                startActivity(intent);

            }
        });
        TaskReport taskReport = new TaskReport();
        Button startSec=(Button) findViewById(R.id.start);
        startSec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (f)
                {
                    f = false;
                    startSec.setText( "Стоп" );
                    Date db=new Date();
                    taskReport.setDateStart(db);
                    Handler handler=new Handler();
                    spinner.setClickable(f);
                    newDealButtonS.setClickable(f);

                    TextView timeText = (TextView) findViewById(R.id.textTextS);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int h=sec/3600;
                            int m=sec%3600/60;
                            int s=sec%60;
                            String time= String.format("%02d:%02d:%02d",h,m,s);
                            timeText.setText(time);
                            sec=sec+1;
                            handler.postDelayed(this,1000);
                        }
                    });

                }
                else
                {

                    f = true;
                    spinner.setClickable(f);
                    newDealButtonS.setClickable(f);
                    startSec.setText( "Старт" );
                    Intent intent = new Intent(SecundomerActivity.this, ReportActivity.class);
                    Date de=new Date();
                    String nameSelectedDeal = spinner.getSelectedItem().toString();
                    taskReport.setDateStop(de);
                    intent.putExtra("TaskReport",taskReport);
                    intent.putExtra("Deal",nameSelectedDeal);

                    startActivity(intent);

                }



            }
        });

        newDealButtonS=(Button) findViewById(R.id.newDealButtonS);
        newDealButtonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog newDealDialog= new Dialog(SecundomerActivity.this);
                newDealDialog.setContentView(R.layout.new_deal_layout);
                newDealDialog.show();

                EditText name=(EditText) newDealDialog.findViewById(R.id.editTextName);
                EditText description=(EditText) newDealDialog.findViewById(R.id.editTextDescription);
                Button createNewDeal =(Button) newDealDialog.findViewById(R.id.createDeal);
                createNewDeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Deal thing2=new Deal(name.getText().toString(),description.getText().toString());
                        nameDealList.add(thing2.getName());
                        newDealDialog.cancel();
                    }
                });



            }
        });

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        //deals.setText(item.getTitle().toString() + item.getItemId());
        return super.onContextItemSelected(item);
    }
}