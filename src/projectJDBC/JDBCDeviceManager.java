package projectJDBC;

import projectInterfaces.DeviceManager;

public class JDBCDeviceManager implements DeviceManager{
	private JDBCManager manager;

	public JDBCDeviceManager(JDBCManager m) {
		// TODO Auto-generated constructor stub
		this.manager = m;
	}

}
