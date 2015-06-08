package primary;

import gamedata.TextBoxes;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HUDButtons {

	public static BufferedImage[] buttonImages;
	public static BufferedImage[] menuBarAssembly;
	static boolean[] pressed;
	static short rolloverOccuring;
	public static Shape[] buttonShapes;
	static String[] buttonRollovers;
	
	static int numButtons;
	
	int xButton[];
	int yButton[];
	
	int rolloverWidth;
	int rolloverHeight;
	int rolloverSize;
	
	static TextBoxes textBoxes = new TextBoxes();
	
	public HUDButtons(){
		numButtons = 3;
		buttonImages = new BufferedImage[2*numButtons];
		menuBarAssembly = new BufferedImage[5];
		pressed = new boolean[numButtons];
		
		rolloverOccuring = -1; //as false
		buttonShapes = new Shape[numButtons];
		
		buttonRollovers = new String[numButtons];
		
		rolloverWidth = 128;
		rolloverHeight = 64;
		
		rolloverSize = 2;
		
		xButton = new int[numButtons];
		yButton = new int[numButtons];
		
		loadButtons();
		setupShapes();
		writeRolloverText();
	}
	
	private void writeRolloverText() {
		//Reminder to update the numButtons whenever you add more rollovers
		buttonRollovers[0] = "abcdefghijklmnopqrstuvwxyz";
		buttonRollovers[1] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		buttonRollovers[2] = "lmnop";
		
	}

	private void setupShapes() {
		for (int i = 0; i < numButtons; i++){
			int[] pX = {xButton[i], xButton[i]+20, xButton[i]+60, xButton[i]+80, xButton[i]+60, xButton[i]+20};
			int[] pY = {yButton[i]+20, yButton[i], yButton[i], yButton[i]+20, yButton[i]+40, yButton[i]+40};
			buttonShapes[i] = new Polygon(pX, pY, 6);
		}
	}

	private void loadButtons() {
		try{
			buttonImages[0] = ImageIO.read(new File("res/button1press.png"));
			buttonImages[1] = ImageIO.read(new File("res/button1unpress.png"));
			buttonImages[2] = ImageIO.read(new File("res/button1press.png"));
			buttonImages[3] = ImageIO.read(new File("res/button1unpress.png"));
			buttonImages[4] = ImageIO.read(new File("res/button1press.png"));
			buttonImages[5] = ImageIO.read(new File("res/button1unpress.png"));
			
		} catch(IOException e){
			System.out.println("wuhoh, pic not loaded for buttons");
			e.printStackTrace();
		}
		try{
			menuBarAssembly[0] = ImageIO.read(new File("res/menubarbase.png"));
			
		} catch(IOException e){
			System.out.println("wuhoh, pic not loaded for buttons");
			e.printStackTrace();
		}
	}

	public static void pressButton(int i){
		pressed[i] = true;
	}
	
	public static void releaseButton(){
		for (int j = 0; j < pressed.length; j++)
		pressed[j] = false;
	}

	public static void clicked(int xMouse, int yMouse) {
		for (int i = 0; i < buttonShapes.length; i++){
			if(buttonShapes[i].contains(xMouse, yMouse)){
				releaseButton();
				pressButton(i);
				System.out.println("click press");
				return;
			}
			else{
				releaseButton();
			}
		}
	}

	//public static BufferedImage rollover(int xMouse, int yMouse, int i) {
	//	return TextBoxes.display(buttonRollovers[i], 256, 32);
		
	//}
}
