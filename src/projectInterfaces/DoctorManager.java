package projectInterfaces;
import java.util.List;

public interface DoctorManager {
	public void createDoctor( Doctor d);
	public List<Doctor> getListOfDoctors();
	public Doctor searchDoctorById(Integer id);

}