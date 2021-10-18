import java.io.FileWriter;
import java.io.FileReader;

import java.io.Externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;

public class Deal implements Externalizable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int id;
	private String description;
	
	//tr = task report
	private HashSet<TaskReport> taskReport = new HashSet<TaskReport>();
	
	public Deal(){
		
	}
	

	public Deal(String name, int id, String description, HashSet<TaskReport> tr) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
		this.taskReport = tr;
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
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
//вывод в файл
	public void outputToFile(String fileName) {
		try (FileWriter writer = new FileWriter(fileName, false)) { 
			writer.write(this.toString());
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
//чтение с файла
	public void inputToFile(String fileName) {//не дописано!!!
		try (FileReader reader = new FileReader(fileName)) { 
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

//сериализация
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

	//десериализация
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
