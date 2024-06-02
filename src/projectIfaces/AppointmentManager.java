package projectIfaces;

import java.util.List;

import projectJDBC.JDBCDoctorManager;
import projectJDBC.JDBCPatientManager;
import projectPOJOs.Appointment;

public interface AppointmentManager {
	public void addAppointment(Appointment a);
	public void deleteAppointment(Integer appointment_id);
	public List<Appointment> getAppointmentsOfPatient(Integer id, JDBCDoctorManager dmanager, JDBCPatientManager pmanager);
	public List<Appointment> getAppointmentsOfDoctor(Integer id, JDBCDoctorManager dmanager, JDBCPatientManager pmanager);

}
