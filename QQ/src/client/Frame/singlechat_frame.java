package client.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import tool.SendFileServer;


 
public class singlechat_frame extends JFrame{
	private JScrollPane jp_up;
	private JScrollPane jp_down;
	public JTextArea text1;
	public JTextArea text2;
	private JPanel p1;
	private JPanel p2;
	private JButton send;
	private JButton clear;
	private JButton file_send;
	private JButton exit;
	private String title;
	private JComboBox<String> jcb;
	// 登录及聊天
	private Socket send_socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	// 文件传输部分
		private Socket fileSocket;
		private FileInputStream fis;  
		private DataOutputStream dos; 
		public String myid=null;
		public String hisid=null;
		//单聊发送、接受端口为10010，发送文件接收文件10011
		
	singlechat_frame(String title) {
		super();
		this.title = title;
		setTitle(this.title);
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("./images/qq14.jpg");
		JLabel background=new JLabel(img);
		this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		setLayout(new BorderLayout());
		Font font = new Font("Helvetica Bold", Font.BOLD, 16);

		// 消息显示框
		text1 = new JTextArea(15, 50);
		text1.setLineWrap(true);    // 设置自动换行
		text1.setEditable(false);   // 禁止用户编辑
		text1.setFont(font);
		text1.setOpaque(false);
		jp_up = new JScrollPane(text1);    // 给文本域增加滚动条
		jp_up.getViewport().setOpaque(false);
		jp_up.setOpaque(false);
		p1 = new JPanel();
		p1.setOpaque(false);
		p1.add(jp_up);
		add(p1, BorderLayout.CENTER);

		JPanel temp = new JPanel();
		temp.setOpaque(false);
		temp.setLayout(new BorderLayout());
		JPanel tup = new JPanel();
		tup.setOpaque(false);
		tup.setLayout(new FlowLayout());
		file_send = new JButton("文件传输");
		file_send.setFont(font);

		tup.add(file_send);

		temp.add(tup, BorderLayout.NORTH);

		// 消息输入框
//		text2 = new JTextArea(8, 50);
//		text2.setLineWrap(true);    // 设置自动换行
		text2.setFont(font);
		text2.setOpaque(false);
		jp_down = new JScrollPane(text2); // 给文本域增加滚动条
		jp_down.setOpaque(false);
		jp_down.getViewport().setOpaque(false);
		p2 = new JPanel();
		p2.setOpaque(false);
		p2.add(jp_down);
		temp.add(p2, BorderLayout.CENTER);

		// 发送，清屏按钮
		send = new JButton("发送");
		send.setFont(font);
		exit = new JButton("退出");
		exit.setFont(font);
		tup = new JPanel();
		tup.setOpaque(false);
		tup.setLayout(new FlowLayout());
		tup.add(send);
		tup.add(exit);
		temp.add(tup, BorderLayout.SOUTH);
		add(temp, BorderLayout.SOUTH);
		pack();
		// 获取屏幕大小
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// 将窗体居中
		setLocation(screen.width / 2 - this.getWidth() / 2, screen.height / 2 - this.getHeight() / 2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		// 设置观感
		String plaf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		try {
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	//	event();
		setResizable(false);
		setVisible(true);
		 send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if (arg0.getSource() == send){
					new Thread(){
						public void run(){
							try {
								text1.append("\n");
//								 Document docs = text1.getDocument();//获得文本对象
//							        try {
//							            docs.insertString(docs.getLength(), "要插入的内容", attrset);//对文本进行追加
//							        } catch (BadLocationException e) {
//							            e.printStackTrace();
//							        }
								text1.append("我说:"+text2.getText());
								send_socket=new Socket("localhost",10010);
								OutputStream oStream=send_socket.getOutputStream();
								DataOutputStream cout=new DataOutputStream(oStream);
								BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(send_socket.getOutputStream()));
								PrintWriter pw =new PrintWriter(bw,true); 
								while(true){
									String message;
									while((message=text2.getText())!=null){
										pw.println(myid+","+hisid+","+message);  
					                    pw.flush();
										text2.setText("");
									}
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}.start();
				}
				
				
				
				
			}
	        	
	        });
		 file_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==file_send){
					JFileChooser chooser = new JFileChooser();
					chooser.setDialogTitle("选择文件框"); // 标题
					chooser.showDialog(getContentPane(), "选择"); // 这是按钮的名字
					String path=chooser.getSelectedFile().getPath();
					//System.out.println(path);
					chooser.hide();
					System.out.println(path);
					
					
							try{
							//告诉对方我向你发文件了
								
								send_socket=new Socket("localhost",10010);	
								OutputStream oStream=send_socket.getOutputStream();
								DataOutputStream cout=new DataOutputStream(oStream);
								BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(send_socket.getOutputStream()));
								PrintWriter pw =new PrintWriter(bw,true); 
								pw.println(myid+","+hisid+","+"file"+","+path);  
			                    pw.flush();
								
			                    System.out.println(path);
								
			                   
                                new Thread(new SendFileServer(path)).start();
							
							
							
							
							
							}catch (IOException e1) {
	                            //TODO Auto-generated catch block
	                            e1.printStackTrace();
	                   }
							
					
				}
			}
		});
	}
       


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		singlechat_frame frame=new singlechat_frame("单人聊天");
	}

}
