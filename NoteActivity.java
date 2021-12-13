package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    private Button buttonPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Bundle arguments = getIntent().getExtras();
        String indication = arguments.getString("whatToDo");
        if (indication.equals("Заметка сохранена")) {
            Toast.makeText(getBaseContext(), indication, Toast.LENGTH_SHORT).show();
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = null;
                i1 = new Intent(NoteActivity.this, CreateNoteActivity.class);
                startActivity(i1);
            }
        };
        buttonPlus.setOnClickListener(onClickListener);
    }
}