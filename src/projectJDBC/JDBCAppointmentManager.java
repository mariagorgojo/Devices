package projectJDBC;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import projectIfaces.AppointmentManager;
import projectPOJOs.Appointment;

public class JDBCAppointmentManager implements AppointmentManager{
	
	private JDBCManager manager;

	public JDBCAppointmentManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}
	
	@Override
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
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteAppointment(Integer appointment_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE from appointments WHERE appointment_id= ?";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, appointment_id);
			prep.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	


}
