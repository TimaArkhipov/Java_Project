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


public class ReportActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, 
    SaveLoadToFile {

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
        Deal deal = (Deal)arguments.getSerializable("Deal");
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
                //List<Deal> dealList = SaveLoadToFile.loadDealListInFile(ReportActivity.this);

                saveTaskReportToFile(filePath, taskReport, ReportActivity.this);
                
                Intent intent = new Intent(ReportActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
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
