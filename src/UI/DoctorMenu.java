package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import projectJDBC.JDBCAppointmentManager;
import projectJDBC.JDBCDeviceManager;
import projectJDBC.JDBCDoctorManager;
import projectJDBC.JDBCPatientManager;
import projectPOJOs.Appointment;
import projectPOJOs.Device;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public class DoctorMenu {
	
	private static List<Patient> patients = new ArrayList<Patient>();
	private static List<Device> devices = new ArrayList<Device>();
	private static List<Appointment> appointments = new ArrayList<Appointment>();
	private static Doctor d;
	private static Patient p;
	private static Device device;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void menu(JDBCDoctorManager dmanager, JDBCPatientManager pmanager, JDBCAppointmentManager amanager, JDBCDeviceManager devicemanager, String email) {
			// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit personal information");
				System.out.println("2. Schedule appointment");
				System.out.println("3. Cancel appointment");
				System.out.println("4. Order device");
				System.out.println("5. Assign device to patient");
				System.out.println("6. View information about a patient");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					editInformation(dmanager, email);
					break;
				case 2:
					scheduleAppointment(dmanager, pmanager, amanager, email);
					break;
				case 3:
					cancelAppointment(amanager, dmanager, pmanager, email);
					break;
				case 4:
					orderDevice(devicemanager, dmanager, email);
					break;
				case 5:
					assignDevice(devicemanager, pmanager, email);
					break;
				case 6:
					viewInformationPatient(pmanager);
					break;
				case 0:
					System.out.println("Back to main menu");
				default:
					System.out.println("Invalid option");
					break;				
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//case 5
	private static void assignDevice(JDBCDeviceManager devicemanager, JDBCPatientManager patientmanager, String email) throws IOException {
		// TODO Auto-generated method stub

		//selection of patient
		patients = patientmanager.getListOfPatients();
		System.out.println("Patients available: ");			
		for(Patient p : patients) {
			System.out.println(p.toString());
		}
		System.out.println("Enter patient's id: ");
		Integer p_id = Integer.parseInt(reader.readLine());
		p = patientmanager.getPatientById(p_id);
		
		//selection of device
		devices = devicemanager.getDevices();
		System.out.println("Devices available: ");
		int count = 0;
		for(Device d : devices) {
			if(count<5) {
				System.out.println(d.toString());
				count++;
			}else {
				break;
			}
		}
		System.out.println("Enter the type of the device: ");
		String type = reader.readLine();
		
		device = devicemanager.getDeviceByType(type);
		devicemanager.assignDeviceToPatient(device, p);
	}

	//case 1
	private static void editInformation(JDBCDoctorManager dmanager, String email) {
		
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit name");
				System.out.println("2. Edit surname");
				System.out.println("3. Edit specialty");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
				
				switch(choice) {
				case 1:
					editName(dmanager, email);
					break;
				case 2:
					editSurname(dmanager, email);
					break;
				case 3:
					editSpecialty(dmanager, email);
					break;
				case 0:
					System.out.println("Back to doctor menu");
					break;
				}
				
			}while(choice!=0);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//ok
	private static void editName(JDBCDoctorManager doctormanager, String email)throws Exception {
		// TODO Auto-generated method stub

		d = doctormanager.getDoctorByEmail(email);
		System.out.println("Enter new name: ");
		String name = reader.readLine();
		doctormanager.editName(d,name);
		
	}
	
	//ok
	private static void editSurname(JDBCDoctorManager doctormanager, String email)throws Exception  {
		// TODO Auto-generated method stub
				
		d = doctormanager.getDoctorByEmail(email);
		System.out.println("Enter new surname: ");
		String surname = reader.readLine();
		doctormanager.editSurname(d,surname);
				
	}
	
	//ok
	private static void editSpecialty(JDBCDoctorManager doctormanager, String email) throws Exception{
		// TODO Auto-generated method stub

		d = doctormanager.getDoctorByEmail(email);
		System.out.println("Enter new specialty: ");
		String specialty = reader.readLine();
		doctormanager.editSpecialty(d,specialty);
		
	}

	//case 6
	private static void viewInformationPatient(JDBCPatientManager pmanager) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("1. Choose Patient");
				System.out.println("0. Return\n");

				choice = Integer.parseInt(reader.readLine());
				
				switch(choice) {
				case 1:
					printPatient(pmanager);
					break;
				case 0:
					System.out.println("Back to doctor menu");
					break;					
				}

			}while(choice!=0);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void printPatient(JDBCPatientManager pmanager) throws Exception {
		patients = pmanager.getListOfPatients();
		System.out.println("Patients available: ");
		for(Patient p : patients) {
			System.out.println(p.toString());
		}
		
		System.out.println("Enter patient's id: ");
		int id = Integer.parseInt(reader.readLine());
		
		p = pmanager.getPatientById(id);
		System.out.println(p.toString());
	}
	
	//case 4
	private static void orderDevice(JDBCDeviceManager devicemanager, JDBCDoctorManager dmanager, String email) throws Exception {
		// TODO Auto-generated method stub
		
		//type of device
		System.out.println("Devices available: ");
		devices = devicemanager.getDevices();
		int count = 0;
		for(Device d : devices) {
			if(count<5) {
				System.out.println(d.toString());
				count++;
			}else {
				break;
			}
		}
		
		System.out.println("Enter type: ");
		String type = reader.readLine();
		
		device = devicemanager.getDeviceByType(type);
		d = dmanager.getDoctorByEmail(email);
		devicemanager.orderDevice(d, device);
	}

	//REVISAR, imprimir la lista de los appointments, comprobar que el doctor_id coincida para que no pueda cancelar un appointment que no sea suyo
	//case 3
	private static void cancelAppointment(JDBCAppointmentManager amanager, JDBCDoctorManager doctormanager, JDBCPatientManager patientmanager, String email)throws Exception {
		// TODO Auto-generated method stub
		d = doctormanager.getDoctorByEmail(email);
		
		appointments = amanager.getAppointmentsOfDoctor(d.getId(), doctormanager, patientmanager);
		System.out.println("Your appointments are: ");
		for(Appointment a : appointments) {
			System.out.println(a.toString());
		}
		
		System.out.println("Enter the appointments'id: ");
		Integer a_id = Integer.parseInt(reader.readLine());
		amanager.deleteAppointment(a_id);
		
	}

	//case 2
	private static void scheduleAppointment(JDBCDoctorManager doctormanager, JDBCPatientManager patientmanager, JDBCAppointmentManager amanager, String email) throws Exception{
		// TODO Auto-generated method stub

		//ask for date of appointment
		System.out.println("Introduce the date of the appointment in format (yyyy/MM/dd): ");
		String ad = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		//util Date
		java.util.Date date = df.parse(ad);
		//convert from util to sql Date
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		//description
		System.out.println("Description: ");
		String description = reader.readLine();
		//doctor
		d = doctormanager.getDoctorByEmail(email);
		//patient
		patients = patientmanager.getListOfPatients();
		System.out.println("Patients available: ");
		for(Patient p : patients) {
			System.out.println(p.toString());
		}
		System.out.println("Enter patient's id: ");
		Integer p_id = Integer.parseInt(reader.readLine());
		p = patientmanager.getPatientById(p_id);
		
		Appointment a = new Appointment(sqlDate, description, d, p);
		amanager.addAppointment(a);
	}

}







