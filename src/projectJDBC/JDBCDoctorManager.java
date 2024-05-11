package projectJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projectInterfaces.DoctorManager;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

// no sabemos que métodos son necesarios para cada entity :/

public class JDBCDoctorManager implements DoctorManager {
	
	private JDBCManager manager;
	private Doctor d;
	
	public JDBCDoctorManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void createDoctor(Doctor d) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO doctors (email, name, surname, specialty)"
						+ "VALUES (?,?,?,?)";
			
			// por qué no incluye el id??
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getEmail());
			prep.setString(2, d.getName());
			prep.setString(3, d.getSurname());
			prep.setString(4, d.getSpecialty());
			
			prep.executeUpdate();				
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Doctor> getListOfDoctors() {
		// TODO Auto-generated method stub
		List<Doctor> doctors= new ArrayList<Doctor>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctors";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("id");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				
				Doctor d = new Doctor (id, email, name, surname, specialty);
				doctors.add(d);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}
		
		
		return doctors;
	}

	@Override
	public Doctor searchDoctorById(Integer id) {
		// TODO Auto-generated method stub
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctors WHERE id=" + id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer d_id = rs.getInt("id");
			String email = rs.getString("email"); 
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String specialty = rs.getString("specialty");
			
		    d = new Doctor (d_id, email, name, surname, specialty);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		
		return d;
	}
	
	@Override
	public Doctor getDoctorByEmail(String email) {
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctors WHERE email=" + email;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("id");
			String d_email = rs.getString("email"); 
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String specialty = rs.getString("specialty");
			
		    d = new Doctor (id, d_email, name, surname, specialty);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return d;
	}

	@Override
	public void orderAppointment(Patient p, Date date) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void editName(Doctor d,String name) {
		d.setName(name);
		
		
	}
	@Override
	public void editSurname(Doctor d,String surname) {
		d.setSurname(surname);
		
	}
	
	public void editSpecialty(Doctor d,String specialty) {
		d.setSpecialty(specialty);
		
	}

	
}
