package core;

import java.util.Date;

public class TaskReport {//TaskReport
	private int grade;
	private String comment;
	private Date dateStart;
	private Date dateStop;
	private Deal deal;
	
	TaskReport(){ 
		grade = 0;
		comment = "";
	}
	
	public void setGrade(int new_grade){
		grade = new_grade;
	}
	public void setComment(String new_comment) {
		comment = new_comment;
	}
	public int getGrade() {
		return grade;
	}
	public String getComment () {
		return comment;
	}
	public void setMode(boolean flag) {
		if (flag == false) {
		//false - òàéìåð, true - ñåêóíäîìåð
			
		}
		else {
			
		}
	}
}

