package objects;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SpaceShip extends JLabel{

	private static ImageIcon [] gemiImage = {new ImageIcon("Pictures/space1.png"),new ImageIcon("Pictures/space2.png"),
											 new ImageIcon("Pictures/space3.png"),new ImageIcon("Pictures/enemy.png"), 
											 new ImageIcon("Pictures/boss1.png"), new ImageIcon("Pictures/boss2.png")};
	
	public static final int [] WIDTH = {54,121,100,49,121,100};
	public static final int [] HEIGHT = {38,100,100,64,100,100};
	public static final int [] STEP = {15,15,15,5,15,15};
	
	public static final int FASTSHIP = 0;
	public static final int FORWARD = 1;
	public static final int REVERSE = -1;
	public static final int STRONGSHIP = 1;
	public static final int HANDSOMESHIP = 2;
	public static final int ENEMY = 3;
	public static final int BOSS1 = 4;
	public static final int BOSS2 = 5;
	private int x ,type,y,yDirection = 1,xDirection = -1;

	
	
	public SpaceShip(int x,int y,int type) {

		super(gemiImage[type]);
		this.type = type;
		this.x =  x;
		this.y = y;
		setBounds(x, y, WIDTH[type], HEIGHT[type]);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getXDirection() {
		return xDirection;
	}
	public void setXDirection(int xDirection) {
		this.xDirection = xDirection;
	}
	public int getYDirection() {
		return yDirection;
	}
	public void setYDirection(int direction) {
		this.yDirection = direction;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public int getWidth(){
		return WIDTH[type];
	}
	public int getHeight(){
		return HEIGHT[type];
	}
	public int getStep(){
		return STEP[type];
	}
	public void setLocation(int x, int y){
		super.setLocation(x, y);
		this.x = x;
		this.y = y;
	}
	public void setDelay(int delay) {
	}
}