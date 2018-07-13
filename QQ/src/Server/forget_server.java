package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import information.account;
import information.xuliehua;
public class forget_server extends Thread  {
	 ServerSocket server;
     Connection connection;
     ObjectInputStream oInputStream;
     ResultSet res=null;
     ObjectOutputStream oOutputStream;
     Statement stmt;
     public forget_server()throws IOException {
			// TODO Auto-generated constructor stub
	  server=new ServerSocket(10002); 
	   start();
		}
     public void run(){
         connection=dataconnection.getconnection();
   	  try {
   		  int num=1;
   		  while(true){
   			  Socket s=server.accept();
   			  System.out.println("第"+num+"个用户尝试修改密码");
   			  num++;
   			  oInputStream= new ObjectInputStream(s.getInputStream());//把InputStream包装到ObjectInputStream中，然后就可以从中读取对象
   			  account a=(account)oInputStream.readObject();
   			  String sql ="Select * from 用户基本信息 where id='"+a.getid()+"'and answer='"+a.getanswer()+"'and problem='"+a.getquestion()+"'";
   			  oOutputStream=new ObjectOutputStream(s.getOutputStream());//把OutputStream包装到ObjectOutputStream中，然后就可以把对象写入到该输出流中
   			  stmt=connection.createStatement();//.createStatement()创建用于执行静态SQL 语句并返回它所生成结果的对象
   			  res=stmt.executeQuery(sql);//executeQuery()方法来下达select指令以查询数据库，executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用。
   			  if(res.next())//第一次使用next()就将指针指向返回结果集的第一行。每使用一次next()，指针就指向下一行
   			  {
   				  try {
   					 connection=dataconnection.getconnection();
   					PreparedStatement stmt1;
	        		PreparedStatement psql;
                    psql = connection.prepareStatement("update 用户基本信息 set password = ? where id = ?");
                    psql.setString(2, a.getid());
                    psql.setString(1, a.getpassword());
                    psql.executeUpdate();
                    psql.close();
					 System.out.println("密码修改成功"); 
					 account user=new account(res.getString(1),res.getString(3),res.getString(7));
					  oOutputStream.writeObject(user);// 将指定的对象写入 ObjectOutputStream，obj - 要写入的对象
   				  oOutputStream.flush();//flush()这个函数是清空的意思，用于清空缓冲区的数据流
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
   			  }else{//说明指向每行的指针到了最后仍没有发现需要查找到的用户信息
   				  account user=new account("","","","");
				  oOutputStream.writeObject(user);
				  oOutputStream.flush();   			  }  
   		  }   				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
   	finally{
   		  
				try {
					if(connection!=null)connection.close();//数据库连接不为空，就关闭
					if(res!=null) res.close();//数据库查询返回的数据表最后关闭
					if(oInputStream!=null) oInputStream.close();//对象输入流关闭
					if(oOutputStream!=null)oOutputStream.close();//对象输出流关闭
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
   	  }
     }
     
     
     
     
     
     
     
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
