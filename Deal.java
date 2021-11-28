package com.example.timetracker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;

import java.io.Externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;

public class Deal implements Externalizable {

	private static final long serialVersionUID = 1L;
	private static int countOfDeal =0;
	private String name;
	private int pid;
	private String description;

	//tr = task report
	private HashSet<TaskReport> taskReport = new HashSet<>();

	public Deal(){
		this.countOfDeal +=1;
		pid= countOfDeal;

	}

	public void formId()
	{

	}

	public Deal(String name, int id, String description, HashSet<TaskReport> tr) {
		super();
		this.name = name;
		this.countOfDeal +=1;
		pid=this.countOfDeal;
		this.description = description;
		this.taskReport = tr;
	}





	public Deal(String name, String description) {
		super();
		this.name = name;
		this.countOfDeal +=1;
		pid=this.countOfDeal;
		this.description = description;
	}

	public void setName(String new_name) {
		name = new_name;
	}
	public void setDescription(String new_desc) {
		description = new_desc;
	}
	//private void makeId() {}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}

	public int getId() {
		return pid;
	}



	public HashSet<TaskReport> getTr() {
		return taskReport;
	}

	public void setTr(HashSet<TaskReport> tr) {
		this.taskReport = tr;
	}
	@Override
	public String toString() {
		String s =new String() ;
		for(TaskReport i : taskReport)
		{
			s+=i.toString();
		}

		return "Name of deal: " + name + "\n" +
				"Description of deal: " + description + "\n"+s;
		//"ID of deal: " + Integer.toString(id) + "\n";
	}
	//input to file
	public void outputToFile(String fileName) {
		try (FileWriter writer = new FileWriter(fileName, false)) {
			writer.write(this.toString());
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	//read from file
	public void inputToFile(String fileName) {//не дописано!!!
		try (FileReader reader = new FileReader(fileName)) {


		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//serialization
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this.getName());
		out.writeObject(this.getDescription());
		out.writeObject(this.taskReport.size());
		for(TaskReport i : taskReport)
		{
			i.writeExternal(out);
		}
	}

	//deserialization
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int count;
		name=(String)in.readObject();
		description=(String)in.readObject();
		count=(int)in.readObject();
		for(int i=0; i<count;i++)
		{
			TaskReport e = new TaskReport();
			e.readExternal(in);
			taskReport.add(e);
		}



	}

}
