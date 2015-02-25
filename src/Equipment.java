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
	private Calendar calendar = Calendar.getInstance();

	
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
	
	public void setBorrowDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month, date);
		borrowDate = calendar.getTime();
	}
	
	public Date getBorrowDate() {
		return borrowDate;
	}
	
	public void setReturnDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month, date);
		returnDate = calendar.getTime();
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setCalibrationDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month, date);
		calibrationDate = calendar.getTime();
	}
	
	public Date getCalibrationDate() {
		return calibrationDate;
	}
	
}
