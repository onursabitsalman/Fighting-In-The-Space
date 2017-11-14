package Windows;

//Onur Sabit Salman - 150114501
//Tuðberk Çelik - 150113057


import java.awt.EventQueue;


public class GameRun {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow frame = new StartWindow();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			
	}
}
