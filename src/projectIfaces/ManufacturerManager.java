package projectIfaces;

import java.util.List;

import projectPOJOs.Device;
import projectPOJOs.Manufacturer;

public interface ManufacturerManager {
	
	public void createManufacturer(Manufacturer m);
	public Manufacturer getManufacturerbyEmail(String email);
	public Manufacturer getManufacturerById(Integer id);
	public List<Device> getDeviceOrder();

	public void editPhoneNumber(Manufacturer m, Integer phonenumber);
	public void editAddress(Manufacturer m, String address);
	public void editName(Manufacturer m, String name);

}
