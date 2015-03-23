package classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class JobOrder {
	private int jobOrderId;
	private int jobOrderNo;
	private int referenceNo;
	private String hospital;
	private String address;
	private String contactPerson;
	private int contactNo;
	private int status;
	//status = 0 is not completed
	//status = 1 is completed
	
	private int assignSchedId;
	private boolean isValid;
	private Calendar calendar = Calendar.getInstance();
	
	public JobOrder(int jobOrderNo, int referenceNo, String hospital, String address, String contactPerson, int contactNo){
		setJobOrderNo(jobOrderNo);
		setReferenceNo(referenceNo);
		setHospital(hospital);
		setAddress(address);
		setContactPerson(contactPerson);
		setContactNo(contactNo);
	}
	
	public JobOrder() {
		
	}
	//GET SET JobOrderId
	public void setJobOrderId(int jobOrderId) {
		this.jobOrderId = jobOrderId;
	}
	
	public int getJobOrderId() {
		return jobOrderId;
	}
	//GET SET JobOrderNo
	public void setJobOrderNo(int jobOrderNo) {
		this.jobOrderNo = jobOrderNo;
	}
	
	public int getJobOrderNo() {
		return jobOrderNo;
	}
	//GET SET ReferenceNo
	public void setReferenceNo(int referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public int getReferenceNo() {
		return referenceNo;
	}
	//GET SET Hospital
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	public String getHospital() {
		return hospital;
	}
	//GET SET Address
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	//GET SET ContactPerson
	public void setContactPerson(String contactPerson){
		this.contactPerson = contactPerson;
	}

	public String getContactPerson(){
		return contactPerson;
	}
	//GET SET ContactNo
	public void setContactNo(int contactNo){
		this.contactNo = contactNo;
	}

	public int getContactNo(){
		return contactNo;
	}
	//GET SET Status
	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
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
