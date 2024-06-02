package projectJDBC;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projectIfaces.AppointmentManager;
import projectPOJOs.Appointment;
import projectPOJOs.Device;

public class JDBCAppointmentManager implements AppointmentManager{
	
	private JDBCManager manager;
	private List <Appointment> appointments = new ArrayList<Appointment>();

	public JDBCAppointmentManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}
	
	@Override
	public void addAppointment(Appointment a) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO appointments (date, description, doctor_id, patient_id)"
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
	
	@Override
	public List <Appointment> getAppointmentsOfPatient(Integer id){
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT appointment_id,date,description FROM appointments WHERE patient_id=" +id;
			ResultSet rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				Integer a_id = rs.getInt("appointment_id");
				Date date = rs.getDate("date");
				String description = rs.getString("description");

				Appointment a = new Appointment(a_id, date, description);					
				appointments.add(a);
			}
			rs.close();
			stmt.close();
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return appointments;
	}
	
	@Override
	public List <Appointment> getAppointmentsOfDoctor(Integer id){
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT appointment_id,date,description FROM appointments WHERE doctor_id=" +id;
			ResultSet rs = stmt.executeQuery(sql);
				
			while(rs.next()){
				Integer a_id = rs.getInt("appointment_id");
				Date date = rs.getDate("date");
				String description = rs.getString("description");

				Appointment a = new Appointment(a_id, date, description);					
				appointments.add(a);
			}
			rs.close();
			stmt.close();
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return appointments;
	}

}
