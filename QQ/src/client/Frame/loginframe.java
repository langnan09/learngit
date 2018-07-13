package client.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.security.auth.login.AccountException;
import javax.swing.*;
import javax.swing.text.Document;

import Server.login_server;

import java.util.function.DoubleConsumer;
import javax.swing.ImageIcon;
import java.net.*;
import information.account;
public class loginframe {
	Socket socket = null;
	int port=10000;
	ObjectOutputStream oOutputStream = null;
	ObjectInputStream oInputStream =null;
	
	
	public loginframe(String title) {
		
		
		JFrame jFrame = new JFrame(title);
		//jFrame.setLocation(1500, 900);// 设置初始位置
		jFrame.setResizable(false);//禁止用户改变窗体大小
		//jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setUndecorated(true);//隐藏边框
		
		
		// 构建面板和布局管理器
		JPanel panel = new JPanel();
		panel.setLayout(null);// 设置为空布局
		
		jFrame.setBounds(800, 300, 550, 350);
		panel.setPreferredSize(new Dimension(500, 400));
		
		//添加退出按钮
		JButton jb1=new JButton(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\tuihcu.png"));
		jb1.setContentAreaFilled(false);/***设置按钮透明*/
		jb1.setBounds(472, -1, 28, 28);
		jb1.setRolloverIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\tuichu1.png")); //鼠标放上面有效果 
		jb1.setPressedIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\tuichu2.png"));  // 鼠标关闭有效果
		jb1.setBorderPainted(false);  //去掉边框
		jb1.setFocusPainted(false); //让里面那个focus的方框不显示
		jb1.setToolTipText("关闭");  
		panel.add(jb1);
		
	
		//添加最小化按钮
		JButton jb2=new JButton(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\zuixiaohua.png"));
		jb2.setContentAreaFilled(false);/***设置按钮透明*/
		jb2.setBounds(444, 0, 28, 28);
		jb2.setRolloverIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\zuixiaohua1.png")); //鼠标放上面有效果 
		jb2.setPressedIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\zuixiaohua2.png"));  // 鼠标关闭有效果
		jb2.setBorderPainted(false);  //去掉边框
		jb2.setFocusPainted(false); //让里面那个focus的方框不显示
		jb2.setToolTipText("关闭");  
		panel.add(jb2);
		
		
		//添加设置按钮
		JButton jb3=new JButton(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\shezhi.png"));
		jb3.setContentAreaFilled(false);/***设置按钮透明*/
		jb3.setBounds(416, 0, 28, 28);
		jb3.setRolloverIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\shezhi1.png")); //鼠标放上面有效果 
		jb3.setPressedIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\shezhi2.png"));  // 鼠标关闭有效果
		jb3.setBorderPainted(false);  //去掉边框
		jb3.setFocusPainted(false); //让里面那个focus的方框不显示
		jb3.setToolTipText("关闭");  
		panel.add(jb3);
		
		// 添加图片获取图片
		JLabel jm2 = new JLabel();
		ImageIcon icon2 = new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\qq.jpg");
    	icon2.setImage(icon2.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
		jm2.setIcon(icon2);
		jm2.setOpaque(false);// 透明
		jm2.setBounds(0, 210, 100, 100);
		panel.add(jm2);
		ImageIcon img = new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ素材\\R6S.png");
    	JLabel jl_bg = new JLabel(img);
		jl_bg.setBounds(0, 0, 600, 400);
		
		
		
		//添加按钮
		JButton button2 = new JButton("登陆");
		JButton button3 = new JButton("注册账号");
		JButton button4 = new JButton("找回密码");
		button2.setBounds(130, 355, 300, 35);
		button3.setBounds(410, 220, 90, 30);
		button4.setBounds(410, 270, 90, 30);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		button3.setBorderPainted(false); //去掉边框
		button4.setBorderPainted(false);//去掉边框
		button3.setOpaque(false);
		button3.setContentAreaFilled(false);/***设置按钮透明*/
		button4.setContentAreaFilled(false);/***设置按钮透明*/
		
		
		
		//为登陆按钮设置颜色及字体
		button2.setForeground(Color.white);
		button2.setBackground(Color.blue);
		
		
		
		// 写提示语
		JLabel label1 = new JLabel("QQ:");
		JLabel label2 = new JLabel("密码:");
		panel.add(label1);
		panel.add(label2);
		label1.setBounds(110, 220, 40, 30);
		label2.setBounds(110, 270, 40, 30);
		
		
		
		
		// 构建文本框
		JTextField textfield = new JTextField(10);
		textfield.setBounds(150, 220, 250, 30);
		panel.add(textfield);
		
		
		
		
		// 构建复选框
		JCheckBox one = new JCheckBox("记住密码");
		one.setBackground(Color.BLACK);
		one.setOpaque(false);
		JCheckBox two = new JCheckBox("自动登录");
		two.setBackground(Color.BLACK);
		two.setOpaque(false);
		panel.add(one);
		panel.add(two);
		one.setBounds(150, 310, 80, 30);
		two.setBounds(275, 310, 80, 30);
		
		
		
		
		// 下拉列表框
		String[] condition = { "在线", "隐身", "离线", "忙碌", "请勿打扰" };
		JComboBox jcombo = new JComboBox(condition);
		panel.add(jcombo);
		jcombo.setBounds(410, 310, 80, 30);
		
		
		

		// 构建密码框
		JPasswordField password = new JPasswordField(30);
		password.setEchoChar('*');
		password.setBounds(150, 270, 250, 30);
		panel.add(password);
		panel.add(jl_bg);
		jFrame.add(panel);
		jFrame.pack();
		jFrame.setVisible(true);
		

         //匿名内部类事件监听器
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == button2) {
					new Thread(){//建立线程
						public void run(){
							if(textfield.getText().equals("")||textfield.getText()==null){
								JOptionPane.showMessageDialog(null, "     用户名不能为空");
							}else if((new String(password.getPassword())) .equals("") ){
								JOptionPane.showMessageDialog(null, "      密码不能为空");
							}else{
									try {
										socket=new Socket("localhost", port);
										oOutputStream=new ObjectOutputStream(socket.getOutputStream());//将客户端的输出流变成对象的输出流
										account a=new account(textfield.getText(),password.getText(),"");
										oOutputStream.writeObject(a);//将对象写入对象流	然后输出给服务器
										oInputStream=new ObjectInputStream(socket.getInputStream());//将客户端从服务器获得的的输入流变成对象的输入流
										account b=(account)oInputStream.readObject();//将输入流的对象强制类型转化为account类的对象
										if(b.getid().equals("")&&b.getpassword().equals("")) JOptionPane.showMessageDialog(null, "      登陆失败");
										else{ JOptionPane.showMessageDialog(null, "      登陆成功");
										main_interface main_interface=new main_interface(b.getid(),b.getfriends());
										jFrame.dispose();
										}
										socket.close();
									} catch(IOException e) {
								   		e.printStackTrace();
								   	} catch (ClassNotFoundException e) {
										e.printStackTrace();
										}
							}
							
						}
					}.start();
				}
			}
		});
        button3.addActionListener(new ActionListener() {//打开注册界面
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button3)
					jFrame.dispose();
				register regist= new register("注册新用户");
			}
		});
        jb1.addActionListener(new ActionListener() {//右上角关闭按钮
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==jb1){
					jFrame.dispose();
				}
			}
		});
        jb2.addActionListener(new ActionListener() {//右上角最小化按钮
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==jb2){
					jFrame.setVisible(false);
				}
			}
		});
        button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button4)
					jFrame.dispose();
				forget forget=new forget("忘记密码");
				
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stu	
		loginframe login = new loginframe("Mini QQ");



	}

}
