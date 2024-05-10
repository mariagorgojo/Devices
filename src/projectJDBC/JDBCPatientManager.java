package projectJDBC;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projectInterfaces.PatientManager;
import projectPOJOs.Patient;

public class JDBCPatientManager implements PatientManager{
	
	private JDBCManager manager;
	private Patient p;

	public JDBCPatientManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}

	@Override
	public void createPatient(Patient p) {
		// TODO Auto-generated method stub
		
	}

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
				Integer id = rs.getInt("id");
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
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return patients;
	}

	@Override
	public Patient getPatientById(Integer id) {
		// TODO Auto-generated method stub
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients WHERE id=" + id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer d_id = rs.getInt("id");
			String email = rs.getString("email"); 
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Date birthday = rs.getDate("birthday");
			String diagnosis = rs.getString("diagnosis");

		    p = new Patient (d_id, email, name, surname, birthday, diagnosis);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}

}
