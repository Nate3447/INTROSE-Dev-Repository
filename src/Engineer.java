
import java.util.ArrayList;

public class Engineer {
	private String name;
	private ArrayList<String> specialties;
	
	
	// get/set methods
	
	public String getName() {
		return name;
	}
	
	
	public void setFirstName(String name) {
		this.name = name;
	}
	
	public void addSpecialty(String specialty) {
		specialties.add(specialty);
	}
	
	public void removeSpecialty(String specialty) {
		specialties.remove(specialties.indexOf(specialty));
	}
	
}
