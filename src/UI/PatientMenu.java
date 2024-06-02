package UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import projectIfaces.XMLManager;
import projectJDBC.JDBCAppointmentManager;
import projectJDBC.JDBCDeviceManager;
import projectJDBC.JDBCDoctorManager;
import projectJDBC.JDBCPatientManager;
import projectPOJOs.Appointment;
import projectPOJOs.Device;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public class PatientMenu {
	
	private static List<Doctor> doctors = new ArrayList<Doctor>();
	private static Doctor d;
	private static Patient p;
	private static List<Device> devices = new ArrayList<Device>();
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void menu(JDBCPatientManager pmanager, JDBCDoctorManager dmanager, JDBCAppointmentManager amanager, JDBCDeviceManager devicemanager, String email, XMLManager xmlmanager) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit personal information");
				System.out.println("2. Schedule appointment");
				System.out.println("3. Cancel appointment");
				System.out.println("4. View information about devices");
				System.out.println("5. Print me to xml");
				System.out.println("6. Load patient from xml");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					editInformation(pmanager, email);
					break;
				case 2:
					scheduleAppointment(dmanager, pmanager, amanager, email);
					break;
				case 3:
					cancelAppointment(amanager);
					break;
				case 4:
					viewInfoDevices(pmanager, devicemanager, email);
					break;
				case 5:
					printMe(xmlmanager, pmanager, email);
					break;
				case 6:
					loadPatient(xmlmanager);
				case 0:
					System.out.println("Back to main menu");
					break;
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//case 6
	private static void loadPatient(XMLManager xmlmanager) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Introduce the path of the xml file: ");
		String path = reader.readLine();
		File file = new File(path);
		p = xmlmanager.xml2Patient(file);
		
		System.out.print(p);
	}
	
	//case 5
	private static void printMe(XMLManager xmlmanager, JDBCPatientManager patientmanager, String email) {
		p = patientmanager.getPatientByEmail(email);
		xmlmanager.patient2xml(p.getId());
		xmlmanager.simpleTransform("./xmls/Patient.xml", "./xmls/patient-style.xslt", "./xmls/patient.html");
	}
	
	//case 4
	private static void viewInfoDevices(JDBCPatientManager patientmanager, JDBCDeviceManager devicemanager, String email) {
		// TODO Auto-generated method stub
		p = patientmanager.getPatientByEmail(email);
		Integer p_id = p.getId();
		
		devices = devicemanager.getListOfDevices(p_id);
		for(Device d : devices) {
			System.out.println(d.toString());
		}
	}

	//case 2
	private static void scheduleAppointment(JDBCDoctorManager doctormanager, JDBCPatientManager patientmanager, JDBCAppointmentManager amanager, String email) throws Exception{
		// TODO Auto-generated method stub

		//ask for date of appointment
		System.out.println("Enter date of appointment in format yyyy/mm/dd: ");
		String ad = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date date = (Date) df.parse(ad);
		//description
		System.out.println("Description: ");
		String description = reader.readLine();
		//doctor
		doctors = doctormanager.getListOfDoctors();
		System.out.println("Doctors available: ");
		for(Doctor d : doctors) {
			System.out.println(d.toString());
		}
		System.out.println("Enter doctor's id: ");
		Integer d_id = Integer.parseInt(reader.readLine());
		d = doctormanager.searchDoctorById(d_id);
		//patient
		p = patientmanager.getPatientByEmail(email);
		
		Appointment a = new Appointment(date, description, d, p);
		amanager.addAppointment(a);
	}
	
	//REVISAR, imprimir la lista de los appointments, comprobar que el patient_id coincida para que no pueda cancelar un appointment que no sea suyo
	//case 3
	private static void cancelAppointment(JDBCAppointmentManager amanager)throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Enter the appointments'id: ");
		Integer a_id = Integer.parseInt(reader.readLine());
		amanager.deleteAppointment(a_id);
	}

	//case 1
    private static void editInformation(JDBCPatientManager pmanager, String email) {
		
    	p = pmanager.getPatientByEmail(email);
    	Integer id = p.getId();
    			
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit name");
				System.out.println("2. Edit surname");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
				
				switch(choice) {
				case 1:
					editName(pmanager, id);
					break;
				case 2:
					editSurname(pmanager, id);
					break;
				case 0:
					System.out.println("Back to patient menu");
					break;
				}
				
			}while(choice!=0);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    //ok
    private static void editName(JDBCPatientManager pmanager, Integer id)throws Exception {
		// TODO Auto-generated method stub

		p = pmanager.getPatientById(id);
		System.out.println("Enter new name: ");
		String name = reader.readLine();
		pmanager.editName(p,name);
		
	}
    
    //ok
    private static void editSurname(JDBCPatientManager pmanager, Integer id)throws Exception  {
		// TODO Auto-generated method stub
				
		p = pmanager.getPatientById(id);
		System.out.println("Enter new surname: ");
		String surname = reader.readLine();
		pmanager.editSurname(p, surname);
				
	}

}
