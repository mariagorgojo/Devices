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
	
	private void createDevices() {
		try {
			
			Statement stmt = c.createStatement();
			
			LocalDate date = LocalDate.of(2030, 01, 01);
			Date expiration_date = Date.valueOf(date);
			
			String sql = "INSERT INTO devices (type, expiration_date)"
					+ "VALUES (" +"pacemaker"+ ", " +expiration_date+ ")";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO devices (type, expiration_date)"
					+ "VALUES (" +"prosthetic limb"+ ", " +expiration_date+ ")";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO devices (type, expiration_date)"
					+ "VALUES (" +"insulin pump"+ ", " +expiration_date+ ")";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO devices (type, expiration_date)"
					+ "VALUES (" +"coclear implant"+ ", " +expiration_date+ ")";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO devices (type, expiration_date)"
					+ "VALUES (" +"bionic eye"+ ", " +expiration_date+ ")";
			stmt.executeUpdate(sql);
			
		}catch(SQLException e) {
			e.printStackTrace();
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
					+ "expiration_date TEXT,"
					+ "manufacturer_id	INTEGER,"
					+ "FOREIGN KEY(manufacturer_id) REFERENCES manufacturers(manufacturer_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE patients ("
					+ "patient_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "email TEXT,"
					+ "name TEXT,"
					+ "surname TEXT,"
					+ "device TEXT"
					+ "birthdate TEXT,"
					+ "diagnosis TEXT,"
					+ "device_id INTEGER,"
					+ "FOREIGN KEY device_id REFERENCES devices(device_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE appointments ("
					+ "appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "date TEXT,"
					+ "description TEXT,"
					+ "doctor_id INTEGER,"
					+ "patient_id INTEGER,"
					+ "FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),"
					+ "FOREIGN KEY(patient_id) REFERENCES patients(patient_id) "
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE orders ("
					+ "doctor_id	INTEGER AUTOINCREMENT,"
					+ "device_id	INTEGER AUTOINCREMENT,"
					+ "FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
					+ "FOREIGN KEY(device_id) REFERENCES devices(device_id),"
					+ "PRIMARY KEY(doctor_id,device_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE devices_patient ("
					+ "patient_id	INTEGER AUTOINCREMENT,"
					+ "device_id	INTEGER AUTOINCREMENT,"
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

