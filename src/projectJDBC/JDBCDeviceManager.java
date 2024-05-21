package projectJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projectIfaces.DeviceManager;
import projectPOJOs.Device;
import projectPOJOs.Doctor;

public class JDBCDeviceManager implements DeviceManager{
	
	private JDBCManager manager;
	private Device d;

	public JDBCDeviceManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}

	//ok
	@Override
	public void orderDevice(Doctor doctor, Device d) {
		try {
			Integer device_id = d.getId(); //you get the id of the type of device you want to order
			
			String sql = "INSERT INTO orders (doctor_id, device_id)"
					+ "VALUES (?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, doctor.getId());
			prep.setInt(2, device_id);
			
			prep.executeUpdate();	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//REVISAR
	//hace falta incluir las foreign keys?
	@Override
	public List<Device> getListOfDevices(Integer patient_id) {
		// TODO Auto-generated method stub
		List<Device> devices= new ArrayList<Device>();
			
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM devices WHERE patient_id=" +patient_id;
			ResultSet rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				Integer id = rs.getInt("id");
				String type = rs.getString("type");
				Date expiration_date = rs.getDate("expiration_date");
					
				Device d = new Device (id, type, expiration_date);					
				devices.add(d);
			}
			rs.close();
			stmt.close();
				
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return devices;	
	}

	public Device getDeviceByType(String type) {
		// TODO Auto-generated method stub
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM devices WHERE type=" +type;
			ResultSet rs = stmt.executeQuery(sql);
				
			Integer id = rs.getInt("id");
			Date expiration_date = rs.getDate("expiration_date");	
			d = new Device (id, type, expiration_date);					
			
			rs.close();
			stmt.close();
				
		}catch(Exception e){
			e.printStackTrace();
		}
		return d;
	}
		

}
