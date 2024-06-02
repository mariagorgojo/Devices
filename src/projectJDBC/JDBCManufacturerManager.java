package projectJDBC;

import projectPOJOs.Device;
import projectPOJOs.Manufacturer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import projectIfaces.ManufacturerManager;

public class JDBCManufacturerManager implements ManufacturerManager{
	
	private JDBCManager manager;
	private Manufacturer m;
	private List<Device> devices;

	public JDBCManufacturerManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}

	@Override
	public void createManufacturer(Manufacturer m) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO manufacturers (email, name, address, phonenumber)"
						+ "VALUES (?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, m.getEmail());
			prep.setString(2, m.getName());
			prep.setString(3, m.getAddress());
			prep.setInt(4, m.getPhoneNumber());
			prep.executeUpdate();				
			prep.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Manufacturer getManufacturerbyEmail(String email) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM manufacturers WHERE email=?";
		
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			
			ResultSet rs = prep.executeQuery();
			
			Integer id = rs.getInt("manufacturer_id");
			String name = rs.getString("name"); 
			String address = rs.getString("address");
			Integer phoneNumber = rs.getInt("phonenumber");
			
		    m = new Manufacturer (id, email, name, address, phoneNumber);
		    
		    rs.close();
		    prep.close();
		    
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	@Override
	public Manufacturer getManufacturerById(Integer id) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM manufacturers WHERE manufacturer_id=?";
		
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			
			ResultSet rs = prep.executeQuery();
			
			Integer m_id = rs.getInt("manufacturer_id");
			String email = rs.getString("email");
			String name = rs.getString("name"); 
			String address = rs.getString("address");
			Integer phoneNumber = rs.getInt("phonenumber");
			
		    m = new Manufacturer (m_id, email, name, address, phoneNumber);
		    
		    rs.close();
		    prep.close();
		    
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public void editPhoneNumber(Manufacturer m, Integer phonenumber) {
		// TODO Auto-generated method stub
		try{
			String sql = "UPDATE manufacturers SET phonenumber=? WHERE manufacturer_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, phonenumber);	
			prep.setInt(2, m.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		m.setPhoneNumber(phonenumber);

	}
	
	@Override
	public void editAddress(Manufacturer m, String address) {
		// TODO Auto-generated method stub
		try{
			String sql = "UPDATE manufacturers SET address=? WHERE manufacturer_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, address);	
			prep.setInt(2, m.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		m.setAddress(address);

	}
	
	@Override
	public void editName(Manufacturer m, String name) {
		// TODO Auto-generated method stub
		try{
			String sql = "UPDATE manufacturers SET name=? WHERE manufacturer_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);	
			prep.setInt(2, m.getId());
			prep.executeUpdate();
			System.out.println("Update finished.");
			prep.close();
			System.out.println("Database connection closed.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		m.setName(name);

	}

	//doble query
	@Override
	public List<Device> getDeviceOrder() {
		// TODO Auto-generated method stub

		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM orders";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer device_id = rs.getInt("device_id"); //you obtain the id of the type of device ordered
				
				Statement stmt2 = manager.getConnection().createStatement();
				String sql2 = "SELECT * FROM devices WHERE id=" +device_id; //you use that id to search the type of device associated with it and add it to the list you return
				ResultSet rs2 = stmt2.executeQuery(sql2);
				
				Integer id = rs2.getInt("id");
				String type = rs2.getString("type");
						
				Device d = new Device (id, type);					
				devices.add(d);
			}
			rs.close();
			stmt.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return devices;
		
	}

}
