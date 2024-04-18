package projectdbPOJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Appointment implements Serializable {

	//main de prueba
	public static void main(String[] args) {
		
		Appointment a = new Appointment(1, null, "prueba");
		System.out.println(a);
	}
	
	//
	private static final long serialVersionUID = 5598607402823796111L;
	
	private int id;
	private Date date;
	private String description;
	
	public Appointment(Date date, String description) {
		super();
		this.date = date;
		this.description = description;
	}
	
	public Appointment(int id, Date date, String description) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(date, other.date) && Objects.equals(description, other.description) && id == other.id;
	}
	
	@Override
	public String toString() {
		return "Appointment [id= " +id+ ", date= " +date+ ", description= " +description+ "]";
	}
	

}
