package projectIfaces;

import projectPOJOs.Appointment;

public interface AppointmentManager {
	public void addAppointment(Appointment a);
	public void deleteAppointment(Integer appointment_id);

}
