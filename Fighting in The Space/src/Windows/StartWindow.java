package Windows;

//Onur Sabit Salman - 150114501
//Tuðberk Çelik - 150113057


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import objects.Ammo;
import objects.GameSound;
import objects.SpaceShip;


public class StartWindow extends JFrame {
	
	JLabel titleLabel = new JLabel("FIGHTING IN THE SPACE");	
	JLabel lblStart = new JLabel("START");
	JLabel lblLoad = new JLabel("LOAD");
	JLabel lblSettings = new JLabel("SETTINGS");
	JLabel lblQuit = new JLabel("QUIT");
	JPanel contentPanel = new JPanel();	
	JPanel menuPanel = new JPanel();
	
	//SettingsWindow sw;
	
	JLabel backgroundImage = new JLabel(new ImageIcon("Pictures/1.gif"));	
	
	public StartWindow() {
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 100, 800, 600);
		setContentPane(contentPanel);
		contentPanel.setBounds(0, 0, 784, 561);
		contentPanel.setLayout(null);
		
		titleLabel.setForeground(new Color(255, 204, 0));
		titleLabel.setFont(new Font("Tekton Pro Ext", Font.BOLD, 28));
		titleLabel.setBounds(192, 64, 371, 121);
		contentPanel.add(titleLabel);
		
		menuPanel.setBounds(289, 212, 206, 232);
		contentPanel.add(menuPanel);
		menuPanel.setLayout(new GridLayout(4, 0, 5, 5));
		menuPanel.setOpaque(false);
		
		lblStart.addMouseListener(new MyMouseEvents());
		lblStart.setForeground(Color.WHITE);
		lblStart.setFont(new Font("Tekton Pro Ext", Font.BOLD, 28));
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(lblStart);
		lblSettings.addMouseListener(new MyMouseEvents());
		
		lblLoad.addMouseListener(new MyMouseEvents());
		lblLoad.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoad.setForeground(Color.WHITE);
		lblLoad.setFont(new Font("Tekton Pro Ext", Font.BOLD, 28));
		
		menuPanel.add(lblLoad);
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setFont(new Font("Tekton Pro Ext", Font.BOLD, 28));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(lblSettings);
		
		lblQuit.addMouseListener(new MyMouseEvents());
		lblQuit.setForeground(Color.WHITE);
		lblQuit.setFont(new Font("Tekton Pro Ext", Font.BOLD, 28));
		lblQuit.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(lblQuit);
		


		backgroundImage.setBounds(0, 0, 794, 571);
		contentPanel.add(backgroundImage);
		
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	SettingsWindow setting = null;
	GameWindow window = null;
	

	public class MyMouseEvents extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e) {
			

			if(e.getSource().equals(lblStart)){
				
				if(setting == null){
						
					window = new GameWindow(0);
					window.setVisible(true);		
				}
				
				else window = new GameWindow(setting.getSelectedShip());{
					window.setVisible(true);}				
			}	 
						
			else if(e.getSource().equals(lblLoad)){
				int ey,edx,edy,ety,change = 1,x,y,type = 0,life = 0,score = 0;
				ArrayList<SpaceShip> enemyList = new ArrayList<SpaceShip>();
				ArrayList<Ammo> ammoList = new ArrayList<Ammo>();
				SpaceShip gemi = new SpaceShip(0, 0, 0);
				try {
					FileInputStream file = new FileInputStream("file.txt");
					DataInputStream data = new DataInputStream(file);
					x = data.readInt();
					data.readUTF();
					y = data.readInt();
					data.readUTF();
					type = data.readInt();
					data.readUTF();
					life = data.readInt();
					data.readUTF();
					score = data.readInt();
					data.readUTF();
					gemi = new SpaceShip(x, y, type);
					while(true){
						int ex = data.readInt();
						data.readUTF();
						if( ex == -1){
							ex = data.readInt();
							data.readUTF();
							change = 0;
						}
						if(change == 1){
							ey = data.readInt();
							data.readUTF();
							edx = data.readInt();
							data.readUTF();
							edy = data.readInt();
							data.readUTF();
							ety = data.readInt();
							data.readUTF();
							SpaceShip enemy = new SpaceShip(ex, ey, ety);
							enemy.setXDirection(edx);
							enemy.setYDirection(edy);
							enemyList.add(enemy);
						}
						else{
							ey = data.readInt();
							data.readUTF();
							edx = data.readInt();
							data.readUTF();
							edy = data.readInt();
							data.readUTF();
							Ammo ammo = new Ammo(ex, ey, edx, edy);
							ammoList.add(ammo);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					window = new GameWindow(gemi, life, score, enemyList, ammoList);
					
					window.setVisible(true);
				
				}
			}	
				
			else if(e.getSource().equals(lblSettings)){
				setting = new SettingsWindow();
					
			}
				
			else if(e.getSource().equals(lblQuit)){
				System.exit(1);
				
			}
		}
	}
}