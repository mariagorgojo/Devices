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
	private String type;
	private Date expirationDate;	

	public Device(int id, String type, Date expirationDate) {
		super();
		this.id = id;
		this.type = type;
		this.expirationDate = expirationDate;
	}
	
	public Device(String type, Date expirationDate) {
		super();
		this.type = type;
		this.expirationDate = expirationDate;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expirationDate, id, type);
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
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", type=" + type + ", expirationDate="
				+ expirationDate + "]";
	}
	
}
