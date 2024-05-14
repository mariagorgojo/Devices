package projectJDBC;


import projectInterfaces.AppointmentManager;

public class JDBCAppointmentManager implements AppointmentManager{
	
	private JDBCManager manager;

	public JDBCAppointmentManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}

}
