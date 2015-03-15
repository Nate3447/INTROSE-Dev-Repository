package classes;

import java.util.Calendar;
import java.util.Date;


public class Equipment {
	private String name;
	private String idNumber;
	private String location;
	private Date borrowDate;
	private Date returnDate;
	private Date calibrationDate;
	private String borrowerName;
	private boolean isAvailable;
	private boolean isValid;
	private Calendar calendar = Calendar.getInstance();
	
	public Equipment() {
		
	}
	
	public Equipment(String name, String idNumber, String location, int borrowYear, 
			int borrowMonth, int borrowDay, int returnYear, int returnMonth, int returnDay,
			int calibrationYear, int calibrationMonth, int calibrationDay, String borrowerName) {
		setName(name.trim());
		setIDNumber(idNumber.trim());
		setLocation(location.trim());
		setBorrowDate(borrowYear, borrowMonth, borrowDay);
		setReturnDate(returnYear, returnMonth, returnDay);
		setCalibrationDate(calibrationYear, calibrationMonth, calibrationDay);
		setBorrowerName(borrowerName.trim());
		isAvailable = true;
		checkValidity();
	}
	
	// get/set methods
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setIDNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public String getIDNumber() {
		return idNumber;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	
	public String getBorrowerName() {
		return borrowerName;
	}
	
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public void setBorrowDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month, date);
		borrowDate = calendar.getTime();
	}
	
	public Date getBorrowDate() {
		return borrowDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public void setReturnDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month, date);
		returnDate = calendar.getTime();
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setCalibrationDate(Date calibrationDate) {
		this.calibrationDate = calibrationDate;
	}
	
	public void setCalibrationDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month, date);
		calibrationDate = calendar.getTime();
	}
	
	public Date getCalibrationDate() {
		return calibrationDate;
	}
	
	public void setAvailability(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public boolean getAvailability() {
		return isAvailable;
	}
	
	public void checkValidity() {
		if(name.equals("") || idNumber.equals("") || location.equals("") || borrowerName.equals("")) {
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
