package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import projectIfaces.XMLManager;
import projectIfaces.UserManager;
import projectJPA.JPAUserManager;
import projectPOJOs.Doctor;
import projectPOJOs.Manufacturer;
import projectPOJOs.Patient;
import projectPOJOs.Role;
import projectPOJOs.User;
import projectXML.XMLManagerImpl;
import projectJDBC.JDBCAppointmentManager;
import projectJDBC.JDBCDeviceManager;
import projectJDBC.JDBCDoctorManager;
import projectJDBC.JDBCManager;
import projectJDBC.JDBCManufacturerManager;
import projectJDBC.JDBCPatientManager;


public class Menu {
	private static JDBCManager jdbcmanager;
	private static JDBCDoctorManager doctormanager;
	private static JDBCPatientManager patientmanager;
	private static JDBCManufacturerManager manufacturermanager;
	private static JDBCAppointmentManager appointmentmanager;
	private static JDBCDeviceManager devicemanager;
	private static UserManager usermanager;
	private static User u;
	private static Patient p;
	private static Doctor d;
	private static Manufacturer m;
	private static XMLManager xmlmanager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		
		jdbcmanager = new JDBCManager();
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		patientmanager = new JDBCPatientManager(jdbcmanager);
		manufacturermanager = new JDBCManufacturerManager(jdbcmanager);
		appointmentmanager = new JDBCAppointmentManager(jdbcmanager);
		devicemanager = new JDBCDeviceManager(jdbcmanager);
		usermanager = new JPAUserManager();
		xmlmanager = new XMLManagerImpl(jdbcmanager);
		
		try {
			int choice;
			
			do {
				
				System.out.println("Choose an option");
				System.out.println("1. Login User");
				System.out.println("2. Sign-up new user");
				System.out.println("3. Udpate the password of an exissting user");
				System.out.println("4. Delete user");
				System.out.println("0. Exit");
								
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					login();		
					break;
				case 2:
					signUpUser();
					break;
				case 3: 
					updatePassword();
					break;
				case 4:
					deleteUser();
					
					
				case 0:
					System.out.println("Exiting application.");
					jdbcmanager.disconnect();
					break;
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//completado
   	private static void updatePassword() throws Exception {
		
		System.out.println("Email: ");
		String email = reader.readLine();
				
		System.out.println("Enter current Password: ");
		String passwd = reader.readLine();
		
		System.out.println("Enter new Password: ");
		String new_passwd = reader.readLine();
				
		u = usermanager.checkPassword(email, passwd);
				
		if(u!=null){ //if user already existed
			u = usermanager.changePassword(u, new_passwd);
		}
				
	}

	//completado
	private static void login() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Email: ");
		String email = reader.readLine();
		
		System.out.println("Password: ");
		String passwd = reader.readLine();
		
		User u = usermanager.checkPassword(email, passwd);
		
		//call for submenus
		if(u==null) {
			throw new Exception("User does not exist");
			
		}else if(u.getRole().getName().equals("doctor")){ //user is a doctor, we open doctor menu
				System.out.println("Login of doctor successful!");
				DoctorMenu.menu(doctormanager, patientmanager, appointmentmanager, devicemanager, email);
				
			}else if(u.getRole().getName().equals("patient")) { //user is a patient, we open patient menu
				System.out.println("Login of patient successful!");
				PatientMenu.menu(patientmanager, doctormanager, appointmentmanager, devicemanager, email, xmlmanager);
				
			}else if(u.getRole().getName().equals("manufacturer")){ //user is a manufacturer, we open manufacturer menu
				System.out.println("Login of manufacturer successful!");
				ManufacturerMenu.menu(doctormanager, manufacturermanager, email, xmlmanager);
			}
		
	}

	//REVISAR DUDA
	private static void signUpUser() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce email: ");
			String email = reader.readLine();
			System.out.println("Introduce password: ");
			String password = reader.readLine();
			
			// no entiendo
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			//
			
			System.out.println("Introduce the role of the user. 1: doctor, 2: patient, 3: manufacturer");
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);
			
			User u = new User(email, pass, r);
			
			usermanager.newUser(u);
			
			if(rol==1) {
				//initialising a doctor
				d = infoDoctor(email);
				doctormanager.createDoctor(d);
			}else if(rol==2) {
				//initialising a patient
				p = infoPatient(email);
				patientmanager.createPatient(p);
			}else if(rol==3) {
				//initialising a manufacturer
				m = infoManufacturer(email);
				manufacturermanager.createManufacturer(m);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static Doctor infoDoctor(String email) throws Exception {
		
		System.out.println("Introduce your name: ");
		String name = reader.readLine();
		System.out.println("Introduce your surname: ");
		String surname = reader.readLine();
		System.out.println("Introduce your specialty: ");
		String specialty = reader.readLine();
		d = new Doctor(email, name, surname, specialty);
		
		return d;
	}

	private static Manufacturer infoManufacturer(String email) throws Exception {
		
		System.out.println("Introduce your name: ");
		String name = reader.readLine();
		System.out.println("Introduce your address: ");
		String address = reader.readLine();
		System.out.println("Introduce your birthday in format (yyyy/mm/dd): ");
		int phonenumber = Integer.parseInt(reader.readLine());
		m = new Manufacturer(email, name, address, phonenumber);
		
		return m;
	}

	private static Patient infoPatient(String email) throws Exception {
		
		System.out.println("Introduce your name: ");
		String name = reader.readLine();
		System.out.println("Introduce your surname: ");
		String surname = reader.readLine();
		System.out.println("Introduce your birthday in format (yyyy/mm/dd): ");
		String dob = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date birthday = (Date) df.parse(dob);
		String diagnosis = "";
		p = new Patient(email, name, surname, birthday, diagnosis);
		
		return p;
	}
	
	private static void deleteUser() throws IOException {
		System.out.println("introduce email of the user you want to delete:");
		String email=reader.readLine();
		usermanager.deleteUserByEmail(email);

	}
	
}
