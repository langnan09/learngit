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
	 //增加按钮
	JButton button1 = new JButton("确定");
	JButton button2 = new JButton("关闭");
	//增加文本框	
	JTextField textfield1 = new JTextField(10);
    JTextField textfield2 = new JTextField(10);
    JPasswordField password1 = new JPasswordField(30);
    JPasswordField password2 = new JPasswordField(30);
    JTextField textfield5 = new JTextField(10);
    String[] sex = { "男","女" };
    JComboBox jcombo1 = new JComboBox(sex);
    String[] question = { "您的父亲的姓名是？","您的母亲的姓名是？","您的父亲的生日是？（如xxxx.x.x）","您的母亲的生日是？（如xxxx.x.x）","您的出生地是？","您的高中语文老师是？" };
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
        	  setResizable(false);//禁止用户改变窗体大小
   		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		      JPanel panel=new JPanel();
		      panel.setLayout(null);
		      panel.setPreferredSize(new Dimension(550, 800));
//		      setUndecorated(true);//隐藏边框		      
		      //加入背景图片
		      JLayeredPane pane =new JLayeredPane(); //创建该 JLayeredPane用于分层
		      ImageIcon icon= new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\background.jpg");
		      panel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight()); //这句话好像没用 
		      JLabel pic=new JLabel(icon);
		      pic.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		      panel.add(pic);
		      pane.add(panel,JLayeredPane.DEFAULT_LAYER); 
		      pane.add(pic,JLayeredPane.MODAL_LAYER);
		      this.setLayeredPane(pane); 

		     
		    //增加按钮
		        
		        
		        button1.setBounds(300, 550, 80, 35);
		        button2.setBounds(400, 550, 80, 35);
		        pic.add(button1);
		        pic.add(button2);
		        

			//增加文本框	
		       
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
		       //增加标签
	           JLabel label1=new JLabel("QQ号码:");
	           JLabel label2=new JLabel("昵称:");
	           JLabel label3=new JLabel("登陆密码:");
	           JLabel label4=new JLabel("确认密码:");
	           JLabel label5=new JLabel("密保答案:");
	           JLabel label6=new JLabel("性别:");
	           JLabel label7=new JLabel("年");
	           JLabel label8=new JLabel("月");
	           JLabel label9=new JLabel("日");
	           JLabel label10=new JLabel("生日:");
	           JLabel label11=new JLabel("密保问题:");
	           label1.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label2.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label3.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label4.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label5.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label6.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label7.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label8.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label9.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label10.setFont(new Font("华文楷体", Font.BOLD, 16));
	           label11.setFont(new Font("华文楷体", Font.BOLD, 16));
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
		       // 下拉列表框
				
				
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
						new Thread(){//建立线程
							public void run(){
								if(textfield1.getText().equals("")||textfield1.getText()==null){
									JOptionPane.showMessageDialog(null, "     QQ号码不能为空");
								}else if(textfield2.getText().equals("")||textfield2.getText()==null){
									JOptionPane.showMessageDialog(null, "     昵称不能为空");									
								}else if((String.valueOf(password1.getPassword())) .equals("")&&(String.valueOf(password2.getPassword())) .equals("") ){
									JOptionPane.showMessageDialog(null, "      密码不能为空");
								}else if (!((String.valueOf(password1.getPassword())) .equals(String.valueOf(password2.getPassword()))) ){
									JOptionPane.showMessageDialog(null, "      两次输入的密码不相同");
								}else if(textfield5.getText().equals("")||textfield5.getText()==null){
									JOptionPane.showMessageDialog(null, "     密保答案不能为空");
								}else{
										String id=textfield1.getText();	
							        	String password=String.valueOf(password1.getPassword());
							        	String username=textfield2.getText();
							        	String sex=String.valueOf(jcombo1.getSelectedItem());
							        	String birthday=String.valueOf(jcombo3.getSelectedItem())+"年"+String.valueOf(jcombo4.getSelectedItem())+"月"+String.valueOf(jcombo5.getSelectedItem())+"日";				      				        				    					         
							            String problem=String.valueOf(jcombo2.getSelectedItem());
						        	    String answer=textfield5.getText();

										try {
											socket=new Socket("localhost", port);
											oOutputStream=new ObjectOutputStream(socket.getOutputStream());//将客户端的输出流变成对象的输出流
											account a=new account(id,password,username,sex,birthday,problem,answer);
											oOutputStream.writeObject(a);//将对象写入对象流	然后输出给服务器
											oOutputStream.flush();
											oInputStream=new ObjectInputStream(socket.getInputStream());//将客户端从服务器获得的的输入流变成对象的输入流
											xuliehua xuliehua= (xuliehua)oInputStream.readObject();
											//account b=(account)oInputStream.readObject();//将输入流的对象强制类型转化为account类的对象
											if(xuliehua.dir.equals("yes")) { JOptionPane.showMessageDialog(null, "     注册成功");dispose();}
									   		else JOptionPane.showMessageDialog(null, "     用户名已经存在");
											
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
