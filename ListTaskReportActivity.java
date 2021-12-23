package com.example.timetracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.timetracker.core.Deal;
import com.example.timetracker.core.TaskReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ListTaskReportActivity extends ListActivity  {

    private ArrayAdapter<String> mAdapter;
    List<TaskReport> taskReportList;
    List<String> commentTaskReportList;
    Deal deal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        deal = (Deal)arguments.get("Deal");
        taskReportList = Arrays.asList(
                new TaskReport(5,"Я бы умер с тайной радостью",new Date(), new Date(),500),
                new TaskReport(5,"В час когда взойдет луна",new Date(), new Date(),500),
                new TaskReport(5,"Овевает странной сладостью",new Date(), new Date(),500),
                new TaskReport(5,"Тень таинственного сна",new Date(), new Date(),500));
        commentTaskReportList = new ArrayList<>();




        for(int i = 0; i < taskReportList.size(); i++) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat d1 = new SimpleDateFormat("dd-MM-yyyy");
            String thing = deal.getName()+"\n"+"Дата начала: "+d1.format(taskReportList.get(i).getDateStart())
                    +"\n"+"Дата конца: "+d1.format(taskReportList.get(i).getDateStop());
            commentTaskReportList.add(thing);
        }

        mAdapter = new ArrayAdapter<>(ListTaskReportActivity.this,
                android.R.layout.simple_list_item_1, commentTaskReportList);
        setListAdapter(mAdapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Dialog taskDialog = new Dialog(ListTaskReportActivity.this);
        taskDialog.setContentView(R.layout.task_report_memory_layout);
        taskDialog.show();
        TextView comment;
        comment=(TextView) taskDialog.findViewById(R.id.commentText);
        comment.setText(taskReportList.get(position).getComment().toString());
        Button saveButton =(Button) taskDialog.findViewById(R.id.saveButton);

        RatingBar bar = (RatingBar) taskDialog.findViewById(R.id.ratingBar2);
        bar.setRating(taskReportList.get(position).getGrade());
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskReportList.get(position).setComment(comment.getText().toString());
                taskReportList.get(position).setGrade(bar.getProgress());
                mAdapter.notifyDataSetChanged();
                taskDialog.cancel();
            }
        });
    }
}