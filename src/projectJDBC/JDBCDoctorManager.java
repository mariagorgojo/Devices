package projectJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projectIfaces.DoctorManager;
import projectPOJOs.Doctor;


public class JDBCDoctorManager implements DoctorManager {
	
	private JDBCManager manager;
	private Doctor d;
	
	public JDBCDoctorManager (JDBCManager m) {
		this.manager = m;
	}

	//ok pero duda
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
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	//ok
	@Override
	public List<Doctor> getListOfDoctors() {
		// TODO Auto-generated method stub
		List<Doctor> doctors= new ArrayList<Doctor>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctors";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("doctor_id");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				
				Doctor d = new Doctor (id, email, name, surname, specialty);
				doctors.add(d);
			}
			
			rs.close();
			stmt.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		
		}
		return doctors;
	}

	//ok
	@Override
	public Doctor searchDoctorById(Integer id) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM doctors WHERE doctor_id=?";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
						
			ResultSet rs = prep.executeQuery();
			
			Integer d_id = rs.getInt("doctor_id");
			String email = rs.getString("email"); 
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String specialty = rs.getString("specialty");
			
		    d = new Doctor (d_id, email, name, surname, specialty);
		    
		    rs.close();
		    prep.close();
		    
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
	//ok
	@Override
	public Doctor getDoctorByEmail(String email) {
		
		try {
			String sql = "SELECT * FROM doctors WHERE email=?";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
						
			ResultSet rs = prep.executeQuery();
			
			Integer id = rs.getInt("doctor_id");
			String d_email = rs.getString("email"); 
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String specialty = rs.getString("specialty");
			
		    d = new Doctor (id, d_email, name, surname, specialty);
		    
		    rs.close();
		    prep.close();
		    
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
	//ok
	@Override
	public void editName(Doctor d,String name) {
		try{
			String sql = "UPDATE doctors SET name=? WHERE doctor_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);	
			prep.setInt(2, d.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		d.setName(name);
	}
	
	//ok
	@Override
	public void editSurname(Doctor d,String surname) {
		try{
			String sql = "UPDATE doctors SET surname=? WHERE doctor_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, surname);	
			prep.setInt(2, d.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(SQLException e) {
				e.printStackTrace();
		}
		d.setSurname(surname);

	}
	
	//ok
	@Override
	public void editSpecialty(Doctor d,String specialty) {
		try{
			String sql = "UPDATE doctors SET specialty=? WHERE doctor_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, specialty);	
			prep.setInt(2, d.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
		}catch(SQLException e) {
			e.printStackTrace();			
		}
		d.setSpecialty(specialty);

	}

	
}
