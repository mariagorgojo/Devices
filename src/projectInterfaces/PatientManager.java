package projectInterfaces;

import java.util.List;

import projectPOJOs.Patient;

public interface PatientManager {
	
	public void addPatient(Patient p);
	public Patient getPatientById(Integer id);
	public List<Patient> getListOfPatients();
	void createPatient(Patient p);
	
}
