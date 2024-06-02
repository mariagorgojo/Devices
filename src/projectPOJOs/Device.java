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
	
	public Device() {
		super();
	}

	public Device(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public Device(String type) {
		super();
		this.type = type;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, type);
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
		return id == other.id && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", type=" + type + "]";
	}
	
}
