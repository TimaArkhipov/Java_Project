package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.timetracker.core.Deal;
import com.example.timetracker.core.TaskReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class ReportActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    TaskReport taskReport;
    SeekBar bar;
    TextView grade;
    Button end;
    EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        bar = (SeekBar) findViewById(R.id.seekBar);
        bar.setOnSeekBarChangeListener(this);
        grade = (TextView) findViewById(R.id.grade);
        end = (Button) findViewById(R.id.button2);
        description = (EditText) findViewById(R.id.description);
        Bundle arguments = getIntent().getExtras();
        String dealName = arguments.getString("nameDeal");
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
                //saveSharedPreferences(taskReport);
                /* Ещё должна быть подгрузка времени
                taskReport.setDateStart();
                taskReport.setDateStop();
                 */
                //TextView tvData = (TextView) findViewById(R.id.textViewData);
                //tvData.setText("Saved data: \n" + "Name of deal: " + dealName + "\n" + taskReport.toString());
                TaskReport tr1 = new TaskReport(new Date(10,10,10),new Date(10,10,11));
                tr1.setComment("На улице снег");
                tr1.setGrade(3);

                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = openFileOutput(dealName + "1" + ".bin",MODE_APPEND);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(taskReport);
                    objectOutputStream.writeObject(tr1);
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileInputStream fis = null;
                TaskReport newTR1 = null;
                TaskReport newTR2 = null;
                try {
                    fis = openFileInput(dealName + "1" + ".bin");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fis);
                    newTR1 = (TaskReport) objectInputStream.readObject();
                    newTR2 = (TaskReport) objectInputStream.readObject();
                    TextView tvData = (TextView) findViewById(R.id.textViewData);
                    String str1 = "Это первый загрузившийся: \n" + newTR1.toString();
                    String str2 = "Это второй загрузившийся: \n" + newTR2.toString();
                    tvData.setText(str1 + "\n\n" + str2);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                //Intent intent = new Intent(ReportActivity.this, TimerActivity.class);
                //startActivity(intent);


            }
        });
    }

    public void saveToFile(String fileName, TaskReport taskReport) {
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
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        grade.setText(String.valueOf(progress) + "/" + String.valueOf(seekBar.getMax()));
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}