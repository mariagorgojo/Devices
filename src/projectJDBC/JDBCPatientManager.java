package projectJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projectIfaces.PatientManager;
import projectPOJOs.Patient;

public class JDBCPatientManager implements PatientManager{
	
	private JDBCManager manager;
	private Patient p;

	public JDBCPatientManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}

	//ok
	@Override
	public void createPatient(Patient p) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO patients (email, name, surname, birthday, diagnosis)"
						+ "VALUES (?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getEmail());
			prep.setString(2, p.getName());
			prep.setString(3, p.getSurname());
			prep.setDate(4, p.getBirthday());
			prep.setString(5, p.getDiagnosis());
			prep.executeUpdate();				
			prep.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	//ok
	@Override
	public List<Patient> getListOfPatients() {
		// TODO Auto-generated method stub
		List<Patient> patients= new ArrayList<Patient>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("patient_id");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Date birthday = rs.getDate("birthday");
				String diagnosis = rs.getString("diagnosis");
				
				Patient p = new Patient (id, email, name, surname, birthday, diagnosis);
				patients.add(p);
			}
			
			rs.close();
			stmt.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return patients;
	}

	//ok
	@Override
	public Patient getPatientById(Integer id) {
		// TODO Auto-generated method stub
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients WHERE patient_id=" + id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer p_id = rs.getInt("patient_id");
			String email = rs.getString("email"); 
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Date birthday = rs.getDate("birthday");
			String diagnosis = rs.getString("diagnosis");

		    p = new Patient (p_id, email, name, surname, birthday, diagnosis);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	//ok
	@Override
	public Patient getPatientByEmail(String email) {
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients WHERE email=" + email;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("patient_id");
			String p_email = rs.getString("email"); 
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Date bd = rs.getDate("birthday");
			String diagnosis = rs.getString("diagnosis");
			//List <Device> devices = rs.getList??? e incluir los devices en el constructor?

			
		    p = new Patient (id, p_email, name, surname, bd, diagnosis);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	//ok
	//NOSE PUEDE USAR EL ATRIBUTO P????
	@Override
	public void editName(Patient p,String name) {
		try{
			p.setName(name);
			String sql = "UPDATE patients SET name=? WHERE patient_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);	
			prep.setInt(2, p.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	//ok
	@Override
	public void editSurname(Patient p,String surname) {
		p.setSurname(surname);
		try{
			p.setSurname(surname); //actualizas el objeto
			
			String sql = "UPDATE patients SET surname=? WHERE patient_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, surname);	
			prep.setInt(2, p.getId());
			prep.executeUpdate();
			
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	//ok
	@Override
	public void editDiagnosis(Patient p, String diagnosis) {
		try {
			
			p.setDiagnosis(diagnosis);
			
			String sql = "UPDATE patients SET diagnosis=? WHERE patient_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, diagnosis);
			prep.setInt(2, p.getId());
			prep.executeUpdate();
			
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
