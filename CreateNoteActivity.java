package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class CreateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateNoteActivity.this, NoteActivity.class);
        String s = "Заметка сохранена";
        intent.putExtra("whatToDo", s);
        startActivity(intent);
    }
}