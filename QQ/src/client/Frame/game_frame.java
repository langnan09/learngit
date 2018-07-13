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
   	     setResizable(false);//禁止用户改变窗体大小
//		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//注释掉这个后关掉此界面好友界面不会关闭
		      JPanel panel=new JPanel();
	      panel.setLayout(null);
	      panel.setPreferredSize(new Dimension(550, 800));	      
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
	      JButton button1=new JButton("推箱子");
	      JButton button2=new JButton("画图板");
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
