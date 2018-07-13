package client.Frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class forget extends JFrame{
	Socket socket = null;
	int port=10002;
	ObjectOutputStream oOutputStream = null;
	ObjectInputStream oInputStream =null;
	//�ı���
    JTextField textfield1_id = new JTextField(10);
    JTextField textfield2_answer = new JTextField(10);

    //�����JComboBox������
    String[] question = { "���ĸ��׵������ǣ�","����ĸ�׵������ǣ�","���ĸ��׵������ǣ�����xxxx.x.x��","����ĸ�׵������ǣ�����xxxx.x.x��","���ĳ������ǣ�","���ĸ���������ʦ�ǣ�" };
	 JComboBox jcombo1 = new JComboBox(question);
    //�����
	  JPasswordField password1 = new JPasswordField(30);
    JPasswordField password2 = new JPasswordField(30);
    //��ť
    JButton button1 = new JButton("ȷ��");
	  JButton button2 = new JButton("ȡ��");
    //��ǩ
	  JLabel label1=new JLabel("������QQ����:");
    JLabel label2=new JLabel("��ش��ܱ�����:");
    JLabel label3=new JLabel("������:");
    JLabel label4=new JLabel("ȷ������:");
	public forget(String title){
	      super(title);
	      setBounds(550, 100, 550, 450);
 	      setResizable(false);//��ֹ�û��ı䴰���С
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      JPanel panel=new JPanel();
	      panel.setLayout(null);
	      panel.setPreferredSize(new Dimension(550, 800));
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
	      add(panel);
	      setVisible(true);

	  	  //�趨λ�úʹ�С
	      textfield1_id.setBounds(150, 100, 250, 30);
	      textfield2_answer.setBounds(350, 150, 180, 30);
	      pic.add(textfield1_id);
	      pic.add(textfield2_answer);
		  jcombo1.setBounds(120, 150, 200, 30);
		  pic.add(jcombo1);
		  button1.setBounds(150, 300, 80, 35);
	      button2.setBounds(300, 300, 80, 35);
	      pic.add(button1);
	      pic.add(button2);
	      label1.setBounds(20, 100, 100, 30);
	      label2.setBounds(20, 150, 100, 30);
	      label3.setBounds(70, 200, 100, 30);
	      label4.setBounds(70, 250, 100, 30);  
	      pic.add(label1);
	      pic.add(label2);
	      pic.add(label3);
	      pic.add(label4);
	      password1.setBounds(150, 200, 250, 30);
	      password2.setBounds(150, 250, 250, 30);
	      pic.add(password1);
	      pic.add(password2);
	      button1.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button1){
					new Thread(){
						
						public void run(){
							if(textfield1_id.getText().equals("")||textfield1_id.getText()==null){
								JOptionPane.showMessageDialog(null, "     QQ���벻��Ϊ��");
							}else if(textfield2_answer.getText().equals("")||textfield2_answer.getText()==null){
								JOptionPane.showMessageDialog(null, "     �ܱ��𰸲���Ϊ��");									
							}else if((String.valueOf(password1.getPassword())) .equals("")&&(String.valueOf(password2.getPassword())) .equals("") ){
								JOptionPane.showMessageDialog(null, "      ���벻��Ϊ��");
							}else if (!((String.valueOf(password1.getPassword())) .equals(String.valueOf(password2.getPassword()))) ){
								JOptionPane.showMessageDialog(null, "      ������������벻��ͬ");
							}else{
									String id=textfield1_id.getText();	
						        	String password=String.valueOf(password1.getPassword());
						        	String answer=textfield2_answer.getText();
						        	String question=String.valueOf(jcombo1.getSelectedItem());

									try {
										socket=new Socket("localhost", port);
										oOutputStream=new ObjectOutputStream(socket.getOutputStream());//���ͻ��˵��������ɶ���������
										account a=new account(id,answer,password,question);
										oOutputStream.writeObject(a);//������д�������	Ȼ�������������
										oOutputStream.flush();
										oInputStream=new ObjectInputStream(socket.getInputStream());//���ͻ��˴ӷ�������õĵ���������ɶ����������
										account b=(account)oInputStream.readObject();//���������Ķ���ǿ������ת��Ϊaccount��Ķ���
										if(b.getid().equals("")&&b.getpassword().equals("")&&b.getanswer().equals("")) JOptionPane.showMessageDialog(null, "      �����޸�ʧ��");
										else JOptionPane.showMessageDialog(null, "      �����޸ĳɹ�");
										dispose();
										loginframe loginframe=new loginframe("Mini QQ");
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
   }
	
	public static void main(String[]args){
		forget aForget=new forget("sa");
	}

}
