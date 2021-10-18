import java.util.Date;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public class TaskReport implements Externalizable{//TaskReport
	private static final long serialVersionUID = 1L;
	private int grade;//оценка
	private String comment;//комментарий
	private Date dateStart;//время начала
	private Date dateStop;//время завершения
	//private Deal deal;//к какому делу относится задание
	
	
	
	public TaskReport(){ 
		
		
		
	}
	
	TaskReport(/*Deal deal,*/ Date dateStart,Date dateStop){ 
		this.dateStart=dateStart;
		this.dateStop=dateStop;
		//this.deal=deal;
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
	
	
	public void setMode(boolean flag) {
		//false - таймер, true - секундомер
		if (flag == false) {
		
			
		}
		else {
			
			
		}
	}
	
	@Override	
	public String toString() {
        return /*"Deal's Name: " + deal.getName() + '\n' +*/
                "Grade: " + grade + '\n'
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
