package com.example.timetracker.core;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Note implements Externalizable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String textNote;
    private int id;
    private static int count;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
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
