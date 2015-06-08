package primary;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;


import javax.swing.JFrame;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 5458905388552833773L;
	
	int d = 0;
	MainWindow(){
	super("Title Goes Here");
	pack();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setVisible(true);
	setSize(740, 500);
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.dispose();
		bs.show();
		
	}

}
