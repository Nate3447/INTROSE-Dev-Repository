package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import DBConnection.ConnectionFactory;

public class Database {
	private ArrayList<Integer> jobDays;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public Database() {
		
	}
	
	private void closeAll() {
        ConnectionFactory.closeConnection(connection);
        ConnectionFactory.closeResultSet(resultSet);
        ConnectionFactory.closeStatement(preparedStatement);
    }
	
	public  java.sql.Date convert(java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }
	/*---------------------------------------------------------------------------------
	  -----------------------------ALL-ADD-STATEMENTS-----------------------------------
	 ----------------------------------------------------------------------------------*/
	
	//Add engineer
	public void addEngineer(Engineer engineer) {
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("insert into Engineer(name, specialties)"
                    + "values(?,?)");
            preparedStatement.setString(1,engineer.getName());
            preparedStatement.setString(2,engineer.getSpecialties());
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e) {
            e.printStackTrace();
        }
	}
	
	//Add equipment
	public void addEquipment(Equipment equipment) {
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("insert into Equipment(name, type, lastCalibrationDate, serialNo)"
                    + "values(?,?,?,?)");
            preparedStatement.setString(1,equipment.getName());
            preparedStatement.setInt(2,equipment.getType());
            Date Cdate = convert(equipment.getCalibrationDate());
            preparedStatement.setDate(3,Cdate);
            preparedStatement.setInt(4,equipment.getSerialNo());
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	//Add job order
	public void addJobOrder(JobOrder jobOrder) {
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("insert into JobOrder(jobOrderNo, referenceNo, hospital, address, contactPerson, contactNo)"
                    + "values(?,?,?,?,?,?)");
            preparedStatement.setInt(1,jobOrder.getJobOrderNo());
            preparedStatement.setInt(2,jobOrder.getReferenceNo());
            preparedStatement.setString(3,jobOrder.getHospital());
            preparedStatement.setString(4,jobOrder.getAddress());
            preparedStatement.setString(5,jobOrder.getContactPerson());
            preparedStatement.setInt(6,jobOrder.getContactNo());
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	/*Add task
	Parameters: job order no */
	public void addTask(Task task, int jobOrderNo){
		int jobOrderId = 0;
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("select jobOrderId from jobOrder where jobOrderNo = " + jobOrderNo);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	jobOrderId = resultSet.getInt("jobOrderId");
            }
            preparedStatement = connection.prepareStatement("insert into Task(itemQuantity, serviceType, itemType, serialNo, assetCode, location, jobOrderId)"
                    + "values(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,task.getItemQuantity());
            preparedStatement.setInt(2,task.getServiceType());
            preparedStatement.setInt(3,task.getItemType());
            preparedStatement.setInt(4,task.getSerialNo());
            preparedStatement.setString(5,task.getAssetCode());
            preparedStatement.setString(6,task.getLocation());
            preparedStatement.setInt(7,jobOrderId);
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	/*----------------------------------------------------------------------------------
	 ------------------------------ALL-ASSIGN-STATEMENTS--------------------------------
	 -----------------------------------------------------------------------------------*/
	
	/*Assign Schedule to Job Order
	Parameters: start date, end date, and job order Number*/
	public void schedToJobOrder(Date start, Date end, int jobOrderNo){
		int AssignSchedId = 0;
		String checkIfAvailable = "";
		Date startCheck;
		Date endCheck;
        Date cStart = convert(start);
        Date cEnd = convert(end);
		try {
			//checks the DB if the schedule already exists
			ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("select assignSchedId from AssignSched where startDate = "+ cStart + " AND endDate = "+ cEnd);
			resultSet = preparedStatement.executeQuery();
			checkIfAvailable = String.valueOf(resultSet.next());
			// if the schedule does not exist, it adds it to the DB
			if (checkIfAvailable.equals("false")) {
	            preparedStatement = connection.prepareStatement("INSERT INTO AssignSched(startDate, endDate)"
	                    + "values(?,?)");
	            preparedStatement.setDate(1,cStart);
	            preparedStatement.setDate(2,cEnd);
	            preparedStatement.executeUpdate();
			}
			// gets the assignSchedId of the schedule and puts it in a variable 
			preparedStatement = connection.prepareStatement("select assignSchedId from AssignSched where startDate = "+ cStart + " AND endDate = "+ cEnd);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				AssignSchedId = resultSet.getInt("assignSchedId");
			}
			// once the assignSchedId is retrieved, it adds the job order an assigned schedule
            preparedStatement = connection.prepareStatement("UPDATE jobOrder SET assignSchedId = " + AssignSchedId + "WHERE jobOrderNo = " + jobOrderNo);
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e){
        	e.printStackTrace();
        }
	}
	
	/*Assign Engineer to a Task
	Parameters: Engineer name and Task serialNo*/
	public void engineerToTask(String name, int serialNo){
		int engId = 0;
		int taskId = 0;
		int assignSchedId = 0;
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            //first get the engineerId of the engineer based on the name
            preparedStatement = connection.prepareStatement("select engineerId from engineer where name = '" + name + "'");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	//store the ID into variable engId
            	engId = resultSet.getInt("engineerId");
            }
            //then get the taskId of the task based on its serialNo
            preparedStatement = connection.prepareStatement("select taskId from task where serialNo = " + serialNo);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	//store the ID into variable taskId
            	taskId = resultSet.getInt("taskId");
            }
            //and insert the engId and taskId to engAssign of the database
            preparedStatement = connection.prepareStatement("INSERT INTO EngAssign(engineerId, taskId)"
                    + "values(?,?)");
            preparedStatement.setInt(1,engId);
            preparedStatement.setInt(2,taskId);
            preparedStatement.executeUpdate();
            
            //assigning schedule to the engineer
            //get the assignSchedId of jobOrder based on task's serialNo
            preparedStatement = connection.prepareStatement("select jo.assignSchedId from jobOrder jo"
            		+ "inner join task ta on ta.jobOrderId = jo.jobOrderId"
            		+ "where ta.serialNo = " + serialNo);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	//store the ID into variable assignSchedId
            	assignSchedId = resultSet.getInt("assignSchedId");
            }
            //insert the schedule to engSched
            preparedStatement = connection.prepareStatement("INSERT INTO EngSched(assignSchedId, engineerId)"
                    + "values(?,?)");
            preparedStatement.setInt(1,assignSchedId);
            preparedStatement.setInt(2,engId);
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e){
        	e.printStackTrace();
        }
	}
	
	/*Assign Equipment to a Task
	Parameters: */
	public void equipmentToTask(int eSerialNo, int tSerialNo){
		int equipmentId = 0;
		int taskId = 0;
		int assignSchedId = 0;
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("select equipmentId from equipment where serialNo = " + eSerialNo);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	equipmentId = resultSet.getInt("equipmentId");
            }
            preparedStatement = connection.prepareStatement("select taskId from task where serialNo = " + tSerialNo);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	taskId = resultSet.getInt("taskId");
            }
            preparedStatement = connection.prepareStatement("INSERT INTO BorrowEquip(taskId, equipmentId)"
                    + "values(?,?)");
            preparedStatement.setInt(1,taskId);
            preparedStatement.setInt(2,equipmentId);
            preparedStatement.executeUpdate();
            
            //assigning schedule to the equipment
            //get the assignSchedId of jobOrder based on task's serialNo
            preparedStatement = connection.prepareStatement("select jo.assignSchedId from jobOrder jo"
            		+ "inner join task ta on ta.jobOrderId = jo.jobOrderId"
            		+ "where ta.serialNo = " + tSerialNo);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	//store the ID into variable assignSchedId
            	assignSchedId = resultSet.getInt("assignSchedId");
            }
            //insert the schedule to EquipSched
            preparedStatement = connection.prepareStatement("INSERT INTO EquipSched(assignSchedId, equipmentId)"
                    + "values(?,?)");
            preparedStatement.setInt(1,assignSchedId);
            preparedStatement.setInt(2,equipmentId);
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e){
        	e.printStackTrace();
        }
	}
	
	/*-----------------------------------------------------------------------------------
	 -------------------------------ALL-GET-STATEMENTS-----------------------------------
	 ------------------------------------------------------------------------------------*/
	
	//Get all Engineers from DB
	public ArrayList<Engineer> getEngineerList() {
		ArrayList<Engineer> engineerList = new ArrayList<Engineer>();
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM engineer");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Engineer engineer = new Engineer();
                engineer.setEngineerId(resultSet.getInt("engineerId"));
                engineer.setName(resultSet.getString("name"));
                engineer.setSpecialties(resultSet.getString("specialties"));        
                engineerList.add(engineer);
            }
            closeAll();
        } catch(SQLException e) {
            e.printStackTrace();
        }
		return engineerList;
	}
	
	//Get all Equipments from DB
	public ArrayList<Equipment> getEquipmentList() {
		ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
		try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM equipment");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Equipment equipment = new Equipment();
                equipment.setEquipmentId(resultSet.getInt("equipmentId"));
                equipment.setName(resultSet.getString("name"));
                equipment.setType(resultSet.getInt("type"));
                Date Cdate = convert(resultSet.getDate("lastCalibrationDate"));
                equipment.setCalibrationDate(Cdate);
                equipment.setSerialNo(resultSet.getInt("serialNo")); 
                equipmentList.add(equipment);
            }
            closeAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
		return equipmentList;
	}
	
	//Get all JobOrders from DB
	public ArrayList<JobOrder> getJobOrderList() {
		ArrayList<JobOrder> jobOrderList = new ArrayList<JobOrder>();
		try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM jobOrder");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                JobOrder jobOrder = new JobOrder();
                jobOrder.setJobOrderId(resultSet.getInt("jobOrderId"));
                jobOrder.setJobOrderNo(resultSet.getInt("jobOrderNo"));
                jobOrder.setReferenceNo(resultSet.getInt("referenceNo"));
                jobOrder.setHospital(resultSet.getString("hospital"));
                jobOrder.setAddress(resultSet.getString("address"));
                jobOrder.setContactPerson(resultSet.getString("contactPerson"));
                jobOrder.setContactNo(resultSet.getInt("contactNo"));
                jobOrder.setStatus(resultSet.getInt("status"));
                jobOrderList.add(jobOrder);
            }
            closeAll();
        } catch(SQLException e){
            e.printStackTrace();
        }
		return jobOrderList;
	}
	
	//Get all task from a specific jobOrder from the DB
	public ArrayList<Task> getTask(int jobOrderNo) {
		ArrayList<Task> taskList = new ArrayList<Task>();
		int jobOrderId = 0;
		try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("select jobOrderId from jobOrder where jobOrderNo = " + jobOrderNo);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	jobOrderId = resultSet.getInt("jobOrderId");
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE jobOrderId = " + jobOrderId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Task task = new Task();
                task.setItemQuantity(resultSet.getInt("itemQuantity"));
                task.setServiceType(resultSet.getInt("serviceType"));
                task.setItemType(resultSet.getInt("itemType"));
                task.setSerialNo(resultSet.getInt("serialNo"));
                task.setAssetCode(resultSet.getString("assetCode"));
                task.setLocation(resultSet.getString("location"));
                task.setJobOrderId(resultSet.getInt("jobOrderId"));
                taskList.add(task);
            }
            closeAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
		return taskList;
		
	}
	
	//Get JobOrders which are not yet assigned
	public ArrayList<JobOrder> getUnscheduledJobs() {
		ArrayList<JobOrder> unscheduledJobList = new ArrayList<JobOrder>();
		try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM jobOrder WHERE assignSchedId is NULL");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                JobOrder jobOrder = new JobOrder();
                jobOrder.setJobOrderId(resultSet.getInt("jobOrderId"));
                jobOrder.setJobOrderNo(resultSet.getInt("jobOrderNo"));
                jobOrder.setReferenceNo(resultSet.getInt("referenceNo"));
                jobOrder.setHospital(resultSet.getString("hospital"));
                jobOrder.setAddress(resultSet.getString("address"));
                jobOrder.setContactPerson(resultSet.getString("contactPerson"));
                jobOrder.setContactNo(resultSet.getInt("contactNo"));
                jobOrder.setStatus(resultSet.getInt("status"));
                unscheduledJobList.add(jobOrder);
            }
            closeAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
		return unscheduledJobList;
	}
	
	//Get available engineers depending on the date given
	public ArrayList<Engineer> getAvailableEngineers(Date date) {
		ArrayList<Engineer> availableEngineerList = new ArrayList<Engineer>();
		return availableEngineerList;
	}
	
	//Get available equipment depending on the date given
	public ArrayList<Equipment> getAvailableEquipment() {
		ArrayList<Equipment> availableEquipmentList = new ArrayList<Equipment>();
		return availableEquipmentList;
	}
	
	/*---------------------------------------------------------------------------------
	 ----------------------------ALL-CHECK_DUPLICATES---------------------------------- 
	 ----------------------------------------------------------------------------------*/
	
	public boolean checkDuplicateEngineer(Engineer engineer) {
		String name = engineer.getName();
		int counter = 0;
		try {
			ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("select name from engineer");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				if(name.equals(resultSet.getString("name"))){
					counter += 1;
				}
			}
		} catch(SQLException e) {
            e.printStackTrace();
			return true;
        }
		if(counter > 0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean checkDuplicateEquipment(Equipment equipment) {
		try {
			ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS matches FROM equipment WHERE equipmentId = \"" 
            		+ equipment.getEquipmentId() + "\"");
			resultSet = preparedStatement.executeQuery();
            if(resultSet.getInt("matches") > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
            e.printStackTrace();
			return true;
        }
	}
	
	public boolean checkDuplicateJobOrder(JobOrder jobOrder) {
		try {
			ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS matches FROM jobOrder WHERE referenceNo = \"" 
            		+ jobOrder.getReferenceNo() + "\"");
			resultSet = preparedStatement.executeQuery();
            if(resultSet.getInt("matches") > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
            e.printStackTrace();
			return true;
        }
	}
	
	public ArrayList<Integer> getJobDays(int month, int year) {
		jobDays = new ArrayList<Integer>();
		try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT DISTINCT day(jobDate) AS day FROM jobOrder WHERE month(jobDate) = " + month + " AND year(jobDate) = " + year);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
            	jobDays.add(Integer.parseInt(resultSet.getString("day")));
            }
            closeAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
		return jobDays;
	}
	
}
