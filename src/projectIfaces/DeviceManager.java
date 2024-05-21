package projectIfaces;

import java.util.List;

import projectPOJOs.Device;
import projectPOJOs.Doctor;

public interface DeviceManager {

	public List<Device> getListOfDevices(Integer patient_id);
	public void orderDevice(Doctor doctor, Device d);
	
}
