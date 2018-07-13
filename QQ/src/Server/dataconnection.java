package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataconnection {
	static Connection connection;
	public static Connection getconnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("未找到驱动程序");
		}
		try {
			  connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users","root","liyi990922");
			  System.out.println("数据库连接成功。。。");  
		} catch (SQLException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		return connection;
	}
          
      
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dataconnection dataconnection=new dataconnection();
		dataconnection.getconnection();
	}

}
