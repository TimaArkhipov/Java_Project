package core;

import java.util.*;

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
	private void makeId() {}
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
	
}
