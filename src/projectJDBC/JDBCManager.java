package projectJDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class JDBCManager {
	
	private Connection c = null;
	
	public JDBCManager() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/DataBase.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			
			System.out.print("Database Connection opened.");
			this.createTables();
			this.createDevices();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			System.out.print("Libraries not loaded");
		}
	}
	
	private void createTables() {
		try {
			
			Statement stmt = c.createStatement();
			
			String sql = "CREATE TABLE doctors ("
					+ "doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "email TEXT,"
					+ "name TEXT,"
					+ "surname TEXT,"
					+ "specialty TEXT "
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE manufacturers ("
					+ "manufacturer_id	INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "email TEXT,"
					+ "name	TEXT,"
					+ "address	TEXT,"
					+ "phonenumber	INTEGER"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE devices ("
					+ "device_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "type TEXT,"
					+ "manufacturer_id	INTEGER,"
					+ "FOREIGN KEY(manufacturer_id) REFERENCES manufacturers(manufacturer_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE patients ("
					+ "patient_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "email TEXT,"
					+ "name TEXT,"
					+ "surname TEXT,"
					+ "devices TEXT,"
					+ "birthday DATE,"
					+ "diagnosis TEXT"
					+ ")";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE appointments ("
					+ "appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "date DATE,"
					+ "description TEXT,"
					+ "doctor_id INTEGER,"
					+ "patient_id INTEGER,"
					+ "FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),"
					+ "FOREIGN KEY(patient_id) REFERENCES patients(patient_id) "
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE orders ("
					+ "doctor_id INTEGER ,"
					+ "device_id INTEGER ,"
					+ "FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
					+ "FOREIGN KEY(device_id) REFERENCES devices(device_id),"
					+ "PRIMARY KEY(doctor_id,device_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE devices_patient ("
					+ "patient_id	INTEGER ,"
					+ "device_id	INTEGER ,"
					+ "FOREIGN KEY(patient_id) REFERENCES patients(patient_id),"
					+ "FOREIGN KEY(device_id) REFERENCES devices(device_id ),"
					+ "PRIMARY KEY(patient_id,device_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			
		}catch(SQLException e) {
			if(!e.getMessage().contains("already exists")) 
			{
				e.printStackTrace();
			}			
		}
		
	}
	
	private void createDevices() {
		try {
			
			Statement stmt = c.createStatement();
			
			String sql = "INSERT INTO devices (type)"
					+ "VALUES ('pacemaker')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO devices (type)"
					+ "VALUES ('prosthetic limb')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO devices (type)"
					+ "VALUES ('insulin pump')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO devices (type)"
					+ "VALUES ('coclear implant')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO devices (type)"
					+ "VALUES ('bionic eye')";
			stmt.executeUpdate(sql);
			
			stmt.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return c;
	}
	
	public void disconnect()
	{
		try {
			c.close();
		}
		catch(SQLException e){ 
			e.printStackTrace();
		}		
	}

}

