package classes;

public class Engineer {
	private String name;
	private String specialties;
	private boolean isAvailable;
	
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
	
	public void setAvailability(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public boolean getAvailability() {
		return isAvailable;
	}
	
}
