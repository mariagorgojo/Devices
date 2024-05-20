package projectIfaces;
import java.io.File;
import projectPOJOs.Patient;

public interface XMLManager {
	public void patient2xml(Integer id);
	public Patient xml2Patient(File xml);
}
