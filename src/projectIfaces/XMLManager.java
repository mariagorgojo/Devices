package projectIfaces;
import java.io.File;

import projectPOJOs.Manufacturer;
import projectPOJOs.Patient;

public interface XMLManager {
	public void patient2xml(Integer id);
	public Patient xml2Patient(File xml);
	public void manufacturer2xml(Integer id);
	public Manufacturer xml2Manufacturer(File xml);
}
