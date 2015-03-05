package classes;

public class Engineer {
	private String name;
	private String specialties;
	private boolean available;
	
	public Engineer(String name) {
		setName(name);
	}
	
	// get/set methods

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}
	
	public String getSpecialty() {
		return specialties;
	}
	
	public void setAvailability(boolean available) {
		this.available = available;
	}
	
	public boolean getAvailability() {
		return available;
	}
	
}
