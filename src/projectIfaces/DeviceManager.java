package projectIfaces;

import java.util.List;

import projectPOJOs.Device;

public interface DeviceManager {

	public void orderDevice(Device d);
	public List<Device> getListOfDevices(Integer patient_id);


}
