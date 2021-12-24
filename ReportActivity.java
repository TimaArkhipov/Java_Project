package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetracker.core.Deal;
import com.example.timetracker.core.TaskReport;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {

    private static final int FIRST = 1;
    TaskReport taskReport;
    RatingBar bar;
    Button end;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        bar = (RatingBar) findViewById(R.id.ratingBar);
        bar.setOnRatingBarChangeListener(this);
        end = (Button) findViewById(R.id.button2);
        description = (EditText) findViewById(R.id.description);
        Bundle arguments = getIntent().getExtras();
        String dealName = arguments.getString("Deal");
        taskReport = (TaskReport) arguments.getSerializable("TaskReport");

        /*
        Deal deal = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = openFileInput(dealName + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            deal = (Deal) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally
        {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TextView tvData = (TextView) findViewById(R.id.textViewData);
        tvData.setText("Saved data:" + deal.toString());
        */
        end.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                taskReport.setGrade(bar.getProgress());
                taskReport.setComment(description.getText().toString());

                String filePath = dealName + "_tr.bin";
                List<Deal> dealList = SaveLoadToFile.loadDealListInFile(ReportActivity.this);

                saveTaskReportToFile(filePath,taskReport);
                for(Deal item : dealList){
                    if (dealName.equals(item.getName())) {
                        item.incrementTaskReportCount();
                    }
                }
            }
        });
    }

    public void saveTaskReportToFile(String fileName, TaskReport taskReport) {
        FileOutputStream fileOutputStream = null;
        try {
        fileOutputStream = openFileOutput(fileName,MODE_APPEND);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(taskReport);
        objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskReport loadToFile(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            TaskReport taskReport = (TaskReport) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return taskReport;

    }



    /*
    private void saveSharedPreferences(TaskReport taskReport)
    {
        @SuppressLint("WrongConstant")
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_APPEND);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("Grade",taskReport.getGrade());
        prefEdit.putString("Comment", taskReport.getComment());
        prefEdit.putLong("DateStart", taskReport.getDateStart().getTime());
        prefEdit.putLong("DateStop", taskReport.getDateStop().getTime());
        prefEdit.apply();


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
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReportActivity.this, TimerActivity.class);
        startActivity(intent);
    }
    /*
    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                ReportActivity.this);
        quitDialog.setTitle("Если нажмёте ещё раз, то оценка и комментарий не сохранятся?");

        quitDialog.setPositiveButton("Таки да!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        quitDialog.setPositiveButton()

        quitDialog.setNegativeButton("Нет", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }
    */
}