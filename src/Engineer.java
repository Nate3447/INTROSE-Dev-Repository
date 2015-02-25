
import java.util.ArrayList;

public class Engineer {
	private String firstName;
	private String lastName;
	private ArrayList<String> specialties;
	private boolean status;
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void addSpecialty(String specialty) {
		specialties.add(specialty);
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
