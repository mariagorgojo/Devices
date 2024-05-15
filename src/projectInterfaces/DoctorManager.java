package projectInterfaces;

import java.util.List;

import projectPOJOs.Appointment;
import projectPOJOs.Device;
import projectPOJOs.Doctor;

public interface DoctorManager {
	
	public void createDoctor(Doctor d);
	public List<Doctor> getListOfDoctors();
	public Doctor searchDoctorById(Integer id);
	public Doctor getDoctorByEmail(String email);
	public void orderDevice(Device d);
	
	public void editName(Doctor d,String name);
	public void editSurname(Doctor d,String surname);
	public void editSpecialty(Doctor d, String specialty);

}