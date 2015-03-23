package classes;

import java.util.Calendar;
import java.util.Date;


public class Equipment {
	private int equipmentId;
	private String name;
	private int type;
	//type = 1 formula
	//type = 2 type
	private Date calibrationDate;
	private int serialNo;

	private int assignSchedId;
	private boolean isAvailable;
	private boolean isValid;
	private Calendar calendar = Calendar.getInstance();
	
	public Equipment(String name, int type, int year, int month, int day, int serialNo){
		setName(name);
		setType(type);
		setCalibrationDate(year, month, day);
		setSerialNo(serialNo);
	}
	
	public Equipment() {
		
	}
	//GET SET EquipmentId
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	public int getEquipmentId() {
		return equipmentId;
	}
	//GET SET Name
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	//GET SET Type
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	//GET SET CalibrationDate
	public void setCalibrationDate(Date calibrationDate) {
		this.calibrationDate = calibrationDate;
	}
	
	public void setCalibrationDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month - 1, date);
		calibrationDate = calendar.getTime();
	}
	
	public Date getCalibrationDate() {
		return calibrationDate;
	}
	//GET SET SerialNo
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	
	public int getSerialNo() {
		return serialNo;
	}
	//GET SET AssignSchedId
	public void setAssignSchedId(int assignSchedId){
		this.assignSchedId = assignSchedId;
	}

	public int getAssignSchedId(){
		return assignSchedId;
	}

	
	public void checkValidity() {
		isValid = true;
	}
	
	public boolean isValid() {
		return true;
	}
}
