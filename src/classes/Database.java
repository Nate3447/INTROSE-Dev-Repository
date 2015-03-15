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
	private ArrayList<Engineer> engineerList;
	private ArrayList<Equipment> equipmentList;
	private ArrayList<JobOrder> jobOrderList;
	private ArrayList<JobOrder> unscheduledJobList;
	private ArrayList<Engineer> availableEngineerList;
	private ArrayList<Equipment> availableEquipmentList;
	private ArrayList<JobOrder> jobsOnMonthList;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public Database() {
		setEngineerList();
		setEquipmentList();
		setJobOrderList();
		setUnscheduledJobs();
		setAvailableEngineers();
		setAvailableEquipment();
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
	
	public void addEquipment(Equipment equipment) {
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("insert into Equipment(name, idNumber, location, borrowDate, returnDate, calibrationDate, borrowerName)"
                    + "values(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,equipment.getName());
            preparedStatement.setString(2,equipment.getIDNumber());
            preparedStatement.setString(3,equipment.getLocation());
            Date Bdate = convert(equipment.getBorrowDate());
            Date Rdate = convert(equipment.getReturnDate());
            Date Cdate = convert(equipment.getCalibrationDate());
            preparedStatement.setDate(4,Bdate);
            preparedStatement.setDate(5,Rdate);
            preparedStatement.setDate(6,Cdate);
            preparedStatement.setString(7,equipment.getBorrowerName());
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	public void addJobOrder(JobOrder jobOrder) {
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("insert into Engineer(orderNo, referenceNo, jobDate, completionDate, hospital, address, contactPerson, contactNo)"
                    + "values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,jobOrder.getOrderNo());
            preparedStatement.setString(2,jobOrder.getReferenceNo());
            Date Jdate = convert(jobOrder.getJobDate());
            Date Complete = convert(jobOrder.getCompletionDate());
            preparedStatement.setDate(3, Jdate);
            preparedStatement.setDate(4, Complete);
            preparedStatement.setString(5,jobOrder.getHospital());
            preparedStatement.setString(6,jobOrder.getAddress());
            preparedStatement.setString(7,jobOrder.getContactPerson());
            preparedStatement.setString(8,jobOrder.getContactNo());
            preparedStatement.executeUpdate();
            closeAll();
        } catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	public boolean checkDuplicateEngineer(Engineer engineer) {
		try {
			ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS matches FROM engineer WHERE name = \"" 
            		+ engineer.getName() + "\"");
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
	
	public boolean checkDuplicateEquipment(Equipment equipment) {
		try {
			ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS matches FROM equipment WHERE idNumber = \"" 
            		+ equipment.getIDNumber() + "\"");
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
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS matches FROM jobOrder WHERE orderNo = \"" 
            		+ jobOrder.getOrderNo() + "\"");
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
	
	public void setEngineerList() {
		engineerList = new ArrayList<Engineer>();
		try {
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM engineer");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Engineer engineer = new Engineer();
                engineer.setName(resultSet.getString("name"));
                engineer.setSpecialties(resultSet.getString("specialties"));        
                engineerList.add(engineer);
            }
            closeAll();
        } catch(SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void setEquipmentList() {
		equipmentList = new ArrayList<Equipment>();
		try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM equipment");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Equipment equipment = new Equipment();
                equipment.setName(resultSet.getString("name"));
                equipment.setIDNumber(resultSet.getString("idNumber"));
                equipment.setLocation(resultSet.getString("location"));
                Date Bdate = convert(resultSet.getDate("borrowDate"));
                Date Rdate = convert(resultSet.getDate("returnDate"));
                Date Cdate = convert(resultSet.getDate("calibrationDate"));
                equipment.setBorrowDate(Bdate);
                equipment.setReturnDate(Rdate);
                equipment.setCalibrationDate(Cdate);
                equipment.setBorrowerName(resultSet.getString("borrowerName")); 
                equipmentList.add(equipment);
            }
            closeAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
		
	}
	
	public void setJobOrderList() {
		jobOrderList = new ArrayList<JobOrder>();
		try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM jobOrder");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                JobOrder jobOrder = new JobOrder();
                jobOrder.setOrderNo(resultSet.getString("orderNo"));
                jobOrder.setReferenceNo(resultSet.getString("referenceNo"));
                Date Jdate = convert(resultSet.getDate("jobDate"));
                Date Complete = convert(resultSet.getDate("completionDate"));
                jobOrder.setJobDate(Jdate);
                jobOrder.setCompletionDate(Complete);
                jobOrder.setHospital(resultSet.getString("hospital")); 
                jobOrder.setAddress(resultSet.getString("address"));
                jobOrder.setContactPerson(resultSet.getString("contactPerson"));
                jobOrder.setContactNumber(resultSet.getString("contactNo"));
                jobOrderList.add(jobOrder);
            }
            closeAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
		
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
		
		
	}
	
	public void setAvailableEngineers() {
		availableEngineerList = new ArrayList<Engineer>();
		
       
	}
	
	public void setAvailableEquipment() {
		availableEquipmentList = new ArrayList<Equipment>();
		
		
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
	
	public void setJobsOnMonth(int month) {
		jobsOnMonthList = new ArrayList<JobOrder>();
		
		
	}
	
	public ArrayList<JobOrder> getJobsOnMonth() {
		return jobsOnMonthList;
	}
}
