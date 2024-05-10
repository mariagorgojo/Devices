package projectInterfaces;

import java.util.List;

import projectPOJOs.Patient;

public interface PatientManager {
	
	public void createPatient(Patient p);
	public Patient getPatientById(Integer id);
	public List<Patient> getListOfPatients();
	
}
