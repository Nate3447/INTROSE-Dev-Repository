package DBConnection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthew Emanuel Lu
 */
import java.sql.*;
public class DBConnectionImp extends ConnectionFactory {
   	private String _driver = "com.mysql.jdbc.Driver";
   	private String _password = "";
  
   	private String _url = "jdbc:mysql://127.0.0.1:3306/introsedb";
   	private String _username="root";


        




    public Connection getConnection(){
        try{
            Class.forName(_driver);
            Connection connection = DriverManager.getConnection(_url,_username,_password);
            return connection;
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
        
    }
    
    
}
