package projectIfaces;

import java.util.List;

import projectPOJOs.Device;
import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public interface DeviceManager {

	public List<Device> getListOfDevices(Integer patient_id);
	public void orderDevice(Doctor doctor, Device d);
	public void assignDeviceToPatient(Device device, Patient p);
	
}
