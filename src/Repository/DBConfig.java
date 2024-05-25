package Repository;

import java.sql.*;
import Helper.PathHelper;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class DBConfig {
	
	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	public DBConfig() {
		try {
			PathHelper phelp = new PathHelper();
			
//			com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
//			DriverManager.registerDriver(d);
			Class.forName(PathHelper.p.getProperty("driver"));
			conn=DriverManager.getConnection(PathHelper.p.getProperty("url"),PathHelper.p.getProperty("user"),PathHelper.p.getProperty("pass"));
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
		}
	}

}
