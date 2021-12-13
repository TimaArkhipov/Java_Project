package com.example.timetracker;

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
	private List <Deal> deals = new  ArrayList<>();
	public static String fileName;
	public static final String PATH_TO_PROPERTIES ="C:\\Users\\Vova\\OneDrive\\Desktop\\Lab_Java\\src\\Prop.properties";

	public Database(){}

	public Database(List<Deal> deals){
		this.deals = deals;
	}

    public List<Deal> getDeals() {
        return deals;
    }

	public static void preLoad()
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
	
	public void save(String fileName1) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(fileName1);
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	    objectOutputStream.writeObject(deals);
	    objectOutputStream.close();
	}

	public void load(String fileName1) throws Exception
	{
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(fileName1);
			objectInputStream = new ObjectInputStream(fileInputStream);
	    	this.deals = (List<Deal>) objectInputStream.readObject();
		}
		catch(Exception e)
		{
			throw new Exception("Can't read data");
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
		}
		
	}

	@Override
	public String toString() {
        String s = "";
        for (Deal i : deals) {
            s += i.toString() + '\n';
        }
        return s;
    }
}
