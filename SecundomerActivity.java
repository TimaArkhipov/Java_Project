
package com.example.timetracker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


public class SecundomerActivity extends AppCompatActivity {


    TextView deals;
    Database data;
    boolean f = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundomer);
        List<Deal> b=new ArrayList<>();


        for(int j = 0; j < 3 ; j++)
        {
            HashSet<TaskReport> tr = new HashSet<>();
            for(int i = 0 ; i < 4; i++)
            {
                TaskReport e = new TaskReport(new Date(0),new Date(20));
                tr.add(e);
            }
            Deal thing = new Deal("Death",1931707,"Smert-smert-smert", tr);
            b.add(thing);
        }

        data=new Database(b);
        TextView tim=(TextView) findViewById(R.id.textView3);
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecundomerActivity.this, TimerActivity.class);


                startActivity(intent);

            }
        });


        deals =(TextView) findViewById(R.id.deal);
        registerForContextMenu(deals);


        TaskReport taskReport = new TaskReport();


        Button startSec=(Button) findViewById(R.id.start);
        startSec.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (f)
                {
                    startSec.setText( "Stop" );
                    f = false;

                    Date db=new Date();
                    taskReport.setDateStart(db);
                }
                else
                {
                    startSec.setText( "Start" );
                    f = true;
                    Intent intent = new Intent(SecundomerActivity.this, ReportActivity.class);
                    Date de = new Date();
                    taskReport.setDateStop(de);
                    intent.putExtra("TaskReport",taskReport);
                    intent.putExtra("Deal",deals.getText());
                    startActivity(intent);
                }
            }
        });
        Button newDealButtonS=(Button) findViewById(R.id.newDealButtonS);
        newDealButtonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog newDealDialog = new Dialog(SecundomerActivity.this);
                newDealDialog.setContentView(R.layout.new_deal_layout);
                newDealDialog.show();

                EditText name = (EditText) newDealDialog.findViewById(R.id.editTextName);
                EditText description = (EditText) newDealDialog.findViewById(R.id.editTextDescription);
                Button createNewDeal = (Button) newDealDialog.findViewById(R.id.createDeal);
                createNewDeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Deal thing2 = new Deal(name.getText().toString(), description.getText().toString());
                        data.getDeals().add(thing2);
                        newDealDialog.cancel();
                    }
                });
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        for(Deal d: data.getDeals()) {
            menu.add(0,d.getId(),0,d.getName());
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        deals.setText(item.getTitle().toString()+item.getItemId());
        return super.onContextItemSelected(item);
    }
}
