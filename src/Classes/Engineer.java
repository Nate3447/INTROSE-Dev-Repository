package classes;

public class Engineer {
	private String name;
	private String specialties;
	private boolean available;
	
	public Engineer(String name, String specialties) {
		setName(name);
		setSpecialties(specialties);
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
	
	public String getSpecialties() {
		return specialties;
	}
	
	public void setAvailability(boolean available) {
		this.available = available;
	}
	
	public boolean getAvailability() {
		return available;
	}
	
}
