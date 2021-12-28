package com.example.timetracker.core;

import java.io.FileInputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;


public class Note implements Externalizable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String textNote;
    private int id;
    private static int count; //comm fo gitS

    public Note(){}

    public Note(String name, String textNote){
        super();
        count++;
        id = count;
        this.name = name;
        this.textNote = textNote;
    }

    public Note(String textNote){
        super();
        count++;
        id = count;
        this.name = name;
        this.textNote = textNote;
    }

    public String getName() {
        return name;
    }

    public String getTextNote() {
        return textNote;
    }

    public int getId() {
        return id;
    }

    public static int getCount() { return count; }

    public void setName(String name) {
        this.name = name;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public static void setCount(int new_count) {
            count = new_count;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Text of note: " + textNote + "\n" +
                "Id of note: " + id + "\n";
    }

    @Override
    public void writeExternal(ObjectOutput output) throws IOException {
        output.writeObject(this.name);
        output.writeObject(this.textNote);
        output.writeObject(this.id);
    }

    @Override
    public void readExternal(ObjectInput input) throws ClassNotFoundException, IOException {
        name = (String) input.readObject();
        textNote = (String) input.readObject();
        id = (int) input.readObject();
    }

}
