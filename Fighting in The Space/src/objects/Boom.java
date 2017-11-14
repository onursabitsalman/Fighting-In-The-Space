package objects;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Boom extends JLabel{
	
	
	private static ImageIcon [] explosionImage = {new ImageIcon("Pictures/explosion.gif")};
	
	private static final int [] WIDTH = {142};
	private static final int [] HEIGHT = {200};
	
	public static final int EXPLOSION = 0;
	
	private int x = 0;
	private int y = 0;
	private int explosionType = 0;
	
	public Boom(int x, int y ,int explosionType) {
		
		super(explosionImage[explosionType]);
		this.x = x;
		this.y = y;
		this.explosionType = explosionType;
		setBounds(x,y, WIDTH[explosionType], HEIGHT[explosionType]);
	}
	public void setLocation(int x,int y){
		super.setLocation(x, y);
		this.x = x;
		this.y = y;
	}
	public int getWidth() {
		return WIDTH[explosionType];
	}
	public int getHeight() {
		return HEIGHT[explosionType];
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setType(int explosionType){
		this.explosionType = explosionType;
	}
	public int getType(){
		return explosionType;
	}
	
}
