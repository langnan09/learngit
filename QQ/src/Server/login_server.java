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




public class login_server extends Thread {
	  ServerSocket server;
      Connection connection;
      ObjectInputStream oInputStream;
      ResultSet res=null;
      ObjectOutputStream oOutputStream;
      Statement stmt;

      public login_server()throws IOException {
						// TODO Auto-generated constructor stub
            	  server=new ServerSocket(10000); 
            	   start();
					}
      public void run(){
          connection=dataconnection.getconnection();
    	  try {
    		  int num=1;
    		  while(true){
   
    			  Socket s=server.accept();
    			  System.out.println("��"+num+"���û����Ե�½");
    			  num++;
    			  oInputStream= new ObjectInputStream(s.getInputStream());//��InputStream��װ��ObjectInputStream�У�Ȼ��Ϳ��Դ��ж�ȡ����
    			  account a=(account)oInputStream.readObject();
    			  String sql ="Select * from �û�������Ϣ where id='"+a.getid()+"'and password='"+a.getpassword()+"'and status='"+a.getstatus()+"'";
    			  oOutputStream=new ObjectOutputStream(s.getOutputStream());//��OutputStream��װ��ObjectOutputStream�У�Ȼ��Ϳ��԰Ѷ���д�뵽���������
    			  stmt=connection.createStatement();//.createStatement()��������ִ�о�̬SQL ��䲢�����������ɽ���Ķ���
    			  res=stmt.executeQuery(sql);//executeQuery()�������´�selectָ���Բ�ѯ���ݿ⣬executeQuery()����������ݿ���Ӧ�Ĳ�ѯ��������ResultSet������й�����ʹ�á�
    			  if(res.next())//��һ��ʹ��next()�ͽ�ָ��ָ�򷵻ؽ�����ĵ�һ�С�ÿʹ��һ��next()��ָ���ָ����һ��
    			  {
    				  if(a.getstatus()==1){
    					  account user=new account("","","");
    					  oOutputStream.writeObject(user);
        				  oOutputStream.flush();
    				  }else{
    					    String sqls="update �û�������Ϣ set status='"+1+"'where id='"+a.getid()+"'";
    	   					Statement stmt1=(Statement) connection.createStatement();//����һ��Statement����
    						 stmt1.executeUpdate(sqls);//ִ��sql���	
    						 System.out.println("�޸ĳɹ�"); 
    					  account user=new account(res.getString(1),res.getString(3),res.getString(7));
    					  oOutputStream.writeObject(user);// ��ָ���Ķ���д�� ObjectOutputStream��obj - Ҫд��Ķ���
        				  oOutputStream.flush();//flush()�����������յ���˼��������ջ�������������
    				  }
    			  }else{//˵��ָ��ÿ�е�ָ�뵽�������û�з�����Ҫ���ҵ����û���Ϣ
    				  account user=new account("","","");
    				  oOutputStream.writeObject(user);
    				  oOutputStream.flush();
    			  }  
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
					if(connection!=null)connection.close();//���ݿ����Ӳ�Ϊ�գ��͹ر�
					if(res!=null) res.close();//���ݿ��ѯ���ص����ݱ����ر�
					if(oInputStream!=null) oInputStream.close();//�����������ر�
					if(oOutputStream!=null)oOutputStream.close();//����������ر�
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
