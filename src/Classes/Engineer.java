package Classes;

import java.util.ArrayList;

public class Engineer {
	private String name;
	private ArrayList<String> specialties;
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
	
	public void addSpecialty(String specialty) {
		specialties.add(specialty);
	}
	
	public void removeSpecialty(String specialty) {
		specialties.remove(specialties.indexOf(specialty));
	}
	
	public void setAvailability(boolean available) {
		this.available = available;
	}
	
	public boolean getAvailability() {
		return available;
	}
	
}
