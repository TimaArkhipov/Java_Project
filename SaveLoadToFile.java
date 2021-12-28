package com.example.timetracker;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetracker.core.Note;
import com.example.timetracker.core.Deal;
import com.example.timetracker.core.TaskReport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface SaveLoadToFile {

    static void saveTaskReportToFile(String fileName, TaskReport taskReport, Context context) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(fileName,Context.MODE_APPEND);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(taskReport);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static TaskReport loadTaskReportToFile(String fileName, Context context) {
        TaskReport taskReport = new TaskReport();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            taskReport = (TaskReport) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return taskReport;
    }

    static List<Deal> loadDealListInFile(Context context){
        FileInputStream fileInputStream = null;
        List<Deal> dealList = new ArrayList<>();
        try {
            fileInputStream = context.openFileInput("Deals.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            //int size = (int) objectInputStream.readObject();
            //for(int i = 0; i < size; i++) {
            dealList = (List<Deal>) objectInputStream.readObject();
            //}
            objectInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return dealList;
    }


    static void saveDealListInFile(List<Deal> dealList, Context context) {
        FileOutputStream fileOutputStream = null;
        if (dealList.size() >= 2) {
            dealList.add(new Deal("Все дела", ""));
        }
        try {
            fileOutputStream = context.openFileOutput("Deals.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //objectOutputStream.writeObject(dealList.size());
            //StringBuilder s = new StringBuilder();
            //for (Deal item : dealList) {
            objectOutputStream.writeObject(dealList);
            //}
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void appendDealInFile(Deal deal, Context context) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput("Deals.bin", Context.MODE_APPEND);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(deal);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveDealListInFileBegin(Context context) {
        FileOutputStream fileOutputStream = null;
        List<Deal> dealList = Arrays.asList(
                new Deal("Программирование", "Делаем проект в команде"),
                new Deal("Прогулка", "Я гуляю со своей собакой"),
                new Deal("Учеба", "Я делаю уроки"));

        if (dealList.size() >= 2) {
            dealList.add(new Deal("Все дела", ""));
        }
        try {
            fileOutputStream = context.openFileOutput("Deals.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(dealList);
            /*
            objectOutputStream.writeObject(dealList.size());
            //int i = 0;
            StringBuilder s = new StringBuilder();
            for (Deal item : dealList) {
                objectOutputStream.writeObject(item);
            }
             */
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveNoteToFile(String fileName, Note note, Context context) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(note);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveCountNoteToFile("CountNote.bin", Note.getCount(), context);
    }

    static Note loadNoteToFile(String fileName, Context context) {
        FileInputStream fileInputStream = null;
        Note note = new Note();
        try {
            fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            note = (Note) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return note;
    }

    static int loadCountNoteToFile(String fileName, Context context) {
        FileInputStream fileInputStream = null;
        int count = 0;
        try {
            fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            count = (int) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    static void saveCountNoteToFile(String fileName, int count, Context context) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(count);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
