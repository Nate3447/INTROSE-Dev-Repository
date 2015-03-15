package classes;

public class Engineer {
	private int id;
	private String name;
	private String specialties;
	private boolean isValid;
	
	public Engineer() {
		
	}
	
	public Engineer(boolean isValid) {
		this.isValid = isValid;
	}
	
	public Engineer(String name, String specialties) {
		setName(name.trim());
		setSpecialties(specialties.trim());
		checkValidity();
	}
	
	// get/set methods

	public void setID(int id) {
		this.id = id;
	}
	
	public int returnID() {
		return id;
	}
	
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
