package projectPOJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = -4423025978446522586L;
	
	private int id;
	private String email;
	private String name;
	private String surname;
	private Date birthday;
	private String diagnosis;
	private List<Device> devices = new ArrayList<Device>();
	private List<Appointment> appointments = new ArrayList<Appointment>();
	
	public Patient(String email, String name, String surname, Date birthday, String diagnosis) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.diagnosis = diagnosis;
	}

	public Patient(int id, String email, String name, String surname, Date birthday, String diagnosis) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.diagnosis = diagnosis;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, diagnosis, email, id, name, surname);
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
		return Objects.equals(birthday, other.birthday) && Objects.equals(diagnosis, other.diagnosis)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", email=" + email + ", name=" + name + ", surname=" + surname + ", birthday="
				+ birthday + ", diagnosis=" + diagnosis + ", devices=" + devices + ", appointments=" + appointments
				+ "]";
	}

	

	
	

}
