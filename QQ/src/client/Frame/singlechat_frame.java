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
	// ��¼������
	private Socket send_socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	// �ļ����䲿��
		private Socket fileSocket;
		private FileInputStream fis;  
		private DataOutputStream dos; 
		public String myid=null;
		public String hisid=null;
		//���ķ��͡����ܶ˿�Ϊ10010�������ļ������ļ�10011
		
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

		// ��Ϣ��ʾ��
		text1 = new JTextArea(15, 50);
		text1.setLineWrap(true);    // �����Զ�����
		text1.setEditable(false);   // ��ֹ�û��༭
		text1.setFont(font);
		text1.setOpaque(false);
		jp_up = new JScrollPane(text1);    // ���ı������ӹ�����
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
		file_send = new JButton("�ļ�����");
		file_send.setFont(font);

		tup.add(file_send);

		temp.add(tup, BorderLayout.NORTH);

		// ��Ϣ�����
//		text2 = new JTextArea(8, 50);
//		text2.setLineWrap(true);    // �����Զ�����
		text2.setFont(font);
		text2.setOpaque(false);
		jp_down = new JScrollPane(text2); // ���ı������ӹ�����
		jp_down.setOpaque(false);
		jp_down.getViewport().setOpaque(false);
		p2 = new JPanel();
		p2.setOpaque(false);
		p2.add(jp_down);
		temp.add(p2, BorderLayout.CENTER);

		// ���ͣ�������ť
		send = new JButton("����");
		send.setFont(font);
		exit = new JButton("�˳�");
		exit.setFont(font);
		tup = new JPanel();
		tup.setOpaque(false);
		tup.setLayout(new FlowLayout());
		tup.add(send);
		tup.add(exit);
		temp.add(tup, BorderLayout.SOUTH);
		add(temp, BorderLayout.SOUTH);
		pack();
		// ��ȡ��Ļ��С
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// ���������
		setLocation(screen.width / 2 - this.getWidth() / 2, screen.height / 2 - this.getHeight() / 2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		// ���ù۸�
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
//								 Document docs = text1.getDocument();//����ı�����
//							        try {
//							            docs.insertString(docs.getLength(), "Ҫ���������", attrset);//���ı�����׷��
//							        } catch (BadLocationException e) {
//							            e.printStackTrace();
//							        }
								text1.append("��˵:"+text2.getText());
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
					chooser.setDialogTitle("ѡ���ļ���"); // ����
					chooser.showDialog(getContentPane(), "ѡ��"); // ���ǰ�ť������
					String path=chooser.getSelectedFile().getPath();
					//System.out.println(path);
					chooser.hide();
					System.out.println(path);
					
					
							try{
							//���߶Է������㷢�ļ���
								
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
		singlechat_frame frame=new singlechat_frame("��������");
	}

}
