package client.Frame;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.xml.bind.ValidationEvent;

//import entity.User;
import information.account;

public class main_interface  extends JFrame {
	
		Socket socket=null;
		Socket receive_socket = null;
		Socket receivefile_socket = null;
		int port=10003;
		ObjectOutputStream oOutputStream = null;
		ObjectInputStream oInputStream =null;
		private String id;//����Ĺ�
		private String friendlist;
		boolean flag=true;
		 JLayeredPane pane =new JLayeredPane(); //������ JLayeredPane���ڷֲ�
	     ImageIcon icon= new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\background.jpg");
		 JLabel pic=new JLabel(icon);
	  public main_interface(String id,String friendlist) {
		  this.id=id;
		  this.friendlist=friendlist;
    	  setBounds(550, 100, 390, 950);//���ô����С
    	  setResizable(false);//��ֹ�û��ı䴰���С
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  JPanel panel=new JPanel();
	      panel.setLayout(null);
	      panel.setPreferredSize(new Dimension(550, 800));
//	      setUndecorated(true);//���ر߿�		      
	      //���뱳��ͼƬ
	      JLayeredPane pane =new JLayeredPane(); //������ JLayeredPane���ڷֲ�
	      ImageIcon icon= new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\background.jpg");
	      panel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight()); //��仰����û�� 

	      pic.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
	      panel.add(pic);
	      pane.add(panel,JLayeredPane.DEFAULT_LAYER); 
	      pane.add(pic,JLayeredPane.MODAL_LAYER);
	      this.setLayeredPane(pane); 
	      
	      Button button1=new Button("����");
	      Button button2=new Button("Ⱥ��");
	      Button button3=new Button("С��Ϸ");
	      Button button4=new Button("�˳�");
	      button1.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	      button2.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	      button3.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	      button4.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	      button1.setBounds(0, 200, 130, 35);
	      button2.setBounds(130, 200, 130, 35);
	      button3.setBounds(260, 200, 130, 35);
	      button4.setBounds(250,850, 130, 35);
	      pic.add(button1);
	      pic.add(button2);
	      pic.add(button3);  
	      pic.add(button4); 
	      JTextField jTextField=new JTextField();
	      JLabel jLabel =new JLabel("��������");
	      jLabel.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	      jTextField.setBounds(70, 150, 330, 30);
	      jLabel.setBounds(0, 150, 100, 30);
	      pic.add(jTextField);
	      pic.add(jLabel);
	      
	  	  String []tt=friendlist.split(" ");
		
		for(int i=0;i<tt.length;i++){
			JLabel friend=new JLabel(tt[i]);
			String hisid=tt[i];
			friend.setFont(new Font("���Ŀ���", Font.BOLD, 36));
			friend.setBounds(40,250+i*50,390,50);
			pic.add(friend);
		    friend.addMouseListener(new MouseAdapter(){
		    	
					@Override
					public void mouseClicked ( MouseEvent e )
					{ 
						singlechat_frame single= new singlechat_frame("��"+hisid+"������");
					   single.hisid=hisid;
					   single.myid=id;
						new Thread(){
					    	public void run(){
					    		try {
									receive_socket=new Socket("localhost", 10010);
								} catch (UnknownHostException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					    		while(true){
								    		try {
													InputStream iStream=receive_socket.getInputStream();
													BufferedReader cin=new BufferedReader(new InputStreamReader(iStream));
			//										OutputStream oStream=receive_socket.getOutputStream();
			//										DataOutputStream cout=new DataOutputStream(oStream);���������������д��
													  String message;
													  message=cin.readLine();
													  String []me= message.split(",");
													  if(me[0].equals(single.hisid)&&me[1].equals(single.myid))
													  {
														  single.text1.append("\n");
														  single.text1.append(me[0]+"˵:"+me[2]);  
														  
														  
													  }
													//  System.out.println("111");
													  if(me[0].equals(single.hisid)&&me[1].equals(single.myid)&&me[2].equals("file"))
													  {	   	System.out.println("Ҫ��ȡ�ļ�");
														  byte[]buf = new byte[100];
														   String SERVERIP = "localhost";
													       int SERVERPORT = 12345;
													       int CLIENTPORT = 54321;
													       Socket s = new Socket();
													       try{
									                            //��������
									                            s.connect(new InetSocketAddress(SERVERIP,SERVERPORT), CLIENTPORT);
									                            
									                            InputStream is = s.getInputStream();
									                            //���մ��������ļ���
									                            int len = is.read(buf);//������ô���ȡ�ļ���?
									                            String fileName = new String(buf,0,len);
									                            System.out.println(fileName);
									                            String []fi= me[3].split("\\.");
									                            
									                            String chars = "abcdefghijklmnopqrstuvwxyz";
									                            //���մ��������ļ�
									                            String receivepath="C:\\Users\\hp\\Desktop\\�������ļ�"+"\\"+chars.charAt((int)(Math.random() * 26))+"."+fi[1];
									                            FileOutputStream fos = new FileOutputStream(receivepath);
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
													  }catch (IOException e) {
															// TODO Auto-generated catch block
															break;
														}										 
									        }
								
					    	}
					    } .start();
					    new Thread(){
					    	public void run(){
					    		try {
									receivefile_socket=new Socket("localhost", 10011);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					    		while(true){
					    			
					    		}
					    	}
					    }.start();
					

					}
				
		    });
	
    }
	 
	      setVisible(true);
	      
	      button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getSource() == button4) {
					new Thread(){//�����߳�
						public void run(){
							
									try {
										socket=new Socket("localhost", port);
										oOutputStream=new ObjectOutputStream(socket.getOutputStream());//���ͻ��˵��������ɶ���������
										account a=new account(id);
										oOutputStream.writeObject(a);//������д�������	Ȼ�������������
										oInputStream=new ObjectInputStream(socket.getInputStream());//���ͻ��˴ӷ�������õĵ���������ɶ����������
										account b=(account)oInputStream.readObject();//���������Ķ���ǿ������ת��Ϊaccount��Ķ���
										if(b.getid().equals("")&&b.getpassword().equals("")) JOptionPane.showMessageDialog(null, "      �˳�ʧ��");
										else{ JOptionPane.showMessageDialog(null, "      �ɹ��˳�");
										dispose();
										}
										socket.close();
									} catch(IOException e) {
								   		e.printStackTrace();
								   	} catch (ClassNotFoundException e) {
										e.printStackTrace();
										}
							
							
						}
					}.start();
			}
			}}); 
	      button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getSource()==button2) {
					multichat_frame multichat= new multichat_frame("��������������");
					multichat.myid=id;
					new Thread(){
				    	public void run(){
				    		try {
								receive_socket=new Socket("localhost", 10011);
							} catch (UnknownHostException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		while(true){
							    		try {
												InputStream iStream=receive_socket.getInputStream();
												BufferedReader cin=new BufferedReader(new InputStreamReader(iStream));
		//										OutputStream oStream=receive_socket.getOutputStream();
		//										DataOutputStream cout=new DataOutputStream(oStream);���������������д��
												  String message;
												  message=cin.readLine();
												  String []me= message.split(",");											 
												  multichat.text1.append("\n");
												  multichat.text1.append(me[0]+"˵:"+me[2]);  
												  }catch (IOException e) {
														// TODO Auto-generated catch block
														break;
													}
								        }							
				    	}
				    } .start();
				}
			
			}
		});
	      button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button3){
					game_frame game=new game_frame("С��Ϸ");
				}
			}
		});
	  }
public static void main(String[] args) {
	// TODO Auto-generated method stub
		main_interface main_interface2=new main_interface("","1 2 3");
		
	}

}
