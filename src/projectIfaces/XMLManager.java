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

/* unmarshal
 * // TODO Auto-generated method stub
		Manufacturer m = null;
		try {
			JAXBContext jaxbC = JAXBContext.newInstance(Manufacturer.class);
			Unmarshaller jaxbU = jaxbC.createUnmarshaller();
			m = (Manufacturer) jaxbU.unmarshal(xml);			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		return m;*/

/*/
 * marshal
 * / TODO Auto-generated method stub
		Patient p = null;
		try {
			JAXBContext jaxbC = JAXBContext.newInstance(Patient.class);
			Unmarshaller jaxbU = jaxbC.createUnmarshaller();
			p = (Patient) jaxbU.unmarshal(xml);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		*/
