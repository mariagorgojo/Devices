package projectPOJOs;

import java.io.Serializable;

import java.sql.Date;
import java.util.Objects;


public class Device implements Serializable{
	
	//main de prueba
	/*public static void main(String[] args) {
			
		Device d = new Device(2, "bomba de insulina", null, null);
		System.out.println(d);
	}*/
	
	private static final long serialVersionUID = -4423025978446522586L;
	
	private int id;
	private Date implantationDate;
	private Date expirationDate;	
	private EnumDevices enumDevices;
	
	public Device(Date implantationDate, Date expirationDate, EnumDevices enumDevices) {
		super();
		this.implantationDate = implantationDate;
		this.expirationDate = expirationDate;
		this.enumDevices = enumDevices;
	}

	public Device(int id, Date implantationDate, Date expirationDate, EnumDevices enumDevices) {
		super();
		this.id = id;
		this.implantationDate = implantationDate;
		this.expirationDate = expirationDate;
		this.enumDevices = enumDevices;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public EnumDevices getEnumDevices() {
		return enumDevices;
	}
	public void setEnumDevices(EnumDevices enumDevices) {
		this.enumDevices = enumDevices;
	}

	public Date getImplantationDate() {
		return implantationDate;
	}
	
	public void setImplantationDate(Date implantationDate) {
		this.implantationDate = implantationDate;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expirationDate, id, implantationDate, enumDevices);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		return Objects.equals(expirationDate, other.expirationDate) && id == other.id
				&& Objects.equals(implantationDate, other.implantationDate) && Objects.equals(enumDevices, other.enumDevices);
	}
	
	@Override
	public String toString() {
		return "Device [id= " +id+ ", type= " +enumDevices+ ", implantation date= " +implantationDate+ ", expiration date= " +expirationDate+ "]";
	}
	

}
