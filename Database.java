package com.example.myapplication; //после com.example. надо писать <имя своего проекта>

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

public class Database implements Externalizable {
	private List <Deal> deals=new  ArrayList<>();
	public static String fileName;
	
	
	public static final String PATH_TO_PROPERTIES ="/Prop.properties";

	Database(){}

	Database(List<Deal> deals){
		this.deals=deals;
	}
	public static void PreLoad()
	{		 
	        FileInputStream fileInputStream;
          //инициализируем специальный объект Properties
	        //типа Hashtable для удобной работы с данными
	        Properties prop = new Properties();
	        try {
	            //обращаемся к файлу и получаем данные
	            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
	            prop.load(fileInputStream);
	 
	            fileName = prop.getProperty("fileName");
	          
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        }
	 
	}

	public List<Deal> getDeals() {
		return deals;
	}

	public void save() throws IOException {
		  FileOutputStream fileOutputStream = new FileOutputStream(fileName);
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	    objectOutputStream.writeObject(deals);
	    objectOutputStream.close();
	}
	
	
	
	public void load() throws Exception 
	{
		FileInputStream fileInputStream=null;
		ObjectInputStream objectInputStream=null;
		try {
		  fileInputStream = new FileInputStream(fileName);
		  objectInputStream = new ObjectInputStream(fileInputStream);
	    this.deals=(List<Deal>) objectInputStream.readObject();
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
  
	@Override
	public void writeExternal(ObjectOutput newOutput) throws IOException {
		newOutput.writeObject(this.deals.size());
		for(Deal i : deals)
		{
			i.writeExternal(newOutput);
		}
	}
	@Override
	public void readExternal(ObjectInput newInput) throws IOException, ClassNotFoundException {
		int count = (int) newInput.readObject();
		for(int i = 0; i < count; i++)
		{
			Deal m = new Deal();
			m.readExternal(newInput);
			deals.add(m);
			//System.out.println("Goodit");
		}
		
	}
	@Override
	public String toString() {
		String s = "";
		for(Deal i : deals)
		{
        s += i.toString()+'\n';
    }
		return s;
	}
	
	public static void main(String[] args)
	{
		Database.PreLoad();
		List<Deal> b = new ArrayList<>();
		for(int j = 0; j < 3; j++)
		{
		HashSet<TaskReport> tr = new HashSet<>();
		for(int i = 0; i < 4; i++)
		{
			TaskReport e = new TaskReport(new Date(0), new Date(20));
			tr.add(e);
		}
		Deal thing = new Deal("Death", 1931707, "Smert-smert-smert", tr);
		b.add(thing);
		}
	
		Database a=new Database(b);
		
		try {
		a.save();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		Database d=new Database();
		try {
		d.load();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
		System.out.println(d.toString());
	}
}
