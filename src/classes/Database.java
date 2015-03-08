package classes;

import java.util.ArrayList;

public class Database {
	private ArrayList<Engineer> engineerList;
	private ArrayList<Equipment> equipmentList;
	private ArrayList<JobOrder> jobOrderList;
	
	public Database() {
		setEngineerList();
		setEquipmentList();
		setJobOrderList();
	}
	
	public void addEngineer(Engineer engineer) {
		engineerList.add(engineer);
	}
	
	public void addEquipment(Equipment equipment) {
		equipmentList.add(equipment);
	}
	
	public void addJobOrder(JobOrder jobOrder) {
		jobOrderList.add(jobOrder);
	}
	
	public void setEngineerList() {
		engineerList = new ArrayList<Engineer>();
		
		
	}
	
	public void setEquipmentList() {
		equipmentList = new ArrayList<Equipment>();
		
		
	}
	
	public void setJobOrderList() {
		jobOrderList = new ArrayList<JobOrder>();
		
		
	}
	
	public ArrayList<Engineer> getEngineerList() {
		return engineerList;
	}
	
	public ArrayList<Equipment> getEquipmentList() {
		return equipmentList;
	}
	
	public ArrayList<JobOrder> getJobOrderList() {
		return jobOrderList;
	}
	
}
