package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timetracker.core.Note;
import com.example.timetracker.core.TaskReport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CreateNoteActivity extends AppCompatActivity {

    EditText noteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        EditText textOfNote = (EditText) findViewById(R.id.noteTextView);
        EditText nameOfNote = (EditText) findViewById(R.id.nameEditText);
        TextView tv = (TextView) findViewById(R.id.dataNote);
        Note note = new Note("","");
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
                saveNoteToFile(fileName, note);
                Note loadNote = loadNoteToFile(fileName);
                tv.setText(loadNote.toString());
            }
        };
        textOfNote.addTextChangedListener(textWatcher);
        nameOfNote.addTextChangedListener(textWatcher);
    }

    public void saveNoteToFile(String fileName, Note note) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(fileName,MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(note);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Note loadNoteToFile(String fileName) {
        FileInputStream fileInputStream = null;
        Note note = new Note();
        try {
            fileInputStream = openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            note = (Note) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateNoteActivity.this, NoteActivity.class);
        String s = "Заметка сохранена";
        intent.putExtra("whatToDo", s);
        startActivity(intent);
    }
}