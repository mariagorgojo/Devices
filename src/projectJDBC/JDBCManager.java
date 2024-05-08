package projectJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
	
	private Connection c = null;
	
	public JDBCManager() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/DataBase.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			
			System.out.print("Database Connection opened.");
			this.createTables();
			
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
					+ "doctor_id INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "surname TEXT,"
					+ "specialty TEXT "
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE patients ("
					+ "patient_id INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "surname TEXT,"
					+ "device TEXT"
					+ "birthdate TEXT,"
					+ "diagnosis TEXT,"
					+ "doctor_id INTEGER,"
					+ "FOREIGN KEY(doctor_id) REFERENCES doctors (doctor_id) "
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE devices ("
					+ "device_id INTEGER PRIMARY KEY,"
					+ "type TEXT,"
					+ "implantation_date TEXT,"
					+ "expiration_date TEXT,"
					+ "manufacturer_id	INTEGER,"
					+ "patient_id	INTEGER,"
					+ "FOREIGN KEY(manufacturer_id) REFERENCES manufacturers(manufacturer_id),"
					+ "FOREIGN KEY(patient_id) REFERENCES patients(patient_id) "
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE appointments ("
					+ "appointment_id INTEGER PRIMARY KEY,"
					+ "date TEXT,"
					+ "description TEXT,"
					+ "manufacturer_id	INTEGER,"
					+ "patient_id	INTEGER,"
					+ "FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id),"
					+ "FOREIGN KEY(patient_id) REFERENCES patients(patient_id) "
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE manufacturers ("
					+ "manufacturer_id	INTEGER PRIMARY KEY,"
					+ "name	TEXT,"
					+ "address	TEXT,"
					+ "phonenumber	INTEGER"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE device_doctor ("
					+ "doctor_id	INTEGER,"
					+ "device_id	INTEGER,"
					+ "amount	INTEGER,"
					+ "date	TEXT,"
					+ "FOREIGN KEY(doctor_id) REFERENCES doctors,"
					+ "FOREIGN KEY(device_id) REFERENCES device,"
					+ "PRIMARY KEY(doctor_id,device_id)"
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
