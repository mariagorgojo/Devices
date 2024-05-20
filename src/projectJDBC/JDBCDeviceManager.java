package projectJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projectIfaces.DeviceManager;
import projectPOJOs.Device;

public class JDBCDeviceManager implements DeviceManager{
	
	private JDBCManager manager;

	public JDBCDeviceManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}

	//ok
	@Override
	public void orderDevice(Device d) {
		try {
			String sql = "INSERT INTO devices (type, implantation_date, expiration_date)"
					+ "VALUES (?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getType());
			prep.setDate(2, d.getImplantationDate());
			prep.setDate(3, d.getExpirationDate());
			
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
				Date implantation_date = rs.getDate("implantation_date");
				Date expiration_date = rs.getDate("expiration_date");
					
				Device d = new Device (id, type, implantation_date, expiration_date);					
				devices.add(d);
			}
			rs.close();
			stmt.close();
				
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return devices;	
	}
		

}
