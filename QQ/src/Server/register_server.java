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
public class register_server extends Thread {
	ServerSocket server;
	Socket socket;
    Connection connection;
    ObjectInputStream oInputStream;
    ResultSet res=null;
    ObjectOutputStream oOutputStream;
    Statement stmt;
    public register_server () throws IOException{
    	server=new ServerSocket(10001); 
    	start();
    }
    public void run(){
    	
    	 try {
    		 int num=1;
			 while(true){
				connection=dataconnection.getconnection();
				Socket s=server.accept();
  			    System.out.println("��"+num+"���û�����ע�� ");
  			    oInputStream= new ObjectInputStream(s.getInputStream());// �ӿͻ��˷����Ķ������������������InputStream��װ��ObjectInputStream�У�Ȼ��Ϳ��Դ��ж�ȡ����
			    account a=(account)oInputStream.readObject();
			    String sql ="Select * from �û�������Ϣ where id='"+a.getid()+"'";
			    stmt = connection.createStatement();
				res = stmt.executeQuery(sql);
				boolean ii=true;
				oOutputStream =new ObjectOutputStream(s.getOutputStream());
				if(res.next()) { xuliehua re2 = new xuliehua("no");oOutputStream.writeObject(re2);oOutputStream.flush();ii=false;}
				if(ii) { xuliehua re2 = new xuliehua("yes");oOutputStream.writeObject(re2);oOutputStream.flush();}
				
				
				String id="'"+a.getid()+"'";
				String username="'"+a.getusername()+"'";
				String password="'"+a.getpassword()+"'";
				String sex="'"+a.getSex()+"'";
				String answer="'"+a.getanswer()+"'";
				String question="'"+a.getquestion()+"'";
				String birthday="'"+a.getbirthday()+"'";
				
				try {
					 connection=dataconnection.getconnection();
					 String  sqls="insert into �û�������Ϣ(id,password,username,sex,birthday,problem,answer)values("+id+","+password+","+username+","+sex+","+birthday+","+question+","+answer+")";
					 Statement stmt1=(Statement) connection.createStatement();//����һ��Statement����
					 stmt1.executeUpdate(sqls);//ִ��sql���	
					 System.out.println("����ɹ�"); 
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println("���ݿ�����ʧ��");
					e.printStackTrace();
					
				}

			  connection.close();//�ر����ݿ�����
			 }} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
