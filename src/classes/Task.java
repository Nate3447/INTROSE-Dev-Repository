package classes;

public class Task {
	private int taskId;
	private int itemQuantity;
	private int serviceType;
	private int itemType;
	//type = 1 preventive maintenance
	//type = 2 maintenance
	//type = 3 repair
	//type = 4 calibration
	private int serialNo;
	private String assetCode;
	private String location;
	
	private int jobOrderId;
	
	//GET SET TaskId
	public void setTaskId(int taskId){
		this.taskId = taskId;
	}
	
	public int getTaskId(){
		return taskId;
	}
	//GET SET ItemQuantity
	public void setItemQuantity(int itemQuantity){
		this.itemQuantity = itemQuantity;
	}
	
	public int getItemQuantity(){
		return itemQuantity;
	}
	//GET SET ServiceType
	public void setServiceType( int serviceType){
		this.serviceType = serviceType;
	}
	
	public int getServiceType(){
		return serviceType;
	}
	//GET SET ItemType
	public void setItemType(int itemType){
		this.itemType = itemType;
	}
	
	public int getItemType(){
		return itemType;
	}
	//GET SET SerialNo
	public void setSerialNo(int serialNo){
		this.serialNo = serialNo;
	}
	
	public int getSerialNo(){
		return serialNo;
	}
	//GET SET Location
	public void setLocation(String location){
		this.location = location;
	}
	
	public String getLocation(){
		return location;
	}
	//GET SET AssetCode
	public void setAssetCode(String assetCode){
		this.assetCode = assetCode;
	}
	
	public String getAssetCode(){
		return assetCode;
	}
	//GET SET JobOrderId
	public void setJobOrderId(int jobOrderId){
		this.jobOrderId = jobOrderId;
	}
	
	public int getJobOrderId(){
		return jobOrderId;
	}
}
