package classes;

import java.util.ArrayList;

public class Engineer {
	private int engineerId;
	private String name;
	private String specialties;
	private int assignSchedId;
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
	//GET SET EngineerId
	public void setEngineerId(int engineerId) {
		this.engineerId = engineerId;
	}
	
	public int getEngineerId() {
		return engineerId;
	}
	//GET SET Name
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	//GET SET Specialties
	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}
	
	public String getSpecialties() {
		return specialties;
	}
	//GET SET AssignSchedId
	public void setAssignSchedId(int assignSchedId){
		this.assignSchedId = assignSchedId;
	}

	public int getAssignSchedId(){
		return assignSchedId;
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
