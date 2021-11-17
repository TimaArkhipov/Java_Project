package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView deals;
    Database data;
    int fla=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Deal> b=new ArrayList<>();


        for(int j=0;j<3;j++)
        {
            HashSet<TaskReport> tr = new HashSet<>();
            for(int i=0; i<4;i++)
            {
                TaskReport e = new TaskReport(new Date(0),new Date(20));
                tr.add(e);
            }
            Deal thing=new Deal("Death",1931707,"Smert-smert-smert", tr);
            b.add(thing);
        }

        data=new Database(b);


        setContentView(R.layout.activity_main);

        deals =(TextView) findViewById(R.id.deal);
        registerForContextMenu(deals);

        TextView tim=(TextView) findViewById(R.id.textView3);
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Secundomer.class);
                startActivity(intent);

            }
        });

        Button startTimer=(Button) findViewById(R.id.button);
        startTimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Date db=new Date();
                EditText hour=(EditText) findViewById(R.id.editTextHours);
                EditText min=(EditText) findViewById(R.id.editTextMinuts);
                EditText sec=(EditText) findViewById(R.id.editTextSeconds);
                int H,M,S;
                try{
                    H = Integer.parseInt( hour.getText().toString());
                }
                catch(Exception e) {
                    H=0;
                }
                try {
                    M = Integer.parseInt(min.getText().toString());
                }
                catch(Exception e) {
                    M=0;
                }
                try{
                    S= Integer.parseInt( sec.getText().toString() );
                }
                catch(Exception e)
                {
                    S=0;
                }
                Date dateEnd=new Date(db.getTime()+H*60*60*1000+M*60*1000+S*1000);

                TextView v1=(TextView) findViewById(R.id.textView3);
                v1.setText("Time end: \n"+dateEnd.toString());









                /*while(!dateEnd.equals(new Date()))
                {
                   System.out.println("loop");

                }*/
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                try {
                    Thread.sleep(H*60*60*1000+M*60*1000+S*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                startActivity(intent);


            }
        });



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

        deals.setText(item.getTitle().toString()+item.getItemId());

        return super.onContextItemSelected(item);
    }
}