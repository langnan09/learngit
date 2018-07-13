package tool;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
/*
 * 用TCP进行文件传输
 * 此文件为服务器文件
 * 当接受到客户端的请求之后，先向其传输文件名
 * 当客户端接受完毕之后，向客户端传输文件
 * */
 
public class SendFileServer extends Thread {
 
         //服务器监听端口
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
                            //文件路径
                            String filePath =path;
                            //文件名
                            
                          //  System.out.println("将文件名:"+filePath+"传输过去");
                            //先将文件名传输过去
                            os.write(filePath.getBytes());
                          //  System.out.println("开始传输文件");
                            //将文件传输过去 
                            //获取文件
                            
                            
                            ////
                            fins= new FileInputStream(filePath);
                            int data;
                            //通过fins读取文件，并通过os将文件传输
                            while(-1!=(data= fins.read()))
                            {
                                     os.write(data);
                            }
                           ////
                            
                            
                            
                            
                          //  System.out.println("文件传输结束");
                            
                            
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