package projectInterfaces;

import java.util.List;

import projectPOJOs.Doctor;

public interface DoctorManager {
	
	public void createDoctor(Doctor d);
	public List<Doctor> getListOfDoctors();
	public Doctor searchDoctorById(Integer id);

}