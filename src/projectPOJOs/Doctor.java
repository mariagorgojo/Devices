package projectPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor implements Serializable{
	
	private static final long serialVersionUID = -4423025978446522586L;
	
	private int id;
	private String name;
	private String surname;
	private String specialty;
	private List<Patient> patients = new ArrayList<>();
	private List<Device> devicesOrdered = new ArrayList<>(); //o al doctor se la pela saber los que ha pedido una vez pedidos??????
	
	//lista de appointments??? por paciente???
	
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

	public Patient searchPatientById(int id) {
		Patient patient = null;
		for(Patient p : patients) {
			if(p.getId()==id) {
				patient = p;
			}
		}
		
		return patient;
	}
	
	//el metodo añadir paciente a la lista de pacientes del doctor añadirá un paciente recién creado que se le pasará por pantalla  
	
}
