package UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import projectIfaces.XMLManager;
import projectJDBC.JDBCManufacturerManager;
import projectJDBC.JDBCDoctorManager;
import projectPOJOs.Device;
import projectPOJOs.Manufacturer;

public class ManufacturerMenu {
	
	private static Manufacturer m;
	private static List <Device> devicesNeeded = new ArrayList<Device>();
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

	public static void menu(JDBCDoctorManager dmanager, JDBCManufacturerManager mmanager, String email, XMLManager xmlmanager) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Edit personal information");
				System.out.println("2. View devices needed");
				System.out.println("3. Print me to xml");
				System.out.println("4. Load manufacturer from xml");
				System.out.println("0. Return");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					editInformation(mmanager, email);
					break;
				case 2:
					viewDevices(mmanager);
					break;
				case 3:
					printMe(xmlmanager, mmanager, email);
					break;
				case 4:
					loadManufacturer(xmlmanager);
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

	//case 4
	private static void loadManufacturer(XMLManager xmlmanager) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Introduce the path of the xml file: ");
		String path = reader.readLine();
		File file = new File(path);
		m = xmlmanager.xml2Manufacturer(file);
		
		System.out.print(m);
	}
	
	//case 3
	private static void printMe(XMLManager xmlmanager, JDBCManufacturerManager manufacturermanager, String email) {
		m = manufacturermanager.getManufacturerbyEmail(email);
		xmlmanager.manufacturer2xml(m.getId());
		xmlmanager.simpleTransform("./xmls/Manufacturer.xml", "./xmls/manufacturer-style.xslt", "./xmls/manufacturer.html");
	}
	
	//case 2
	private static void viewDevices(JDBCManufacturerManager mmanager) {
		// TODO Auto-generated method stub
		devicesNeeded = mmanager.getDeviceOrder();
		for(Device d : devicesNeeded) {
			System.out.println(d.toString());
		}
	}

	//case 1
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
					System.out.println("Back to manufacturer menu");
					break;
				}
				
			}while(choice!=0);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void editPhoneNumber(JDBCManufacturerManager mmanager, String email) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		m = mmanager.getManufacturerbyEmail(email);
		System.out.println("Enter new phone number: ");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		mmanager.editPhoneNumber(m,phonenumber);
		
	}

	private static void editAddress(JDBCManufacturerManager mmanager, String email) throws IOException {
		// TODO Auto-generated method stub
		m = mmanager.getManufacturerbyEmail(email);
		System.out.println("Enter new address: ");
		String address = reader.readLine();
		mmanager.editAddress(m,address);
	}

	private static void editName(JDBCManufacturerManager mmanager, String email) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		m = mmanager.getManufacturerbyEmail(email);
		System.out.println("Enter new name: ");
		String name = reader.readLine();
		mmanager.editName(m,name);
	}

}
