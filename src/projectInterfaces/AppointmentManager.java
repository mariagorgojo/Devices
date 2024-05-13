package projectInterfaces;

import java.sql.Date;

import projectPOJOs.Doctor;
import projectPOJOs.Patient;

public interface AppointmentManager {
	
	public void addAppointment(Doctor d, Patient p, Date date);
	public void deleteAppointment(Doctor d, Patient p,Date date);

}
