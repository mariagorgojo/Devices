package projectPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import projectXMLutils.SQLDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name = "Manufacturer")
@XmlType(propOrder = {"email", "name", "address","phonenumber", "devices"})

public class Manufacturer implements Serializable{

	private static final long serialVersionUID = -2756263737120347160L;
	@XmlTransient
	private int id;
	@XmlElement
	private String email;
	@XmlElement
	private String name;
	@XmlElement
	private String address;
	@XmlElement
	private int phonenumber;
	@XmlElement(name = "devices")
	@XmlElementWrapper(name = "Device")
	private List<Device> devices;
	
	public Manufacturer() {
		super();
	}
	
	public Manufacturer(String email, String name, String address, int phonenumber) {
		super();
		this.email = email;
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		devices = new ArrayList<>();
	}
	
	public Manufacturer(int id, String email, String name, String address, int phonenumber) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		devices = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setDevices(List<Device>devices){
		this.devices = devices;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getPhoneNumber() {
		return phonenumber;
	}
	
	public void setPhoneNumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<Device> getDevices() {
		return devices;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, devices, email, id, name, phonenumber);
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
		return Objects.equals(address, other.address) && Objects.equals(devices, other.devices)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& phonenumber == other.phonenumber;
	}

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", email=" + email + ", name=" + name + ", address=" + address
				+ ", phonenumber=" + phonenumber + ", devices=" + devices + "]";
	}

}
