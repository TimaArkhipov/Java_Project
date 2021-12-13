package com.example.timetracker;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

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
}