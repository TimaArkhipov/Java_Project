package com.example.timetracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetracker.core.TaskReport;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
//import com.jjoe64.graphview.GraphView.GraphViewData;
//import com.jjoe64.graphview.GraphViewSeries;
//import com.jjoe64.graphview.LineGraphView;
//import com.jjoe64.graphview.GraphView.

public class StatActivityGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(7, 10)
        });
        graph.addSeries(series);
        /*
        // Линейный график
        GraphViewSeries exampleSeries = new GraphViewSeries(
                new GraphViewData[] { new GraphViewData(1, 3.0d),
                        new GraphViewData(2, 1.5d), new GraphViewData(3, 2.5d),
                        new GraphViewData(4, 1.0d), new GraphViewData(5, 1.3d) });

        GraphView graphView = new LineGraphView(this, "График каких-то данных");
        graphView.addSeries(exampleSeries);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.addView(graphView);
         */

        //load Task reports
        /*
        FileInputStream fis = null;
        try {
            fis = openFileInput(dealName + "_tr.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
            newTR1 = (TaskReport) objectInputStream.readObject();
            //newTR2 = (TaskReport) objectInputStream.readObject();
            TextView tvData = (TextView) findViewById(R.id.textViewData);
            //String str1 = "Это первый загрузившийся: \n" + newTR1.toString();
            //String str2 = "Это второй загрузившийся: \n" + newTR2.toString();
            //tvData.setText(str1 + "\n\n" + str2);
            tvData.setText("Название дела:" + dealName + '\n' + newTR1.toString());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        */

    }
    /*
    private TaskReport saveSharedPreferences(TaskReport taskReport)
    {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        taskReport.setGrade(prefs.getInt("Grade",0));
        taskReport.setComment(prefs.getString("Comment", ""));
        taskReport.setDateStart(new Date(prefs.getLong("DateStart", 0)));
        taskReport.setDateStart(new Date(prefs.getLong("DateStop", 0)));

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
        Intent intent = new Intent(StatActivityGraph.this, StatActivity.class);
        startActivity(intent);
    }
}