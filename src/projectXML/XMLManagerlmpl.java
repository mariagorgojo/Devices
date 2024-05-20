package projectXML;

import projectIfaces.DoctorManager;
import projectIfaces.ManufacturerManager;
import projectIfaces.PatientManager;
import projectIfaces.XMLManager;
import projectJDBC.JDBCManager;

public class XMLManagerlmpl implements XMLManager{

	private JDBCManager manager;
	private DoctorManager doctormanager;
	private PatientManager patientmanager;
	private ManufacturerManager manufacturermanager;
	
	
}
