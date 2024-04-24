package projectPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Doctor implements Serializable{
	private static final long serialVersionUID = -4423025978446522586L;
	private int id;
	private String name;
	private String surname;
	private String speciality;
	
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

	public String getSpeciality() {
		return speciality;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, speciality, surname);
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
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(speciality, other.speciality)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", surname=" + surname + ", speciality=" + speciality + "]";
	}

	
	
	
	
	
	
	
}
