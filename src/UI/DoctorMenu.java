package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public class DoctorMenu {
	
	private static Doctor d;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void menu(String email) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit personal information.");
				System.out.println("2. Add new patient.");
				System.out.println("3. Schedule appointment.");
				System.out.println("4. Cancel appointment.");
				System.out.println("5. Order devices.");
				System.out.println("6. View information about a patient.");
				System.out.println("0. Return.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					editInformation();
					break;
				case 2:
					addNewPatient();
					break;
				case 3:
					scheduleAppointment();
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

	private static void addNewPatient() {
		// TODO Auto-generated method stub
		// llamará al método addPatient para añadirlo a la lista de pacientes de este Doctor
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
					editName();
					break;
				case 2:
					editSurname();
					break;
				case 3:
					editSpecialty();
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
	
	//métodos para editar info doctor HACER
	private static void editName() {
		// TODO Auto-generated method stub
		
	}
	
	private static void editSurname() {
		// TODO Auto-generated method stub
		
	}
	
	private static void editSpecialty() {
		// TODO Auto-generated method stub
		
	}

	private static void viewInformationPatient() {
		// TODO Auto-generated method stub
		try {
			Patient p = null;
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
					
					p = d.searchPatientById(id);
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

	private static void scheduleAppointment() {
		// TODO Auto-generated method stub
		
	}

}
