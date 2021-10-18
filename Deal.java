import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Deal {
	private String name;
	private int id;
	private String description;
	private HashSet<TaskReport> tr = new HashSet<TaskReport>();
	Deal(){
		name = "";
		description = "";
		id = 0;
	}	

	public Deal(String name, int id, String description, HashSet<TaskReport> tr) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
		this.tr = tr;
	}
	
	public void setName(String new_name) {
		name = new_name;
	}
	
	public void setDescription(String new_desc) {
		description = new_desc;
	}
	
	private void makeId() {
		// Можно сделать в виде счётчика
		// Пока не знаю как делать
	}
	
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
		return tr;
	}

	public void setTr(HashSet<TaskReport> tr) {
		this.tr = tr;
	}
	
	@Override
	public String toString() {
		return "Name of deal: " + name + "\n" +
				"Description of deal: " + description + "\n";
				//"ID of deal: " + Integer.toString(id) + "\n";
	}
	
	public void outputToFile(String fileName) {
		try (FileWriter writer = new FileWriter(fileName, false)) { 
			writer.write(this.toString());
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
