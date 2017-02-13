package TBR.Database_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class ConnectMySQL {

	@Test
	public void connectDB() throws ClassNotFoundException, SQLException {
		
		/*Class is a class in Java
		 * forName() is a method in Class which we use to pass the driver to connect the DB
		 * */
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		/*DriverManager is another class
		 * java.sql package will help us to connect with the DB
		 * We need to mention the Connection String
		 * getConnection() is a method in DriverManager, which will establish the connection
		 * to the given DB URL
		 * Will pass the following:
		 * "jdbc:mysql://hostname:port/dbname","username","password"
		 * import Connection from java.sql*/
		Connection con = DriverManager.getConnection("jdbc:mysql://130.211.97.174:3306/TempBuddy","sai_db_dev","UrYBv57nGATwEanL");
        System.out.println("connected to MySQL DB");
        /*1. Should use con object and call an method called createStatement()
         * 2. Statement is separate interface in Java which will help us to execute any
         * DB queries 
         * 3. Statement Interface is imported from java.sql package
         * 4. Storing createStatement(); object into the Statement reference */
        
       Statement smt = con.createStatement();
	}

}
