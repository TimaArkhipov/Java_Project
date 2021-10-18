
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		HashSet<TaskReport> tr = new HashSet<>();
		for(int i=0; i<4;i++)
		{
			TaskReport e = new TaskReport(new Date(0),new Date(20));
			tr.add(e);
		}
		Deal thing=new Deal("Death",1931707,"Smert-smert-smert", tr);
		FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Vova\\OneDrive\\Desktop\\Lab_Java\\src\\test.txt");
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	    objectOutputStream.writeObject(thing);

	   FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Vova\\OneDrive\\Desktop\\Lab_Java\\src\\test.txt");
	    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	    Deal thing2=(Deal) objectInputStream.readObject();
	    System.out.print(thing2);
	    objectInputStream.close();
	}

}
