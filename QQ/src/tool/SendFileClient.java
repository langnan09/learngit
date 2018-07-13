package tool;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
 
/*
 * 用TCP进行文件传输
 * 此文件为客户端文件
 * 连接上服务器之后，直接接受文件
 * 
 * */
public class SendFileClient {
 
         private static final String SERVERIP = "192.168.43.238";
         private static final int SERVERPORT = 12345;
         private static final int CLIENTPORT = 54321;
         
         
         /**
          * @param args
          */
         public static void main(String[] args) {
                   //TODO Auto-generated method stub
                   
                   //用来接受传输过来的字符
                   byte[]buf = new byte[100];
                   
                   Socket s = new Socket();
                   try{
                            //建立连接
                            s.connect(new InetSocketAddress(SERVERIP,SERVERPORT), CLIENTPORT);                           
                            InputStream is = s.getInputStream();
                            //接收传输来的文件名
                            int len = is.read(buf);//这里怎么会读取文件名?
                            String fileName = new String(buf,0,len);
                            System.out.println(fileName);                           
                            //接收传输来的文件
                            FileOutputStream fos = new FileOutputStream("C:\\Users\\hp\\Desktop\\liyi.txt");
                            int data;
                            while(-1!=(data= is.read()))
                            {
                               fos.write(data);
                            }
                            System.out.println("传输成功");
                            is.close();
                            s.close();
                            
                   }catch (IOException e) {
                            //TODO Auto-generated catch block
                            e.printStackTrace();
                   }
 
         }
 
}
 