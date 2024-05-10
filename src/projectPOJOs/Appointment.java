package projectPOJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Appointment implements Serializable {

	//main de prueba
	/*public static void main(String[] args) {
		
		Appointment a = new Appointment(1, null, "prueba");
		System.out.println(a);
	}*/
	
	//
	private static final long serialVersionUID = 5598607402823796111L;
	
	private int id;
	private Date date;
	private String description;
	private Doctor d;
	private Patient p;
	
	public Appointment(Date date, String description, Doctor d, Patient p) {
		super();
		this.date = date;
		this.description = description;
		this.setDoctor(d);
		this.setPatient(p);
	}

	public Appointment(int id, Date date, String description, Doctor d, Patient p) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
		this.setDoctor(d);
		this.setPatient(p);
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

	public Doctor getDoctor() {
		return d;
	}

	public void setDoctor(Doctor d) {
		this.d = d;
	}

	public Patient getPatient() {
		return p;
	}

	public void setPatient(Patient p) {
		this.p = p;
	}

	@Override
	public int hashCode() {
		return Objects.hash(d, date, description, id, p);
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
		return Objects.equals(d, other.d) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && id == other.id && Objects.equals(p, other.p);
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", description=" + description + ", d=" + d + ", p=" + p
				+ "]";
	}
	

}
