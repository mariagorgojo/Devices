package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import projectPOJOs.Doctor;

public class PatientMenu {

	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void menu(String email) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit personal information");
				System.out.println("2. Schedule appointment");
				System.out.println("3. Cancel appointment");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					editInformation();
					break;
				case 2:
					scheduleAppointment();
					break;
				case 3:
					cancelAppointment();
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

	private static void cancelAppointment() {
		// TODO Auto-generated method stub
		
	}

	private static void scheduleAppointment() {
		// TODO Auto-generated method stub
		
	}

	private static void editInformation() {
		// TODO Auto-generated method stub
		
	}

}
