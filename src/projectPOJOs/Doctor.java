package projectPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Doctor implements Serializable{
	
	private static final long serialVersionUID = -4423025978446522586L;
	
	private int id;
	private String name;
	private String surname;
	private String specialty;
	
	public Doctor(String name, String surname, String speciality) {
		super();
		this.name = name;
		this.surname = surname;
		this.specialty = speciality;
	}

	public Doctor(int id, String name, String surname, String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.specialty = speciality;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, name, specialty, surname);
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
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(specialty, other.specialty)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", surname=" + surname + ", speciality=" + specialty + "]";
	}

	
	
	
	
	
	
	
}
