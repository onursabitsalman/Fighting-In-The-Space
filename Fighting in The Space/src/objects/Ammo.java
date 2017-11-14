package objects;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ammo extends JLabel{
	
	
	private static ImageIcon [] kursunImage = {new ImageIcon("Pictures/blasterTwo.png"),new ImageIcon("Pictures/bullet2.png"),
											   new ImageIcon("Pictures/laser.png"),new ImageIcon("Pictures/blaster.png")};
	
	private static final int [] WIDTH = {29,90,80,29};
	private static final int [] HEIGHT = {50,39,3,50};
	private static int []  STEP = {10,15,45,10};
	private static int []  DELAY = {10,30,20,10};
	public static final int ALLYAMMO = 1;
	public static final int ENEMYAMMO = -1;
	public static final int FASTAMMO = 0;
	public static final int STRONGAMMO = 1;
	public static final int LASERAMMO = 2;
	public static final int ENEMYFASTAMMO = 3;
	
	private int x = 0;
	private int y = 0;
	private int kursunTipi = 0;
	private int direction = 1;
	public Ammo(int x, int y,int direction ,int kursunTipi) {
		
		super(kursunImage[kursunTipi]);
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.kursunTipi = kursunTipi;
		setBounds(x,y, WIDTH[kursunTipi], HEIGHT[kursunTipi]);
	}
	public void setLocation(int x,int y){
		super.setLocation(x, y);
		this.x = x;
		this.y = y;
	}
	public int getWidth() {
		return WIDTH[kursunTipi];
	}
	public int getHeight() {
		return HEIGHT[kursunTipi];
	}
	public int getStep() {
		return STEP[kursunTipi];
	}
	public int getDelay() {
		return DELAY[kursunTipi];
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getDirection() {
		return direction;
	}
	public void setType(int kursunTipi){
		this.kursunTipi = kursunTipi;
	}
	public int getType(){
		return kursunTipi;
	}
	
}
