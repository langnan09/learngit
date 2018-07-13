package Server;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Server extends JFrame{
	public Server(String title){
		 super(title);
	      setBounds(550, 100, 550, 450);
	      setResizable(false);//禁止用户改变窗体大小
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          JPanel  panel=new JPanel();
          panel.setLayout(null);// 设置为空布局
          panel.setPreferredSize(new Dimension(500, 400));

	      Button button1=new Button("连接服务器") ;
	      Button button2=new Button("断开服务器") ;
	      button1.setFont(new Font("华文楷体", Font.BOLD, 16));  
	      button2.setFont(new Font("华文楷体", Font.BOLD, 16));  
	      button1.setBounds(100,200,100,30);
	     button2.setBounds(350,200,100,30);
	      panel.add(button1);
	      panel.add(button2);
	      add(panel);
          setVisible(true);
      	button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getSource() == button1){
					
					try {
						
						login_server one=new login_server();
						register_server two =new register_server();
						forget_server three=new forget_server();
						main_server four=new main_server();
						OnetooneThreads five=new OnetooneThreads();
						multi_server six=new multi_server();
						//singlechat_server five=new singlechat_server();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			}
		});
	}
	
	
	

	
	
	
	
	
	
public static void main(String[]args) throws IOException{
	
			Server server =new Server("服务器");
	
	
  }
}
