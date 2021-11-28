package com.example.timetracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface SLDeal {


    public static void save(Deal deal) throws IOException
    {

        String fileName=deal.getName()+".bin";

        File f=new File(fileName);
        f.createNewFile();


        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(deal);
        objectOutputStream.close();
    }

    public static void load(Deal deal) throws Exception
    {
        String fileName=deal.getName()+".bin";
        FileInputStream fileInputStream=null;
        ObjectInputStream objectInputStream=null;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            deal=(Deal) objectInputStream.readObject();


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
}
