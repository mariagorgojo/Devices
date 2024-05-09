package projectInterfaces;

import java.sql.Date;
import java.util.List;

import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public interface DoctorManager {
	
	public void createDoctor(Doctor d);
	public List<Doctor> getListOfDoctors();
	public Doctor searchDoctorById(Integer id);
	public void orderAppointment(Patient p, Date date);

}