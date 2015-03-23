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
            
            
            
            
            

