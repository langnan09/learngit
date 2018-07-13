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
		//jFrame.setLocation(1500, 900);// ���ó�ʼλ��
		jFrame.setResizable(false);//��ֹ�û��ı䴰���С
		//jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setUndecorated(true);//���ر߿�
		
		
		// �������Ͳ��ֹ�����
		JPanel panel = new JPanel();
		panel.setLayout(null);// ����Ϊ�ղ���
		
		jFrame.setBounds(800, 300, 550, 350);
		panel.setPreferredSize(new Dimension(500, 400));
		
		//����˳���ť
		JButton jb1=new JButton(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\tuihcu.png"));
		jb1.setContentAreaFilled(false);/***���ð�ť͸��*/
		jb1.setBounds(472, -1, 28, 28);
		jb1.setRolloverIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\tuichu1.png")); //����������Ч�� 
		jb1.setPressedIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\tuichu2.png"));  // ���ر���Ч��
		jb1.setBorderPainted(false);  //ȥ���߿�
		jb1.setFocusPainted(false); //�������Ǹ�focus�ķ�����ʾ
		jb1.setToolTipText("�ر�");  
		panel.add(jb1);
		
	
		//�����С����ť
		JButton jb2=new JButton(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\zuixiaohua.png"));
		jb2.setContentAreaFilled(false);/***���ð�ť͸��*/
		jb2.setBounds(444, 0, 28, 28);
		jb2.setRolloverIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\zuixiaohua1.png")); //����������Ч�� 
		jb2.setPressedIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\zuixiaohua2.png"));  // ���ر���Ч��
		jb2.setBorderPainted(false);  //ȥ���߿�
		jb2.setFocusPainted(false); //�������Ǹ�focus�ķ�����ʾ
		jb2.setToolTipText("�ر�");  
		panel.add(jb2);
		
		
		//������ð�ť
		JButton jb3=new JButton(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\shezhi.png"));
		jb3.setContentAreaFilled(false);/***���ð�ť͸��*/
		jb3.setBounds(416, 0, 28, 28);
		jb3.setRolloverIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\shezhi1.png")); //����������Ч�� 
		jb3.setPressedIcon(new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\shezhi2.png"));  // ���ر���Ч��
		jb3.setBorderPainted(false);  //ȥ���߿�
		jb3.setFocusPainted(false); //�������Ǹ�focus�ķ�����ʾ
		jb3.setToolTipText("�ر�");  
		panel.add(jb3);
		
		// ���ͼƬ��ȡͼƬ
		JLabel jm2 = new JLabel();
		ImageIcon icon2 = new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\qq.jpg");
    	icon2.setImage(icon2.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
		jm2.setIcon(icon2);
		jm2.setOpaque(false);// ͸��
		jm2.setBounds(0, 210, 100, 100);
		panel.add(jm2);
		ImageIcon img = new ImageIcon("D:\\eclipse\\QQ\\MiNi QQ�ز�\\R6S.png");
    	JLabel jl_bg = new JLabel(img);
		jl_bg.setBounds(0, 0, 600, 400);
		
		
		
		//��Ӱ�ť
		JButton button2 = new JButton("��½");
		JButton button3 = new JButton("ע���˺�");
		JButton button4 = new JButton("�һ�����");
		button2.setBounds(130, 355, 300, 35);
		button3.setBounds(410, 220, 90, 30);
		button4.setBounds(410, 270, 90, 30);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		button3.setBorderPainted(false); //ȥ���߿�
		button4.setBorderPainted(false);//ȥ���߿�
		button3.setOpaque(false);
		button3.setContentAreaFilled(false);/***���ð�ť͸��*/
		button4.setContentAreaFilled(false);/***���ð�ť͸��*/
		
		
		
		//Ϊ��½��ť������ɫ������
		button2.setForeground(Color.white);
		button2.setBackground(Color.blue);
		
		
		
		// д��ʾ��
		JLabel label1 = new JLabel("QQ:");
		JLabel label2 = new JLabel("����:");
		panel.add(label1);
		panel.add(label2);
		label1.setBounds(110, 220, 40, 30);
		label2.setBounds(110, 270, 40, 30);
		
		
		
		
		// �����ı���
		JTextField textfield = new JTextField(10);
		textfield.setBounds(150, 220, 250, 30);
		panel.add(textfield);
		
		
		
		
		// ������ѡ��
		JCheckBox one = new JCheckBox("��ס����");
		one.setBackground(Color.BLACK);
		one.setOpaque(false);
		JCheckBox two = new JCheckBox("�Զ���¼");
		two.setBackground(Color.BLACK);
		two.setOpaque(false);
		panel.add(one);
		panel.add(two);
		one.setBounds(150, 310, 80, 30);
		two.setBounds(275, 310, 80, 30);
		
		
		
		
		// �����б��
		String[] condition = { "����", "����", "����", "æµ", "�������" };
		JComboBox jcombo = new JComboBox(condition);
		panel.add(jcombo);
		jcombo.setBounds(410, 310, 80, 30);
		
		
		

		// ���������
		JPasswordField password = new JPasswordField(30);
		password.setEchoChar('*');
		password.setBounds(150, 270, 250, 30);
		panel.add(password);
		panel.add(jl_bg);
		jFrame.add(panel);
		jFrame.pack();
		jFrame.setVisible(true);
		

         //�����ڲ����¼�������
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == button2) {
					new Thread(){//�����߳�
						public void run(){
							if(textfield.getText().equals("")||textfield.getText()==null){
								JOptionPane.showMessageDialog(null, "     �û�������Ϊ��");
							}else if((new String(password.getPassword())) .equals("") ){
								JOptionPane.showMessageDialog(null, "      ���벻��Ϊ��");
							}else{
									try {
										socket=new Socket("localhost", port);
										oOutputStream=new ObjectOutputStream(socket.getOutputStream());//���ͻ��˵��������ɶ���������
										account a=new account(textfield.getText(),password.getText(),"");
										oOutputStream.writeObject(a);//������д�������	Ȼ�������������
										oInputStream=new ObjectInputStream(socket.getInputStream());//���ͻ��˴ӷ�������õĵ���������ɶ����������
										account b=(account)oInputStream.readObject();//���������Ķ���ǿ������ת��Ϊaccount��Ķ���
										if(b.getid().equals("")&&b.getpassword().equals("")) JOptionPane.showMessageDialog(null, "      ��½ʧ��");
										else{ JOptionPane.showMessageDialog(null, "      ��½�ɹ�");
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
        button3.addActionListener(new ActionListener() {//��ע�����
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button3)
					jFrame.dispose();
				register regist= new register("ע�����û�");
			}
		});
        jb1.addActionListener(new ActionListener() {//���Ͻǹرհ�ť
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==jb1){
					jFrame.dispose();
				}
			}
		});
        jb2.addActionListener(new ActionListener() {//���Ͻ���С����ť
			
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
				forget forget=new forget("��������");
				
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stu	
		loginframe login = new loginframe("Mini QQ");



	}

}
