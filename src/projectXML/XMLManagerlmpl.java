package projectXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


import projectIfaces.DeviceManager;
import projectIfaces.DoctorManager;
import projectIfaces.ManufacturerManager;
import projectIfaces.PatientManager;
import projectIfaces.XMLManager;
import projectJDBC.JDBCManager;
import projectJDBC.JDBCManufacturerManager;
import projectPOJOs.Patient;
import projectPOJOs.Device;
import projectPOJOs.Manufacturer;
import projectJDBC.JDBCPatientManager;
import projectJDBC.JDBCDeviceManager;

public class XMLManagerlmpl implements XMLManager{

	private JDBCManager manager;
	private DoctorManager doctormanager;
	private PatientManager patientmanager;
	private ManufacturerManager manufacturermanager;
	private DeviceManager devicemanager;
	
	@Override
	public void patient2xml(Integer id) {
		Patient p=null;
		List<Device> devices=new ArrayList<Device>();
		manager = new JDBCManager();
		patientmanager = new JDBCPatientManager(manager);
		devicemanager = new JDBCDeviceManager(manager);
		
		try {
			//Do a sql query to get the patient by the id
			p = patientmanager.getPatientById(id);
			//search for the devices of the patient
			devices = devicemanager.getListOfDevices(id);
			p.setDevices(devices);
			
			//export the patient to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Patient.xml");
			marshaller.marshal(p, file);
			System.out.print(p);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Patient xml2Patient(File xml) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void manufacturer2xml(Integer id){
		Manufacturer m = null;
		List<Device> devices=new ArrayList<Device>();
		manager = new JDBCManager();
		manufacturermanager = new JDBCManufacturerManager(manager);
		devicemanager = new JDBCDeviceManager(manager);
		
		try {
			//Do a sql query to get the manufacturer by the id
			m = manufacturermanager.getManufacturerById(id);
			//search for the devices of the manufacturer
			devices = devicemanager.getListOfDevices(id);
			m.setDevices(devices);
			
			//export the manufacturer to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Manufacturer.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Manufacturer.xml");
			marshaller.marshal(m, file);
			System.out.print(m);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Manufacturer xml2Manufacturer(File xml) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
