package projectJDBC;

import projectPOJOs.Device;
import projectPOJOs.Doctor;
import projectPOJOs.Manufacturer;
import projectPOJOs.Patient;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import projectIfaces.ManufacturerManager;

public class JDBCManufacturerManager implements ManufacturerManager{
	
	private JDBCManager manager;
	private Manufacturer m;

	public JDBCManufacturerManager(JDBCManager jdbcmanager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Manufacturer getManufacturerbyEmail(String email) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM manufacturers WHERE email=" + email;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("id");
			String name = rs.getString("name"); 
			String address = rs.getString("address");
			Integer phoneNumber = rs.getInt("phonenumber");
			
		    m = new Manufacturer (id, name, address, phoneNumber);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return m;
	}
	@Override
	public Manufacturer getManufacturerById(Integer id) {
		// TODO Auto-generated method stub
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM manufacturers WHERE id=" + id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer m_id = rs.getInt("id"); 
			String name = rs.getString("name");
			String address = rs.getString("adrress");
			Integer phonenumber = rs.getInt("phonenumber");

		    m = new Manufacturer (m_id, name, address, phonenumber);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public void editPhoneNumber(Manufacturer m, Integer phonenumber) {
		// TODO Auto-generated method stub
		try{
			m.setPhoneNumber(phonenumber);
			String sql = "UPDATE doctors SET phonenumber=? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, phonenumber);	
			prep.setInt(2, m.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void editAddress(Manufacturer m, String address) {
		// TODO Auto-generated method stub
		try{
			m.setAddress(address);
			String sql = "UPDATE manufacturers SET address=? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, address);	
			prep.setInt(2, m.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void editName(Manufacturer m, String name) {
		// TODO Auto-generated method stub
		try{
			m.setName(name);
			String sql = "UPDATE manufacturers SET name=? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);	
			prep.setInt(2, m.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
