package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetracker.core.Deal;
import com.example.timetracker.core.TaskReport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ReportActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {

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
        Deal dealName = (Deal)arguments.get("Deal");
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
                //TaskReport tr1 = new TaskReport(new Date(10,10,10),new Date(10,10,11));
                //tr1.setComment("На улице снег");
                //tr1.setGrade(3);

                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = openFileOutput(dealName + "_tr.bin",MODE_APPEND);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(taskReport);
                    //objectOutputStream.writeObject(tr1);
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileInputStream fis = null;
                TaskReport newTR1 = null;
                TaskReport newTR2 = null;
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
                /*
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(ReportActivity.this, MenuActivity.class);
                startActivity(intent);
                */


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