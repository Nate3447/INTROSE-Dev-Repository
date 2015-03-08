package classes;

import java.util.Calendar;
import java.util.Date;


public class JobOrder {
	private int orderNo;
	private int referenceNo;
	private Date jobDate;
	private Date completionDate;
	private String hospital;
	private String address;
	private String contactPerson;
	private String contactNo;
	private Calendar calendar = Calendar.getInstance();
	
	// get/set methods
	
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	
	public void setReferenceNo(int referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public int getReferenceNo() {
		return referenceNo;
	}
	
	public void setJobDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month, date);
		jobDate = calendar.getTime();
	}
	
	public Date getJobDate() {
		return jobDate;
	}
	
	public void setCompletionDate(int year, int month, int date) {
		calendar.clear();
		calendar.set(year, month, date);
		completionDate = calendar.getTime();
	}
	
	public Date getCompletionDate() {
		return completionDate;
	}
	
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	public String getHospital() {
		return hospital;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	public String getContactPerson() {
		return contactPerson;
	}
	
	public void setContactNumber(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getContactNo() {
		return contactNo;
	}
}
