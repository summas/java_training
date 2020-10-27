package Classes;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import Productdisp.alldisp;

public class SqlConnection {
	 final Properties prop = new Properties();
     InputStream inStream = null;
     Connection conn = null;	
	//SQL ConnectionŠJŽn
	public Connection Connect() {	
		
		if(conn!=null){
		
			return this.conn;
		} 

		try {
			inStream = alldisp.class.getClassLoader().getResourceAsStream("baseinfo.properties");
	        prop.load(inStream);
	        final String urlpath = prop.getProperty("urlpath");
	        final String sqldriver = prop.getProperty("sqldriver");
	        final String dbname = prop.getProperty("dbname");
	        final String dbpass = prop.getProperty("dbpass");

	 Class.forName(sqldriver).newInstance();
	  conn = DriverManager.getConnection(urlpath,dbname,dbpass);
	  
		} catch(Exception e) {
			  e.printStackTrace();
			} finally {
			  if (inStream != null){try {inStream.close();} catch (IOException e){ e.printStackTrace();}}
			}
		return conn;
	}
	//SQL Connection Close
	public void close(Connection conn){
	 if (conn != null ) {
		 try {
			 conn.close(); 
		 } catch (SQLException e) {e.printStackTrace();} 
	 	}
	}
}
