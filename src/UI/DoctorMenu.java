package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import VetClinicPOJOs.Owner;
import projectJDBC.JDBCAppointmentManager;
import projectJDBC.JDBCDoctorManager;
import projectJDBC.JDBCPatientManager;
import projectPOJOs.Appointment;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public class DoctorMenu {
	
	private static Doctor d;
	private static Patient p;
	private static List<Patient> patients;
	private static Appointment a;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void menu(JDBCDoctorManager dmanager, JDBCPatientManager pmanager, JDBCAppointmentManager amanager, String email) {
		//DUDA!!! EN VETCLINIC no pasa nada como argumento para el menu, ESTA BIEN LO NUESTRO???
		//DUDA->NO TENDRIAMOS QUE PONER UN MAIN????
		//EN SCHEDULEAPPOINTMENT FALTARIA PONER LA FECHA COMO ARGUMENTO
		//FALTA PONER CON TABLAS EL EDIT
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
					editInformation();
					break;
				case 2:
					createPatient(dmanager,pmanager);
					break;
				case 3:
					scheduleAppointment(dmanager, pmanager, amanager, email,);
					break;
				case 4:
					cancelAppointment();
					break;
				case 5:
					orderDevices();
					break;
				case 6:
					viewInformationPatient();
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

	private static void createPatient(JDBCDoctorManager doctormanager,JDBCPatientManager patientmanager) throws Exception {
		// TODO Auto-generated method stub
		// llamará al método addPatient para añadirlo a la lista de pacientes de este Doctor
		System.out.println("Type the email of the patient");
		String email = reader.readLine();
		System.out.println("Type the name of the patient");
		String name = reader.readLine();
		System.out.println("Type the surname of the patient");
		String surname = reader.readLine();
		System.out.println("Type the birthday of the patient in formal yyyy/mm/dd");
		String dob = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date birthday = (Date) df.parse(dob);
		System.out.println("Type the diagnosis of the patient");
		String diagnosis = reader.readLine();
		System.out.println("Type the doctor id of the patient");
		Integer doctor_id = Integer.parseInt(reader.readLine());
		
		Doctor o = doctormanager.searchDoctorById(doctor_id);
		Patient p=new Patient(email,name,surname,birthday,diagnosis);
		patientmanager.addPatient(p);
		

	}

	private static void editInformation() {
		
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
					editName(dmanager);
					break;
				case 2:
					editSurname(dmanager);
					break;
				case 3:
					editSpecialty(dmanager);
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
	
	
	private static void editName(JDBCDoctorManager doctormanager)throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Email: ");
		String email = reader.readLine();
		d = doctormanager.getDoctorByEmail(email);
		System.out.println("Enter new name: ");
		String name = reader.readLine();
		doctormanager.editName(d,name);//NO ENTIENDO XQ ME DICE QUE ESTA MAL
		
		
	}
	
	private static void editSurname(JDBCDoctorManager doctormanager)throws Exception  {
		// TODO Auto-generated method stub
				
		System.out.println("Email: ");
		String email = reader.readLine();
		d = doctormanager.getDoctorByEmail(email);
		System.out.println("Enter new surname: ");
		String surname = reader.readLine();
		doctormanager.editSurname(d,surname);
				
		
	}
	
	private static void editSpecialty(JDBCDoctorManager doctormanager) throws Exception{
		System.out.println("Email: ");
		String email = reader.readLine();
		d = doctormanager.getDoctorByEmail(email);
		System.out.println("Enter new specialty: ");
		String specialty = reader.readLine();
		doctormanager.editSpecialty(d,specialty);
		
		
		
		// TODO Auto-generated method stub
		
	}

	private static void viewInformationPatient() {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("1. Choose Patient");
				System.out.println("0. Return");

				choice = Integer.parseInt(reader.readLine());
				
				switch(choice) {
				case 1:
					int id;
					System.out.println("Enter patient's id: ");
					id = Integer.parseInt(reader.readLine());
					
					System.out.println(p.toString());
					
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

	private static void orderDevices() {
		// TODO Auto-generated method stub
		
	}

	private static void cancelAppointment() {
		// TODO Auto-generated method stub
		
	}

	private static void scheduleAppointment(JDBCDoctorManager doctormanager, JDBCPatientManager patientmanager, JDBCAppointmentManager appointmentmanager) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("Email: ");
		String email = reader.readLine();
		d = doctormanager.getDoctorByEmail(email);
		patients = patientmanager.getListOfPatients();
		System.out.println("Enter patient's id: ");
		int id = Integer.parseInt(reader.readLine());
		p = patientmanager.getPatientById(id);
		System.out.println("Type the date in formal yyyy/mm/dd");
		String dob = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date date = (Date) df.parse(dob);
		appointmentmanager.addAppointment(d, p, date);
	}

}







