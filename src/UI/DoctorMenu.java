package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import projectJDBC.JDBCAppointmentManager;
import projectJDBC.JDBCDoctorManager;
import projectJDBC.JDBCPatientManager;
import projectPOJOs.Appointment;
import projectPOJOs.Device;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public class DoctorMenu {
	
	private static Doctor d;
	private static Patient p;
	private static Device device;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void menu(JDBCDoctorManager dmanager, JDBCPatientManager pmanager, JDBCAppointmentManager amanager, String email) {
			// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit personal information");
				System.out.println("2. Add new patient");
				System.out.println("3. Schedule appointment");
				System.out.println("4. Cancel appointment");
				System.out.println("5. Order devices.");
				System.out.println("6. View information about a patient");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					editInformation(dmanager, email);
					break;
				case 2:
					createPatient(dmanager, pmanager);
					break;
				case 3:
					scheduleAppointment(dmanager, pmanager, email);
					break;
				case 4:
					cancelAppointment(dmanager);
					break;
				case 5:
					orderDevice(dmanager);
					break;
				case 6:
					viewInformationPatient(pmanager);
					break;
				case 0:
					System.out.println("Back to main menu");
					break;
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//REVISAR
	private static void createPatient(JDBCDoctorManager doctormanager,JDBCPatientManager patientmanager) throws Exception {
		// TODO Auto-generated method stub
		// llamará al método addPatient para añadirlo a la lista de pacientes de este Doctor
		System.out.println("Enter the email of the patient");
		String email = reader.readLine();
		System.out.println("Enter the name of the patient");
		String name = reader.readLine();
		System.out.println("Enter the surname of the patient");
		String surname = reader.readLine();
		System.out.println("Enter the birthday of the patient in format yyyy/mm/dd");
		String dob = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date birthday = (Date) df.parse(dob);
		System.out.println("Type the diagnosis of the patient");
		String diagnosis = reader.readLine();
		System.out.println("Type the doctor id of the patient");
		Integer doctor_id = Integer.parseInt(reader.readLine());
		
		doctormanager.searchDoctorById(doctor_id);
		Patient p = new Patient(email,name,surname,birthday,diagnosis);
		patientmanager.addPatient(p);
		
	}

	//ok
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

	//ok
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

	//ok
	private static void printPatient(JDBCPatientManager pmanager) throws Exception {
		System.out.println("Enter patient's id: ");
		int id = Integer.parseInt(reader.readLine());
		p = pmanager.getPatientById(id);
		System.out.println(p.toString());
	}
	
	//ok
	private static void orderDevice(JDBCDoctorManager dmanager) throws Exception {
		// TODO Auto-generated method stub
		
		//type of device
		System.out.println("Enter type: ");
		String type = reader.readLine();
		//implantation date
		System.out.println("Enter date of implantation in format yyyy/mm/dd: ");
		String date1 = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date implantationDate = (Date) df.parse(date1);
		//expiration date
		System.out.println("Enter date of expiration in format yyyy/mm/dd: ");
		String date2 = reader.readLine();
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		Date expirationDate = (Date) df2.parse(date2);
		
		device = new Device(type, implantationDate, expirationDate);
		dmanager.orderDevice(device);
	}

	//ok
	private static void cancelAppointment(JDBCDoctorManager doctormanager)throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Enter the appointments'id: ");
		Integer a_id = Integer.parseInt(reader.readLine());
		doctormanager.deleteAppointment(a_id);
		
	}

	//ok
	private static void scheduleAppointment(JDBCDoctorManager doctormanager, JDBCPatientManager patientmanager, String email) throws Exception{
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
		d = doctormanager.getDoctorByEmail(email);
		//patient
		System.out.println("Enter patient's id: ");
		Integer p_id = Integer.parseInt(reader.readLine());
		p = patientmanager.getPatientById(p_id);
		
		Appointment a = new Appointment(date, description, d, p);
		doctormanager.addAppointment(a);
	}

}







