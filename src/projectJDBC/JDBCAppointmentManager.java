package projectJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;

import projectInterfaces.AppointmentManager;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public class JDBCAppointmentManager implements AppointmentManager{
	
	private JDBCManager manager;

	public JDBCAppointmentManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}
	
	// REVISAAAAAAAAR
	
	//método intermedio que rellene los atributos del appointment y cree el objeto
	
	
	public void addAppointment(Doctor d, Patient p, Date date) { //para que luego a este método se le pase el objeto appointment ya creado
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO appointments (date,"
					+ "description, email, cardnumber)"
					+ "VALUES (?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, o.getName());
			prep.setInt(2, o.getPhone());
			prep.setString(3, o.getEmail());
			prep.setInt(4, o.getCardnumber());
			
			prep.executeUpdate();				
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
