
package com.example.timetracker;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;


public class TaskReport implements Externalizable {
	private static final long serialVersionUID = 1L;
	private int grade;
	private String comment;
	private Date dateStart;
	private Date dateStop;

	public TaskReport(){ }

	public TaskReport(int grade, String comment, Date dateStart, Date dateStop) {
		this.dateStart = dateStart;
		this.dateStop = dateStop;
		this.grade = grade;
		this.comment = comment;
	}

	public TaskReport(Date dateStart, Date dateStop) {
		this.dateStart = dateStart;
		this.dateStop = dateStop;
	}
	/*
	public void saveToFile(String fileName) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(taskReport);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	public int getGrade() {
		return grade;
	}
	
	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateStop() {
		return dateStop;
	}

	public String getComment () {
		return comment;
	}

	public void setGrade(int new_grade) {
		grade = new_grade;
	}
	
	public void setDateStart(Date dateStart) {
		this.dateStart=dateStart;
	}
	
	public void setDateStop(Date dateStop) {
		this.dateStop=dateStop;
	}
	
	public void setComment(String new_comment) {
		comment = new_comment;
	}

	@Override	
	public String toString() {
        return "Grade: " + grade + '\n'
				+ "Comment: "+ comment+ '\n'
				+ "Time start: " + dateStart + '\n'
				+ "Time stop: "+ dateStop+ '\n';
	}

	@Override
	public void writeExternal(ObjectOutput output) throws IOException {
		output.writeObject(this.getGrade());
		output.writeObject(this.getDateStart());
		output.writeObject(this.getDateStop());
		output.writeObject(this.getComment());
	}

	@Override
	public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
		grade = (int) input.readObject();
		dateStart = (Date) input.readObject();
		dateStop = (Date) input.readObject();
		comment = (String) input.readObject();
	}

	public void saveTaskReport(String fileName) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(this);
		objectOutputStream.close();
	}

	public TaskReport loadTaskReport(String fileName) throws Exception
	{
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		TaskReport tr = null;
		try {
			fileInputStream = new FileInputStream(fileName);
			objectInputStream = new ObjectInputStream(fileInputStream);
			tr = (TaskReport) objectInputStream.readObject();
		}
		catch(Exception e)
		{
			throw new Exception(" Can't read data");
		}
		finally
		{
			objectInputStream.close();
		}
		return tr;
	}
}
