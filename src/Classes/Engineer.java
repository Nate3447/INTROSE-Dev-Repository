package classes;

public class Engineer {
	private String name;
	private String specialties;
	private boolean isAvailable;
	private boolean isValid;
	
	public Engineer(boolean isValid) {
		this.isValid = isValid;
	}
	
	public Engineer(String name, String specialties) {
		setName(name.trim());
		setSpecialties(specialties.trim());
		setAvailability(true);
		checkValidity();
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
	
	public void checkValidity() {
		if(name.equals("") || specialties.equals("")) {
			isValid = false;
		}
		else {
			isValid = true;
		}
	}
	
	public boolean isValid() {
		return isValid;
	}
}
