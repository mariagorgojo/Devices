package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import projectInterfaces.UserManager;
import projectJPA.JPAUserManager;
import projectPOJOs.Role;
import projectPOJOs.User;
import projectInterfaces.AppointmentManager;
import projectInterfaces.DeviceManager;
import projectInterfaces.DoctorManager;
import projectInterfaces.ManufacturerManager;
import projectInterfaces.PatientManager;
import projectJDBC.JDBCDoctorManager;
import projectJDBC.JDBCManager;
import projectJDBC.JDBCManufacturerManager;
import projectJDBC.JDBCPatientManager;


public class Menu {
	private static JDBCManager jdbcmanager;
	private static JDBCDoctorManager doctormanager;
	private static JDBCPatientManager patientmanager;
	private static JDBCManufacturerManager manufacturermanager;
	private static UserManager usermanager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		
		jdbcmanager = new JDBCManager();
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		patientmanager = new JDBCPatientManager(jdbcmanager);
		manufacturermanager = new JDBCManufacturerManager(jdbcmanager);
		usermanager = new JPAUserManager();
		
		try {
			int choice;
			
			do {
				
				System.out.println("Choose an option");
				System.out.println("1. Login User");
				System.out.println("2. Sign-up new user");
				System.out.println("3. Udpate the password of an exissting user");
				System.out.println("0. Exit.");
								
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
				case 0:
					System.out.println("Exiting application.");
					jdbcmanager.disconnect();
					break;
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
	
	//casi completado
	private static void updatePassword() throws Exception {
		
		System.out.println("Email: ");
		String email = reader.readLine();
				
		System.out.println("Enter current Password: ");
		String passwd = reader.readLine();
		
		System.out.println("Enter new Password: ");
		String new_passwd = reader.readLine();
				
		User u = usermanager.checkPassword(email, passwd);
				
		if(u!=null){ //if user already existed
			usermanager.changePassword(u, new_passwd); //método changePassword todavía por hacer
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
				DoctorMenu.menu(doctormanager, patientmanager, email);
				
			}else if(u.getRole().getName().equals("patient")) { //user is a patient, we open patient menu
				System.out.println("Login of doctor successful!");
				PatientMenu.menu(email);
				
			}else { //user is a manufacturer, we open manufacturer menu
				System.out.println("Login of doctor successful!");
				ManufacturerMenu.menu(email);
			}
		
	}

	//completado
	private static void signUpUser() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce email: ");
			String email = reader.readLine();
			System.out.println("Introduce the password");
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
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//INSPECCIONAR SI ME SIRVEN
	
	/*private static void createPet() throws Exception{
		
		System.out.println("Type the name of the pet");
		String name = reader.readLine();
		System.out.println("Type if it's cured or not");
		Boolean cured = Boolean.valueOf(reader.readLine());
		System.out.println("Type the type of animal(dog, cat, bird, hamster)");
		String typeOfAnimal = reader.readLine();
		System.out.println("Type the dob of the pet in formal yyyy/mm/dd");
		String dob_str = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dob = (Date) df.parse(dob_str);
		System.out.println("Type the coat of the pet");
		String coat = reader.readLine();
		System.out.println("Type the owner id of the pet");
		Integer owner_id = Integer.parseInt(reader.readLine());
		
		Owner o = ownermanager.searchOwnerById(owner_id);
		
		Pet p = new Pet(coat,  name,cured, typeOfAnimal, dob, o);
		petmanager.addPet(p);
		
	}*/
	
	/*private static void getAllowners() throws Exception{
		
		List<Owner> owners = null;
		
		owners = ownermanager.getListOfOwners();
		
		System.out.println(owners);
		
	}*/
	
	/*private static void printOwnersPets() throws Exception{
		
		List<Pet> pets = null;
		
		System.out.println("Type the id of the owner");
		Integer o_id = Integer.parseInt(reader.readLine());
		
		pets = petmanager.getPetsOfanOwner(o_id);
		
		System.out.println(pets);
		
	}*/
	
}
