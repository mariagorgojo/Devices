package projectPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor implements Serializable{
	
	private static final long serialVersionUID = -4423025978446522586L;
	
	private int id;
	private String email;
	private String name;
	private String surname;
	private String specialty;
	private List<Appointment> appointments;
		
	public Doctor() {
		super();
		appointments = new ArrayList<>();
	}
	
	public Doctor(String email, String name, String surname, String specialty) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.specialty = specialty;
		appointments = new ArrayList<>();
	}

	public Doctor(int id, String email, String name, String surname, String specialty) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.specialty = specialty;
		appointments = new ArrayList<>();
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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, specialty, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(specialty, other.specialty) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", email=" + email + ", name=" + name + ", surname=" + surname + ", specialty="
				+ specialty + ", appointments=" + appointments + "]";
	}
	
	
}
