package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timetracker.core.Note;
import com.example.timetracker.core.TaskReport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CreateNoteActivity extends AppCompatActivity implements SaveLoadToFile{

    EditText noteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        EditText textOfNote = (EditText) findViewById(R.id.noteTextView);
        EditText nameOfNote = (EditText) findViewById(R.id.nameEditText);
        //TextView tv = (TextView) findViewById(R.id.dataNote);
        Bundle arguments = getIntent().getExtras();
        Note note;
        if(arguments == null) {
            note = new Note("", "");
            Toast.makeText(getBaseContext(), "arguments == null", Toast.LENGTH_SHORT).show();
        } else {
            note = (Note) arguments.getSerializable("Note");
            textOfNote.setText(note.getTextNote());
            nameOfNote.setText(note.getName());
        }
        Toast.makeText(getBaseContext(), "count =" + Note.getCount(), Toast.LENGTH_SHORT).show();
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //String text = s.toString();
                if (textOfNote.getText().equals(s)) {
                    note.setTextNote(s.toString());
                } else if (nameOfNote.getText().equals(s)) {
                    note.setName(s.toString());
                }
                String fileName = "no" + note.getId() + ".bin";
                SaveLoadToFile.saveNoteToFile(fileName, note, CreateNoteActivity.this);
                //Note loadNote = loadNoteToFile(fileName);
                //tv.setText(loadNote.toString());
            }
        };
        textOfNote.addTextChangedListener(textWatcher);
        nameOfNote.addTextChangedListener(textWatcher);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateNoteActivity.this, NoteActivity.class);
        String s = "Заметка сохранена";
        intent.putExtra("whatToDo", s);
        startActivity(intent);
    }
}