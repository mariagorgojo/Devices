package projectIfaces;

import java.util.List;

import projectPOJOs.Appointment;

public interface AppointmentManager {
	public void addAppointment(Appointment a);
	public void deleteAppointment(Integer appointment_id);
	public List<Appointment> getAppointmentsOfPatient(Integer id);
	public List<Appointment> getAppointmentsOfDoctor(Integer id);

}
