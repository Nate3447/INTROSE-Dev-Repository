package classes;

import java.util.Calendar;
import java.util.Date;


public class JobOrder {
	private String orderNo;
	private String referenceNo;
	private Date jobDate;
	private Date completionDate;
	private String hospital;
	private String address;
	private String contactPerson;
	private String contactNo;
	private boolean isScheduled;
	private boolean isValid;
	private Calendar calendar = Calendar.getInstance();
	
	public JobOrder(String orderNo, String referenceNo, int jobYear, int jobMonth, int jobDay, 
			int completionYear, int completionMonth, int completionDay, String hospital, 
			String address, String contactPerson, String contactNo) {
		setOrderNo(orderNo);
		setReferenceNo(referenceNo);
		setJobDate(jobYear, jobMonth, jobDay);
		setCompletionDate(completionYear, completionMonth, completionDay);
		setHospital(hospital);
		setAddress(address);
		setContactPerson(contactPerson);
		setContactNumber(contactNo);
		setIsScheduled();
		checkValidity();
	}
	
	// get/set methods
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public String getReferenceNo() {
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

	public void setIsScheduled() {
		// ERROR CHECKING HERE
		isScheduled = false;
	}
	
	public boolean getIsScheduled() {
		return isScheduled;
	}
	
	public void checkValidity() {
		if(orderNo.equals("") || referenceNo.equals("") || hospital.equals("") || address.equals("")
				|| contactPerson.equals("") || contactNo.equals("")) {
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
