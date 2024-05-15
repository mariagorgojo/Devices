package projectJDBC;


import java.sql.PreparedStatement;

import projectInterfaces.AppointmentManager;
import projectPOJOs.Appointment;

public class JDBCAppointmentManager implements AppointmentManager{
	
	private JDBCManager manager;

	public JDBCAppointmentManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}
	public void addAppointment(Appointment a) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO appointments (date, description, doctor, patient)"
					+ "VALUES (?,?,?,?)";
		
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1, a.getDate());
			prep.setString(2, a.getDescription());
			prep.setInt(3, a.getDoctor().getId());
			prep.setInt(4, a.getPatient().getId());
			
			prep.executeUpdate();				
					
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void deleteAppointment(Integer appointment_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE from appointments WHERE id= ?";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, appointment_id);
			prep.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	


}
