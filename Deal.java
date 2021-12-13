package com.example.timetracker.core;

import android.content.Context;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class Deal implements Externalizable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int pid;
	private static int count;
	private String description;
	
	private HashSet<TaskReport> taskReportSet = new HashSet<TaskReport>();
	
	public Deal(){ }

	public Deal(String name, String description) {
		super();
		count++;
		pid = count;
		this.name = name;
		this.description = description;
	}

	public Deal(String name, String description, HashSet<TaskReport> tr) {
		super();
		count++;
		pid = count;
		this.name = name;
		this.description = description;
		this.taskReportSet = tr;
	}

	public void setName(String new_name) {
		name = new_name;
	}
	
	public void setDescription(String new_desc) {
		description = new_desc;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public int getId() { return pid; }

	public HashSet<TaskReport> getTr() {
		return taskReportSet;
	}

	public void setTr(HashSet<TaskReport> tr) {
		this.taskReportSet = tr;
	}
	
	@Override
	public String toString() {
		String s = new String();
		for(TaskReport i : taskReportSet)
			s += i.toString();
		return "Name of deal: " + name + "\n" +
				"Description of deal: " + description + "\n" + s;
				//"ID of deal: " + Integer.toString(id) + "\n";
	}

	public void saveTaskReport(String fileName) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(taskReportSet);
		objectOutputStream.close();
	}

	public void loadTaskReport(String fileName) throws Exception
	{
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(fileName);
			objectInputStream = new ObjectInputStream(fileInputStream);
			taskReportSet = (HashSet<TaskReport>) objectInputStream.readObject();
		}
		catch(Exception e)
		{
			throw new Exception(" Can't read data");
		}
		finally
		{
			objectInputStream.close();
		}
	}

	public void saveDeal(String filename) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(this);
		objectOutputStream.close();
	}

	public Deal loadDeal(String filename) throws Exception
	{
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		Deal deal = new Deal();
		try {
			fileInputStream = new FileInputStream(filename);
			objectInputStream = new ObjectInputStream(fileInputStream);
			deal = (Deal) objectInputStream.readObject();
		}
		catch(Exception e)
		{
			throw new Exception(" Can't read data");
		}
		finally
		{
			objectInputStream.close();
		}
		return deal;
	}

	//serialization
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this.name);
		out.writeObject(this.getDescription());
		out.writeObject(this.pid);
	}

	//deserialization
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = (String) in.readObject();
		description = (String) in.readObject();
		pid = (int) in.readObject();
	}
}
