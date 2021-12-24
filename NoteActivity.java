package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timetracker.core.Deal;
import com.example.timetracker.core.Note;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteActivity extends ListActivity implements SaveLoadToFile{

    private Button buttonPlus;
    private ArrayAdapter<String> mAdapter;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //saveNoteToFileBegin();
        noteList = new ArrayList<>();
        List<String> noteNameList = new ArrayList<>();
        //saveNoteToFileBegin(); Написать условие - если не существует
        int n = SaveLoadToFile.loadCountNoteToFile("CountNote.bin",NoteActivity.this);
        Note.setCount(n);
        for(int i = 1; i <= n; i++) {
            noteList.add(SaveLoadToFile.loadNoteToFile("no" + i + ".bin",NoteActivity.this));
            //noteList.get(i - 1).setCount(n);
            noteNameList.add(noteList.get(i - 1).getName() + '\n' + noteList.get(i - 1).getId());
        }
        /*
        List<String> catNamesArray = Arrays.asList("Рыжик", "Барсик", "Мурзик",
                "Мурка", "Васька", "Томасина", "Бобик", "Кристина", "Пушок",
                "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба");
        */
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, noteNameList);
        setListAdapter(mAdapter);

        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Bundle arguments = getIntent().getExtras();
        String indication = arguments.getString("whatToDo");
        /*
        if (indication.equals("Заметка сохранена")) {
            Toast.makeText(getBaseContext(), indication, Toast.LENGTH_SHORT).show();
        }
        */

        Toast.makeText(getBaseContext(), "count =" + Note.getCount(), Toast.LENGTH_SHORT).show();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = null;
                i1 = new Intent(NoteActivity.this, CreateNoteActivity.class);
                startActivity(i1);
            }
        };
        buttonPlus.setOnClickListener(onClickListener);
        //
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(NoteActivity.this, CreateNoteActivity.class);
        intent.putExtra("Note", noteList.get(position));
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "Notename - " + noteList.get(position).toString(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getBaseContext(), "Item = " + l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "position = " + position, Toast.LENGTH_SHORT).show();
    }


    public void saveNoteToFileBegin() {
        FileOutputStream fileOutputStream = null;
        List<Note> noteList = Arrays.asList(
                        new Note("Сон - это важно", "А в сессию очень важно"),
                        new Note("Стих", "Сел и стих"),
                        new Note("Список продуктов", "Кофе, молоко, блендер"));
        /*
        if (dealList.size() >= 2) {
            dealList.add(new Deal("Все дела", "бла"));
        }
         */
        for(int i = 1; i <= noteList.size(); i++) {
            try {
                fileOutputStream = openFileOutput("no" + i + ".bin", MODE_PRIVATE);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(noteList.get(i - 1));
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SaveLoadToFile.saveCountNoteToFile("CountNote.bin", noteList.get(i - 1).getId(), NoteActivity.this);
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NoteActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}