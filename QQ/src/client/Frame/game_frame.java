package client.Frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalInternalFrameTitlePane;

import MiniDrawPad.MiniDrawPad;
import game.mainFrame;

public class game_frame extends JFrame {
     public  game_frame(String title){
    	 super(title);
   	     setBounds(550, 100, 450, 350);
   	     setResizable(false);//��ֹ�û��ı䴰���С
//		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ע�͵������ص��˽�����ѽ��治��ر�
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
	      JButton button1=new JButton("������");
	      JButton button2=new JButton("��ͼ��");
	      button1.setBounds(75, 130,100, 30);
	      button2.setBounds(250, 130,100, 30);
	      pic.add(button1);
	      pic.add(button2);
	      add(panel);
	      setVisible(true);
	      button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button1){
					
					new mainFrame();
				}
			}
		});
	      button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MiniDrawPad newPad = new MiniDrawPad();
				// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				 newPad.addWindowListener(
			                new WindowAdapter() {
			                    public void windowClosing(WindowEvent e) {
			                        System.exit(0);
			                    }
			                });
			}
		});
     }
}
