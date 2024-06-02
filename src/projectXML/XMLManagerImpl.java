package projectXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import projectIfaces.DeviceManager;
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

public class XMLManagerImpl implements XMLManager{

	private JDBCManager manager;
	private PatientManager patientmanager;
	private ManufacturerManager manufacturermanager;
	private DeviceManager devicemanager;
	
	public XMLManagerImpl(JDBCManager m) {
		super();
		this.manager = m;
		this.patientmanager = new JDBCPatientManager(manager);
		this.manufacturermanager = new JDBCManufacturerManager(manager);
		this.devicemanager = new JDBCDeviceManager(manager);

	}
	
	@Override
	public void patient2xml(Integer id) {
		Patient p=null;
		List<Device> devices=new ArrayList<Device>();
		
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
		Patient p = null;
		try {
			JAXBContext jaxbC = JAXBContext.newInstance(Patient.class);
			Unmarshaller jaxbU = jaxbC.createUnmarshaller();
			p = (Patient) jaxbU.unmarshal(xml);
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return p;
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
		Manufacturer m = null;
		try {
			JAXBContext jaxbC = JAXBContext.newInstance(Manufacturer.class);
			Unmarshaller jaxbU = jaxbC.createUnmarshaller();
			m = (Manufacturer) jaxbU.unmarshal(xml);			
						
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		return m;
	}
	
	@Override
	public void simpleTransform(String sourcePath, String xsltPath, String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
