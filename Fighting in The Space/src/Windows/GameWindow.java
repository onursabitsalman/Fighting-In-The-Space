package Windows;

import java.awt.*;
import objects.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class GameWindow extends JFrame {
	
	private JPanel contentPane;
	
	private SpaceShip gemi,enemy,boss1,boss2;
	private Boom boom ;
	
	private final int SCREENHEIGHT= 900;
	private final int SCREENWIDTH= 1200;
	private final int LIMIT = 5;
	
	private ArrayList<SpaceShip> enemyList = new ArrayList<SpaceShip>();
	private ArrayList<Ammo> ammoList = new ArrayList<Ammo>();
	private ArrayList<Boom> boomList = new ArrayList<Boom>();

	private Timer motion = new Timer(10, new TimerListener());
	private int counter = 0,counterAmmo,enemyCounter,life = 10,score = 0,boss1Counter = 0,boss1Life = 15, boss2Counter = 0, boss2Life = 30;
	
	private JLabel bigText = new JLabel();
	private boolean paused = false;
	private JLabel scoreLbl = new JLabel();
	private JLabel lifeLbl = new JLabel();
	private JButton btnTryAgain = new JButton("PLAY AGAIN");
	private JButton btnExit = new JButton("QUIT");

	/**
	 * @wbp.parser.constructor
	 */
	public GameWindow(int gemiTipi){
		
		gemi = new SpaceShip(5,50,gemiTipi);
		init();
		
	}
	public GameWindow(SpaceShip gemi,int life,int score,ArrayList<SpaceShip> enemyList,ArrayList<Ammo> ammoList) {

		this.gemi = gemi;
		this.enemyList = enemyList;
		this.ammoList = ammoList;
		this.life = life;
		this.score = score;

		
		init();
		for(int i=0;i<enemyList.size();i++){
			getContentPane().add(enemyList.get(i));
		}
		for(int i=0;i<ammoList.size();i++){
			getContentPane().add(ammoList.get(i));
		}
		for(int i=0;i<boomList.size();i++){
			getContentPane().add(boomList.get(i));
		}
		
		
		motion.stop();
		paused = true;
		bigText.setText("Paused");
		bigText.setVisible(true);
	}
	public void init(){
		new GameSound();
		setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);

		contentPane = new JPanel();
		contentPane.addKeyListener(new MyController());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setFocusable(true);
		contentPane.setBackground(Color.BLACK);
		setResizable(false);
		

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmPause = new JMenuItem("Pause");
		mntmPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(paused == false){
					paused = true;
					motion.stop();
					bigText.setText("Paused");
					bigText.setVisible(true);
				}
				else{
					paused = false;
					motion.start();
					bigText.setVisible(false);
				}
			}
		});
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					paused = true;
					motion.stop();
					bigText.setText("Saved and Paused");
					bigText.setVisible(true);

					FileOutputStream file = new FileOutputStream("file.txt");
					DataOutputStream data = new DataOutputStream(file);
					data.writeInt(gemi.getX());
					data.writeUTF("\t");
					data.writeInt(gemi.getY());
					data.writeUTF("\t");
					data.writeInt(gemi.getType());
					data.writeUTF("\t");
					data.writeInt(life);
					data.writeUTF("\t");
					data.writeInt(score);
					data.writeUTF("\n");
					for(int i=0;i<enemyList.size();i++){
						data.writeInt(enemyList.get(i).getX());
						data.writeUTF("\t");
						data.writeInt(enemyList.get(i).getY());
						data.writeUTF("\t");
						data.writeInt(enemyList.get(i).getXDirection());
						data.writeUTF("\t");
						data.writeInt(enemyList.get(i).getYDirection());
						data.writeUTF("\t");
						data.writeInt(enemyList.get(i).getType());
						data.writeUTF("\n");
					}
					data.writeInt(-1);
					data.writeUTF("\n");
					for(int i=0;i<ammoList.size();i++){
						data.writeInt(ammoList.get(i).getX());
						data.writeUTF("\t");
						data.writeInt(ammoList.get(i).getY());
						data.writeUTF("\t");
						data.writeInt(ammoList.get(i).getDirection());
						data.writeUTF("\t");
						data.writeInt(ammoList.get(i).getType());
						data.writeUTF("\n");
					}
					data.close();
					file.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
			}
		});
		mnNewMenu.add(saveMenuItem);
		mnNewMenu.add(mntmPause);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		


		contentPane.add(gemi);
		bigText.setForeground(Color.WHITE);
		
		bigText.setHorizontalAlignment(SwingConstants.CENTER);
		bigText.setVisible(false);
		
		boss1 = new SpaceShip(SCREENWIDTH-100 , (int)(Math.random()*SCREENHEIGHT)-SpaceShip.HEIGHT[SpaceShip.BOSS1],SpaceShip.BOSS1);
		boss1.setLocation(2000, 2000);
		contentPane.add(boss1);
		boss1.setVisible(false);
		
		boss2 = new SpaceShip(SCREENWIDTH-100 , (int)(Math.random()*SCREENHEIGHT)-SpaceShip.HEIGHT[SpaceShip.BOSS2],SpaceShip.BOSS2);
		boss2.setLocation(2000, 2000);
		contentPane.add(boss2);
		boss2.setVisible(false);

		bigText.setFont(new Font("Tekton Pro", Font.PLAIN, 53));
		bigText.setBounds(369, 265, 448, 210);
		contentPane.add(bigText);
		lifeLbl.setForeground(Color.WHITE);
		
		lifeLbl.setFont(new Font("Tekton Pro", Font.PLAIN, 16));
		lifeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		lifeLbl.setBounds(954, 0, 116, 38);
		lifeLbl.setText("Life : "+life);
		contentPane.add(lifeLbl);
		scoreLbl.setForeground(Color.WHITE);
		
		scoreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLbl.setFont(new Font("Tekton Pro", Font.PLAIN, 16));
		scoreLbl.setBounds(1068, 0, 116, 38);
		scoreLbl.setText("Score : "+score);
		contentPane.add(scoreLbl);
	
	
		btnTryAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindow window = new GameWindow(0);
				window.setVisible(true);
			}
		});
		btnTryAgain.setBounds(369, 502, 222, 57);
		btnTryAgain.setVisible(false);
		contentPane.add(btnTryAgain);
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(595, 502, 222, 57);
		btnExit.setVisible(false);
		contentPane.add(btnExit);
		
		  motion.start();
		  setLocationRelativeTo(null);
			
	}
	public ArrayList<SpaceShip> getEnemyList(){
		return enemyList;
	}
	public ArrayList<Boom> getBoomList(){
		return boomList;
	}
	
	int forBoss1 = 1;
	int forBoss2 = 1;
	
	private class TimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			counter ++;
			counterAmmo++;
			enemyCounter++;
			boss1Counter++;
			boss2Counter++;

			
			for(int i=0 ;i<ammoList.size();i++){
				int x = 0;
				if(!removeCrashed(ammoList.get(i))){
					if(ammoList.get(i).getX() < 0 ){
						remove(ammoList.get(i));
						ammoList.remove(i);
					}
					else if(ammoList.get(i).getX() > getWidth() - ammoList.get(i).getWidth()){
						remove(ammoList.get(i));
						ammoList.remove(i);
					}
					else {
						x = ammoList.get(i).getX()+ammoList.get(i).getStep()*ammoList.get(i).getDirection();
						ammoList.get(i).setLocation(x, ammoList.get(i).getY());
					}
				}
				repaint();				
			}
			

			
			if(counter == 10 ){
				
				int x = 0;
				int y = 0;
				for(int i =0;i<enemyList.size();i++){
					
					if(enemyList.get(i).getY() < 0 ){
						enemyList.get(i).setYDirection(SpaceShip.FORWARD);
					}
					else if(enemyList.get(i).getY() > getHeight()- enemyList.get(i).getHeight()){
						enemyList.get(i).setYDirection(SpaceShip.REVERSE);
						
					}
					if(enemyList.get(i).getX() < 0 ){
						remove(enemyList.get(i));
						enemyList.remove(i);
						
					}
					else if(enemyList.get(i).getX() > getWidth() - enemyList.get(i).getWidth()){
						remove(enemyList.get(i));
						enemyList.remove(i);
						

					}
					else {
						x = enemyList.get(i).getX()+enemyList.get(i).getStep()*enemyList.get(i).getXDirection();
						y = enemyList.get(i).getY() + enemyList.get(i).getStep()*enemyList.get(i).getYDirection();
						enemyList.get(i).setLocation(x, y);
						
					}
					

					
					repaint();
					
				}

				
				counter = 0;
				
			}
		
			
			if(counterAmmo == 100 ){
				for(int i =0;i<enemyList.size();i++){
					Ammo newAmmo = new Ammo(enemyList.get(i).getX(), enemyList.get(i).getY(), Ammo.ENEMYAMMO, Ammo.ENEMYFASTAMMO);
					ammoList.add(newAmmo);
					add(newAmmo);
					repaint();
				}
				counterAmmo = 0;
			}
			
			
			if(enemyCounter == 50){
				int x = 0;
				int y = 0;
				
				if(enemyList.size() < LIMIT){
					
					x = SCREENWIDTH-100;
					y = (int)(Math.random()*SCREENHEIGHT)-SpaceShip.HEIGHT[SpaceShip.ENEMY];
					
					enemy = new SpaceShip(x , y,SpaceShip.ENEMY);
				
					if((int)(Math.random()*10)<5){
						enemy.setYDirection(SpaceShip.REVERSE);
					}
					
					else if((int)(Math.random()*10)>=5){
						enemy.setYDirection(SpaceShip.FORWARD);
					}
					
					enemyList.add(enemy);
					add(enemy);
			
			
				}
				

				
				enemyCounter= 0;
			}
			
			
			
			if(boss1Counter == 30){

				if(score >= 300 && forBoss1 == 1){
					
					boss1.setLocation(1068, 375);
					boss1.setVisible(true);
					forBoss1 = 0;
				}
				else if(score >= 300 && boss1Life > 0){
					
					
					if(boss1.getY() < 0 ){
						boss1.setYDirection(SpaceShip.FORWARD);
					}
					else if(boss1.getY() > getHeight()- boss1.getHeight()-100){
						boss1.setYDirection(SpaceShip.REVERSE);
					}
					
					int y = boss1.getY() + boss1.getStep()*boss1.getYDirection();
					boss1.setLocation(1068 , y);
					
					Ammo newAmmo = new Ammo(boss1.getX(), boss1.getY()+45, Ammo.ENEMYAMMO, Ammo.LASERAMMO);
					ammoList.add(newAmmo);
					add(newAmmo);
					repaint();
					
					
				}

				boss1Counter = 0;

			}
			
			if(boss2Counter == 10 ){
				
				if(score >= 1000 && forBoss2 == 1){
					
					boss2.setLocation(1068, 375);
					boss2.setVisible(true);
					forBoss2 = 0;
					
				}
				
				else if(score >= 1000 && boss2Life > 0){
					
					boss2.setVisible(true);
					
					if(boss2.getY() < 0 ){
						boss2.setYDirection(SpaceShip.FORWARD);
					}
					else if(boss2.getY() > getHeight()- boss2.getHeight()-100){
						boss2.setYDirection(SpaceShip.REVERSE);
					}
					
					int y = boss2.getY() + boss2.getStep()*boss2.getYDirection();
					boss2.setLocation(1068 , y);
					
					Ammo newAmmo = new Ammo(boss2.getX(), boss2.getY()+40, Ammo.ENEMYAMMO, Ammo.STRONGAMMO);
					ammoList.add(newAmmo);
					add(newAmmo);
					repaint();
					
					
				}

				boss2Counter = 0;

			}
			
			

		}
	}
	SettingsWindow setting = null;
	
	public class MyController extends KeyAdapter {
		
		
		public void keyPressed(KeyEvent e) {
			if(life > 0 && !paused){
				if(e.getKeyCode() == KeyEvent.VK_UP){
					int temp = gemi.getY()-gemi.getStep();
					if(temp <=0  ){
						temp = 0;
					}
					gemi.setLocation(gemi.getX(), temp);
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					int temp = gemi.getY()+gemi.getStep();
					if(temp  >= SCREENHEIGHT-(2*gemi.getHeight() )){
						temp = SCREENHEIGHT-(2*gemi.getHeight() );
					}
					gemi.setLocation(gemi.getX(), temp);
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					int temp = gemi.getX()+gemi.getStep();
					gemi.setLocation(temp, gemi.getY());
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					int temp = gemi.getX()-gemi.getStep();
					if(temp  <= 0){
						temp = 0;
					}
					gemi.setLocation(temp, gemi.getY());
				}
				
				
				else if(e.getKeyCode() == KeyEvent.VK_A){
				
					Ammo kursun = new Ammo(gemi.getX()+gemi.getWidth(), gemi.getY()+gemi.getHeight()/2-20, Ammo.ALLYAMMO, Ammo.FASTAMMO);
					new FastAmmoSound();
					ammoList.add(kursun);
					contentPane.add(kursun);
				}
				else if(e.getKeyCode() == KeyEvent.VK_S){
					
					Ammo kursun = new Ammo(gemi.getX()+gemi.getWidth(), gemi.getY()+gemi.getHeight()/2-10, Ammo.ALLYAMMO, Ammo.STRONGAMMO);
					new StrongAmmoSound();
					ammoList.add(kursun);
					contentPane.add(kursun);
				}
				else if(e.getKeyCode() == KeyEvent.VK_D){
					
					Ammo kursun = new Ammo(gemi.getX()+gemi.getWidth(), gemi.getY()+gemi.getHeight()/2, Ammo.ALLYAMMO, Ammo.LASERAMMO);
					new LaserSound();
					ammoList.add(kursun);
					contentPane.add(kursun);
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_L){
					
					life = 10;
					lifeLbl.setText("Life :" + life);
				}
				contentPane.repaint();
			}
		}		
	}
	private boolean removeCrashed(Ammo ammo){

		
		Rectangle rec = ammo.getBounds();
		boolean value = false;
		if(ammo.getDirection() == Ammo.ALLYAMMO){
			for(int i= 0; i< enemyList.size();i++){
				if(rec.intersects(enemyList.get(i).getBounds())){
					
					new BoomSound();
					score += 10;
					scoreLbl.setText("Score : " + score);
					scoreLbl.repaint();
					remove(ammo);
					ammoList.remove(ammo);
					remove(enemyList.get(i));
					enemyList.remove(i);

					repaint();
					value = true;								

				}
			}
			
			if(rec.intersects(boss1.getBounds())){
				
				if(boss1Life > 0){
					
					boss1Life = boss1Life - 1;
					remove(ammo);
					ammoList.remove(ammo);
					
				}

				else if(boss1Life == 0){
					
					new BoomSound();
					score += 300;
					boss1Life = -1;
					remove(boss1);
					remove(ammo);
					ammoList.remove(ammo);	
					value = true;
					
				}
				
				repaint();

			}
			
			if(rec.intersects(boss2.getBounds())){
				
				if(boss2Life > 0){
					
					boss2Life = boss2Life - 1;
					remove(ammo);
					ammoList.remove(ammo);
					
				}

				else if(boss2Life == 0){
					
					new BoomSound();
					score += 1000;
					boss2Life = -1;
					remove(boss2);
					remove(ammo);
					ammoList.remove(ammo);
					bigText.setText("VICTORY");
					bigText.setVisible(true);
					btnTryAgain.setVisible(true);
					btnExit.setVisible(true);
					motion.stop();
					value = true;
					
				}
				
				repaint();

			}
		}
			
		if(rec.intersects(gemi.getBounds())){
			life--;
			lifeLbl.setText("Life :" + life);
			remove(ammo);
			ammoList.remove(ammo);
			if(life == 0){
				bigText.setText("GAME OVER");
				new BoomSound();
				bigText.setVisible(true);
				btnTryAgain.setVisible(true);
				btnExit.setVisible(true);
				motion.stop();
				remove(gemi);
				boom = new Boom(gemi.getX(),gemi.getY(),0);
				getContentPane().add(boom);
				
			}
			
			repaint();
			value = true;
		}
		return value;
	}
	
}