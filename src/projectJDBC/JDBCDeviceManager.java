package projectJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projectIfaces.DeviceManager;
import projectPOJOs.Device;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

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
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void assignDeviceToPatient(Device device, Patient p) {
		// TODO Auto-generated method stub
		try {
			
			String sql = "INSERT INTO devices_patient (patient_id, device_id)"
					+ "VALUES (?, ?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, p.getId());
			prep.setInt(2, device.getId());
			
			prep.executeUpdate();
			
		}catch(SQLException e){
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
			String sql = "SELECT * FROM devices_patient AS dp JOIN devices AS d ON dp.device_id=d.device_id WHERE patient_id=" +patient_id;
			ResultSet rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				Integer id = rs.getInt("device_id");
				String type = rs.getString("type");
					
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

	public Device getDeviceByType(String type) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "SELECT * FROM devices WHERE type=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, type);
			
			ResultSet rs = prep.executeQuery();
				
			Integer id = rs.getInt("device_id");
			d = new Device (id, type);					
			
			rs.close();
			prep.close();
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		return d;
	}

	public List<Device> getDevices() {
		// TODO Auto-generated method stub
		List<Device> devices= new ArrayList<Device>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM devices";
			ResultSet rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				Integer id = rs.getInt("device_id");
				String type = rs.getString("type");
				
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
