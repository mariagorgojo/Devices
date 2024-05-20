package projectIfaces;

import java.util.List;

import projectPOJOs.Device;
import projectPOJOs.Patient;

public interface PatientManager {
	
	public void addPatient(Patient p);
	public Patient getPatientById(Integer id);
	public List<Patient> getListOfPatients();
	public void createPatient(Patient p);
	public Patient getPatientByEmail(String email);
	
	public void editName(Patient p, String name);
	public void editSurname(Patient p, String surname);
	public void editDiagnosis(Patient p, String diagnosis);
	
}
