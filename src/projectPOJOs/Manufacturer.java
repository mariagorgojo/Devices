package projectPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manufacturer implements Serializable{

	private static final long serialVersionUID = -2756263737120347160L;
	
	private int id;
	private String name;
	private String address;
	private int phoneNumber;
	private List<Device> devices;
	
	public Manufacturer(String name, String address, int phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		devices = new ArrayList<>();
	}
	
	public Manufacturer(int id, String name, String address, int phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		devices = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Device> getDevices() {
		return devices;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, id, name, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(address, other.address) && id == other.id && Objects.equals(name, other.name)
				&& phoneNumber == other.phoneNumber;
	}

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", devices=" + devices + "]";
	}

}
