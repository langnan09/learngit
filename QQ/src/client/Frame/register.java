package client.Frame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import information.account;
import information.xuliehua;

public class register extends JFrame {
	Socket socket = null;
	int port=10001;
	ObjectOutputStream oOutputStream = null;
	ObjectInputStream oInputStream =null;
	 //���Ӱ�ť
	JButton button1 = new JButton("ȷ��");
	JButton button2 = new JButton("�ر�");
	//�����ı���	
	JTextField textfield1 = new JTextField(10);
    JTextField textfield2 = new JTextField(10);
    JPasswordField password1 = new JPasswordField(30);
    JPasswordField password2 = new JPasswordField(30);
    JTextField textfield5 = new JTextField(10);
    String[] sex = { "��","Ů" };
    JComboBox jcombo1 = new JComboBox(sex);
    String[] question = { "���ĸ��׵������ǣ�","����ĸ�׵������ǣ�","���ĸ��׵������ǣ�����xxxx.x.x��","����ĸ�׵������ǣ�����xxxx.x.x��","���ĳ������ǣ�","���ĸ���������ʦ�ǣ�" };
	JComboBox jcombo2 = new JComboBox(question);
	String[] year = { "1990","1991","1992", "1993","1994","1995","1996","1997","1998", "1999","2000","2001", "2002","2003","2004", "2005","2006","2007", "2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018" };
	JComboBox jcombo3 = new JComboBox(year);
	String[] month = { "1","2","3","4","5","6","7","8","9","10","11","12" };
	JComboBox jcombo4 = new JComboBox(month);
	String[] day = { "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31" };
	JComboBox jcombo5 = new JComboBox(day);
          public register(String title) {
			// TODO Auto-generated constructor stub
        	  super(title);
        	  setBounds(550, 100, 550, 650);
        	  setResizable(false);//��ֹ�û��ı䴰���С
   		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		      JPanel panel=new JPanel();
		      panel.setLayout(null);
		      panel.setPreferredSize(new Dimension(550, 800));
//		      setUndecorated(true);//���ر߿�		      
		      //���뱳��ͼƬ
		      JLayeredPane pane =new JLayeredPane(); //������ JLayeredPane���ڷֲ�
		      ImageIcon icon= new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\background.jpg");
		      panel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight()); //��仰����û�� 
		      JLabel pic=new JLabel(icon);
		      pic.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		      panel.add(pic);
		      pane.add(panel,JLayeredPane.DEFAULT_LAYER); 
		      pane.add(pic,JLayeredPane.MODAL_LAYER);
		      this.setLayeredPane(pane); 

		     
		    //���Ӱ�ť
		        
		        
		        button1.setBounds(300, 550, 80, 35);
		        button2.setBounds(400, 550, 80, 35);
		        pic.add(button1);
		        pic.add(button2);
		        

			//�����ı���	
		       
	   		   password1.setEchoChar('*');
	   		   
			   password2.setEchoChar('*');
		       
		       textfield1.setBounds(80, 30, 250, 30);
		       textfield2.setBounds(80, 100, 250, 30);
		       password1.setBounds(80, 170, 250, 30);
		       password2.setBounds(80, 240, 250, 30);
		       textfield5.setBounds(80, 470, 250, 30);
		       pic.add(textfield1);
		       pic.add(textfield2);
		       pic.add(password1);
		       pic.add(password2);
		       pic.add(textfield5);
		       //���ӱ�ǩ
	           JLabel label1=new JLabel("QQ����:");
	           JLabel label2=new JLabel("�ǳ�:");
	           JLabel label3=new JLabel("��½����:");
	           JLabel label4=new JLabel("ȷ������:");
	           JLabel label5=new JLabel("�ܱ���:");
	           JLabel label6=new JLabel("�Ա�:");
	           JLabel label7=new JLabel("��");
	           JLabel label8=new JLabel("��");
	           JLabel label9=new JLabel("��");
	           JLabel label10=new JLabel("����:");
	           JLabel label11=new JLabel("�ܱ�����:");
	           label1.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label2.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label3.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label4.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label5.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label6.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label7.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label8.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label9.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label10.setFont(new Font("���Ŀ���", Font.BOLD, 16));
	           label11.setFont(new Font("���Ŀ���", Font.BOLD, 16));
		       label1.setBounds(10, 30, 100, 30);
		       label2.setBounds(10, 100, 100, 30);
		       label3.setBounds(10, 170, 100, 30);
		       label4.setBounds(10, 240, 100, 30);
		       label5.setBounds(10, 470, 100, 30);
		       label6.setBounds(30, 300, 100, 30);
		       label7.setBounds(170, 350, 50, 30);
		       label8.setBounds(250, 350, 50, 30);
		       label9.setBounds(340, 350, 50, 30);
		       label10.setBounds(30, 350, 50, 30);
		       label11.setBounds(10, 410, 80, 30);
		       pic.add(label1);
		       pic.add(label2);
		       pic.add(label3);
		       pic.add(label4);
		       pic.add(label5);
		       pic.add(label6);
		       pic.add(label7);
		       pic.add(label8);
		       pic.add(label9);
		       pic.add(label10);
		       pic.add(label11);
		       // �����б��
				
				
				pic.add(jcombo1);
				jcombo1.setBounds(80, 300, 80, 30);
				pic.add(jcombo2);
				jcombo2.setBounds(80, 410, 200, 30);
				pic.add(jcombo3);
				jcombo3.setBounds(80, 350, 80, 30);
				pic.add(jcombo4);
				jcombo4.setBounds(190, 350, 50, 30);
				pic.add(jcombo5);
				jcombo5.setBounds(280, 350, 50, 30);
				
		      add(panel);
		      setVisible(true);
		      button1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource()==button1){
						new Thread(){//�����߳�
							public void run(){
								if(textfield1.getText().equals("")||textfield1.getText()==null){
									JOptionPane.showMessageDialog(null, "     QQ���벻��Ϊ��");
								}else if(textfield2.getText().equals("")||textfield2.getText()==null){
									JOptionPane.showMessageDialog(null, "     �ǳƲ���Ϊ��");									
								}else if((String.valueOf(password1.getPassword())) .equals("")&&(String.valueOf(password2.getPassword())) .equals("") ){
									JOptionPane.showMessageDialog(null, "      ���벻��Ϊ��");
								}else if (!((String.valueOf(password1.getPassword())) .equals(String.valueOf(password2.getPassword()))) ){
									JOptionPane.showMessageDialog(null, "      ������������벻��ͬ");
								}else if(textfield5.getText().equals("")||textfield5.getText()==null){
									JOptionPane.showMessageDialog(null, "     �ܱ��𰸲���Ϊ��");
								}else{
										String id=textfield1.getText();	
							        	String password=String.valueOf(password1.getPassword());
							        	String username=textfield2.getText();
							        	String sex=String.valueOf(jcombo1.getSelectedItem());
							        	String birthday=String.valueOf(jcombo3.getSelectedItem())+"��"+String.valueOf(jcombo4.getSelectedItem())+"��"+String.valueOf(jcombo5.getSelectedItem())+"��";				      				        				    					         
							            String problem=String.valueOf(jcombo2.getSelectedItem());
						        	    String answer=textfield5.getText();

										try {
											socket=new Socket("localhost", port);
											oOutputStream=new ObjectOutputStream(socket.getOutputStream());//���ͻ��˵��������ɶ���������
											account a=new account(id,password,username,sex,birthday,problem,answer);
											oOutputStream.writeObject(a);//������д�������	Ȼ�������������
											oOutputStream.flush();
											oInputStream=new ObjectInputStream(socket.getInputStream());//���ͻ��˴ӷ�������õĵ���������ɶ����������
											xuliehua xuliehua= (xuliehua)oInputStream.readObject();
											//account b=(account)oInputStream.readObject();//���������Ķ���ǿ������ת��Ϊaccount��Ķ���
											if(xuliehua.dir.equals("yes")) { JOptionPane.showMessageDialog(null, "     ע��ɹ�");dispose();}
									   		else JOptionPane.showMessageDialog(null, "     �û����Ѿ�����");
											
											socket.close();
										} catch(IOException e) {
									   		e.printStackTrace();
									   	}catch (ClassNotFoundException e) {
											e.printStackTrace();
											}
										  
								}
							}
						}.start();
					}
				}
			});
		      button2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(e.getSource()==button2){
							dispose();
						}
					}
				});
		 
		}
          
}
