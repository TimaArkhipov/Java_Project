package com.example.timetracker;

import java.util.Date;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public class TaskReport implements Externalizable{//TaskReport
	private static final long serialVersionUID = 1L;
	private int grade;
	private String comment;
	private Date dateStart;
	private Date dateStop;




	public TaskReport(){



	}

	TaskReport( Date dateStart,Date dateStop){
		this.dateStart=dateStart;
		this.dateStop=dateStop;

	}


	public void setGrade(int new_grade){
		grade = new_grade;
	}

	public int getGrade() {
		return grade;
	}

	public Date getDateStart()
	{
		return dateStart;
	}

	public void setDateStart(Date dateStart)
	{
		this.dateStart=dateStart;
	}

	public Date getDateStop()
	{
		return dateStop;
	}

	public void setDateStop(Date dateStop)
	{
		this.dateStop=dateStop;
	}

	public void setComment(String new_comment) {
		comment = new_comment;
	}

	public String getComment () {
		return comment;
	}


	public void setMode(boolean flag)
	{
		//false - TimerActivity, true - secundomer
		if (flag)
		{


		}
		else
		{
			System.out.println("Enter time to stop");
			this.dateStart = new Date();


		}
	}

	@Override
	public String toString() {
		return
				"ReportActivity: " + grade + '\n'
						+ "Time start: " + dateStart + '\n'
						+ "Time stop: "+ dateStop+ '\n'
						+ "Comment: "+ comment+ '\n';
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {

		out.writeObject(this.getGrade());
		out.writeObject(this.getDateStart());
		out.writeObject(this.getDateStop());
		out.writeObject(this.getComment());
		// TODO Auto-generated method stub

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		grade=(int)in.readObject();
		dateStart=(Date)in.readObject();
		dateStop=(Date)in.readObject();
		comment=(String)in.readObject();

	}
}
