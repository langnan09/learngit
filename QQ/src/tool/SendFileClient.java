package tool;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
 
/*
 * ��TCP�����ļ�����
 * ���ļ�Ϊ�ͻ����ļ�
 * �����Ϸ�����֮��ֱ�ӽ����ļ�
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
                   
                   //�������ܴ���������ַ�
                   byte[]buf = new byte[100];
                   
                   Socket s = new Socket();
                   try{
                            //��������
                            s.connect(new InetSocketAddress(SERVERIP,SERVERPORT), CLIENTPORT);                           
                            InputStream is = s.getInputStream();
                            //���մ��������ļ���
                            int len = is.read(buf);//������ô���ȡ�ļ���?
                            String fileName = new String(buf,0,len);
                            System.out.println(fileName);                           
                            //���մ��������ļ�
                            FileOutputStream fos = new FileOutputStream("C:\\Users\\hp\\Desktop\\liyi.txt");
                            int data;
                            while(-1!=(data= is.read()))
                            {
                               fos.write(data);
                            }
                            System.out.println("����ɹ�");
                            is.close();
                            s.close();
                            
                   }catch (IOException e) {
                            //TODO Auto-generated catch block
                            e.printStackTrace();
                   }
 
         }
 
}
 