package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import projectJDBC.JDBCManufacturerManager;
import projectJDBC.JDBCDoctorManager;
import projectPOJOs.Device;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public class ManufacturerMenu {
	
	private static Doctor d;
	private static Patient p;
	private static Device device;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

	public static void menu(JDBCDoctorManager dmanager, JDBCManufacturerManager mmanager, String email) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit personal information");
				System.out.println("2. View devices needed");
				System.out.println("3. Confirm devices");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					editInformation(mmanager, email);
					break;
				case 2:
					viewDevices();
					break;
				case 3:
					confirmDevices();
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

	private static void confirmDevices() {
		// TODO Auto-generated method stub
		
	}

	private static void viewDevices() {
		// TODO Auto-generated method stub
		
	}

	private static void editInformation(JDBCManufacturerManager mmanager, String email) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit name");
				System.out.println("2. Edit address");
				System.out.println("3. Edit phone number");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
				
				switch(choice) {
				case 1:
					editName(mmanager, email);
					break;
				case 2:
					editAddress(mmanager, email);
					break;
				case 3:
					editPhoneNumber(mmanager, email);
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

	private static void editPhoneNumber(JDBCManufacturerManager mmanager, String email) {
		// TODO Auto-generated method stub
		
	}

	private static void editAddress(JDBCManufacturerManager mmanager, String email) {
		// TODO Auto-generated method stub
		
	}

	private static void editName(JDBCManufacturerManager mmanager, String email) {
		// TODO Auto-generated method stub
		
	}

}
