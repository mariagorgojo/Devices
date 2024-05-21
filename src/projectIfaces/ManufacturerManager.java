package projectIfaces;

import projectPOJOs.Manufacturer;

public interface ManufacturerManager {

	public Manufacturer getManufacturerbyEmail(String email);
	public Manufacturer getManufacturerById(Integer id);
	public void editPhoneNumber(Manufacturer m, Integer phonenumber);
	public void editAddress(Manufacturer m, String address);
	public void editName(Manufacturer m, String name);

}
