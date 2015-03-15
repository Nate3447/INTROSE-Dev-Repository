/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthew Emanuel lu
 */

//sets up connection and connect java to sql
package DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ConnectionFactory {
      
	public abstract Connection getConnection();
        
	public static ConnectionFactory getInstance() {
    	return new DBConnectionImp();
    }
        
	public static void closeResultSet(ResultSet resultSet) {
    	try{
        	resultSet.close();
        } catch(Exception exception) {}
	}
	
	public static void closeConnection(Connection connection) {
		try{
			connection.close();
    	} catch (Exception exception){}
    }
        
    public static void closeStatement(Statement statement) {
    	try{
        	statement.close();
    	} catch (Exception exception) {}
    }     
    
}
            
            
            
            
            

