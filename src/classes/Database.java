package classes;

import java.util.ArrayList;

public class Database {
	private ArrayList<Engineer> engineerList;
	private ArrayList<Equipment> equipmentList;
	private ArrayList<JobOrder> jobOrderList;
	private ArrayList<JobOrder> unscheduledJobList;
	private ArrayList<Engineer> availableEngineerList;
	private ArrayList<Equipment> availableEquipmentList;
	
	public Database() {
		setEngineerList();
		setEquipmentList();
		setJobOrderList();
		setUnscheduledJobs();
		setAvailableEngineers();
		setAvailableEquipment();
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
		// SQL Database connection
		
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
	
	public void setUnscheduledJobs() {
		unscheduledJobList = new ArrayList<JobOrder>();
		
		for(int i = 0; i < jobOrderList.size(); i++) {
			if(!(jobOrderList.get(i).getIsScheduled())) {
				unscheduledJobList.add(jobOrderList.get(i));
			}
		}
	}
	
	public void setAvailableEngineers() {
		availableEngineerList = new ArrayList<Engineer>();
		
		for(int i = 0; i < engineerList.size(); i++) {
			if(engineerList.get(i).getAvailability()) {
				availableEngineerList.add(engineerList.get(i));
			}
		}
	}
	
	public void setAvailableEquipment() {
		availableEquipmentList = new ArrayList<Equipment>();
		
		for(int i = 0; i < equipmentList.size(); i++) {
			if(equipmentList.get(i).getAvailability()) {
				availableEquipmentList.add(equipmentList.get(i));
			}
		}
	}
	
	public ArrayList<JobOrder> getUnscheduledJobs() {
		return unscheduledJobList;
	}
	
	public ArrayList<Engineer> getAvailableEngineers() {
		return availableEngineerList;
	}
	
	public ArrayList<Equipment> getAvailableEquipment() {
		return availableEquipmentList;
	}
}
