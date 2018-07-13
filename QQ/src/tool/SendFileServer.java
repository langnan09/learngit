package tool;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
/*
 * ��TCP�����ļ�����
 * ���ļ�Ϊ�������ļ�
 * �����ܵ��ͻ��˵�����֮�������䴫���ļ���
 * ���ͻ��˽������֮����ͻ��˴����ļ�
 * */
 
public class SendFileServer extends Thread {
 
         //�����������˿�
         private static final int MONITORPORT  = 12345;
         private Socket s ;
         public String path;
         public SendFileServer(String path) {
                   super();
                   
                   this.path=path;
         }
 
         
 
 
         @Override
         public void run() {
                   //TODO Auto-generated method stub
        	 ServerSocket ss = null;
			try {
				ss = new ServerSocket(MONITORPORT);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	 Socket s = null;
			try {
				s = ss.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	 
        	 	byte[]buf = new byte[100];
                   OutputStream os=null;
                   FileInputStream fins=null;
                   try{
                            os= s.getOutputStream();
                            //�ļ�·��
                            String filePath =path;
                            //�ļ���
                            
                          //  System.out.println("���ļ���:"+filePath+"�����ȥ");
                            //�Ƚ��ļ��������ȥ
                            os.write(filePath.getBytes());
                          //  System.out.println("��ʼ�����ļ�");
                            //���ļ������ȥ 
                            //��ȡ�ļ�
                            
                            
                            ////
                            fins= new FileInputStream(filePath);
                            int data;
                            //ͨ��fins��ȡ�ļ�����ͨ��os���ļ�����
                            while(-1!=(data= fins.read()))
                            {
                                     os.write(data);
                            }
                           ////
                            
                            
                            
                            
                          //  System.out.println("�ļ��������");
                            
                            
                   }catch (IOException e) {
                            //TODO Auto-generated catch block
                            e.printStackTrace();
                   }finally
                   {
                            try{
                                     if(fins!=null)fins.close();
                                     if(os!=null)        os.close();
                                     ss.close();
                            }catch (IOException e) {
                                     e.printStackTrace();
                            }
                            
                   }
                   
         }
 
}