package projectPOJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = -4423025978446522586L;
	
	private int id;
	private String name;
	private String surname;
	private String device;
	private Date birthday;
	private String diagnosis;
	private List<Device> devices = new ArrayList<>();
	private List<Appointment> appointments = new ArrayList<>();
	
	public Patient(String name, String surname, String device, Date birthday, String diagnosis) {
		super();
		this.name = name;
		this.surname = surname;
		this.device = device;
		this.birthday = birthday;
		this.diagnosis = diagnosis;
	}

	public Patient(int id, String name, String surname, String device, Date birthday, String diagnosis) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.device = device;
		this.birthday = birthday;
		this.diagnosis = diagnosis;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(birthday, device, diagnosis, id, name, surname);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(device, other.device)
				&& Objects.equals(diagnosis, other.diagnosis) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + ", device=" + device + ", birthday="
				+ birthday + ", diagnosis=" + diagnosis + "]";
	}
	
	

}
