package Windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class SettingsWindow extends JFrame {
	
	private int selectedShip = 0;
	private int selectedSound = 0;
	
	JLabel backgroundImage = new JLabel(new ImageIcon("Pictures/4.gif"));
	
	String [] ships = {"FAST SHIP","STRONG SHIP","HANDSOME SHIP"};

	JComboBox shipType = new JComboBox(ships);
	

	private JPanel contentPane;
		
	JLabel lblSettings = new JLabel("SETTINGS");
	JLabel lblSpaceShips = new JLabel("SPACE SHIPS  =");
	JLabel lblBack = new JLabel("<-  BACK");
	private final JLabel lblNewLabel = new JLabel("HOW TO PLAY");
	
	public SettingsWindow() {
		
		setBounds(0, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		setVisible(true);		
		contentPane.setLayout(null);
		setResizable(false);
		
		lblSettings.setFont(new Font("Tekton Pro Ext", Font.BOLD, 30));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setBounds(218, 99, 259, 49);
		contentPane.add(lblSettings);
		
		
		lblSpaceShips.setFont(new Font("Tekton Pro Ext", Font.BOLD, 20));
		lblSpaceShips.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpaceShips.setForeground(Color.WHITE);
		lblSpaceShips.setBounds(165, 204, 213, 50);
		contentPane.add(lblSpaceShips);
		
		
		shipType.setSelectedIndex(0);
		shipType.setBounds(383, 206, 138, 49);
		shipType.addActionListener(new ComboBoxEvents());
		contentPane.add(shipType);
		

		lblBack.addMouseListener(new MyMouseEvents());
		lblBack.setFont(new Font("Tekton Pro Ext", Font.BOLD, 20));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setForeground(Color.WHITE);
		lblBack.setBounds(43, 464, 127, 49);
		contentPane.add(lblBack);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tekton Pro Ext", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(165, 330, 356, 54);
		
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Direction Keys = UP / DOWN / RIGHT / LEFT\r\n");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tekton Pro", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(165, 392, 356, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("A  ----> Two Bullet\r\n");
		lblNewLabel_2.setFont(new Font("Tekton Pro", Font.BOLD, 13));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(165, 429, 106, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("S  ----> Fire Bullet\r\n");
		lblNewLabel_3.setFont(new Font("Tekton Pro", Font.BOLD, 13));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(281, 429, 106, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("D  ----> Laser\r\n");
		lblNewLabel_4.setFont(new Font("Tekton Pro", Font.BOLD, 13));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(398, 429, 106, 14);
		contentPane.add(lblNewLabel_4);
		
		backgroundImage.setBounds(0, 0, 784, 561);
		contentPane.add(backgroundImage);
		setLocationRelativeTo(null);
			
	}

		
	public class MyMouseEvents extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(lblBack)){
				setVisible(false);
			}
		}
	}	
	

	public class ComboBoxEvents implements ActionListener {
	    
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(shipType)){
				selectedShip = shipType.getSelectedIndex();				
			}
			
		} 
	} 
	
	public int getSelectedShip() {		
		return selectedShip;
	}
}