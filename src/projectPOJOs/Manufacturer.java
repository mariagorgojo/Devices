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
@XmlType(propOrder = {"name", "address","phonenumber", "Device"})

public class Manufacturer implements Serializable{

	private static final long serialVersionUID = -2756263737120347160L;
	
	private int id;
	private String name;
	private String address;
	private int phonenumber;
	private List<Device> devices;
	
	public Manufacturer(String name, String address, int phonenumber) {
		super();
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		devices = new ArrayList<>();
	}
	
	public Manufacturer(int id, String name, String address, int phonenumber) {
		super();
		this.id = id;
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
	
	public int getphonenumber() {
		return phonenumber;
	}
	
	public void setphonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<Device> getDevices() {
		return devices;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, id, name, phonenumber);
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
				&& phonenumber == other.phonenumber;
	}

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + ", address=" + address + ", phonenumber=" + phonenumber
				+ ", devices=" + devices + "]";
	}

}
