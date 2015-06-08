package gamedata;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


import javax.imageio.ImageIO;

public class TextBoxes {
	static BufferedImage textPack;
	static BufferedImage textBox;
	public TextBoxes(){
		try{
			textPack = ImageIO.read(new File("res/textpack.png"));
			textBox = ImageIO.read(new File("res/textbox.png"));
			
		} catch(IOException e){
			System.out.println("fonts not loaded");
			e.printStackTrace();
		}		
		loadFont(textPack);
		
	}
	
	
	private void loadFont(BufferedImage textPack) {
		
		
	}


	public BufferedImage display(String string, int xBox, int yBox){
		//converts a string to a composite image of the font.
		BufferedImage[] imgText = new BufferedImage[string.length()];
		int[] pixelLength = new int[string.length()];
		Arrays.fill(pixelLength, 0);
		for(int i = 0; i < string.length(); i++)
		switch (string.charAt(i)){
			case 'a': imgText[i] = textPack.getSubimage(1, 1, 8, 10); pixelLength[i] = 7; break;
			case 'b': imgText[i] = textPack.getSubimage(17, 1, 8, 10); pixelLength[i] = 7; break;
			case 'c': imgText[i] = textPack.getSubimage(33, 1, 8, 10); pixelLength[i] = 7; break;
			case 'd': imgText[i] = textPack.getSubimage(49, 1, 8, 10); pixelLength[i] = 7; break;
			case 'e': imgText[i] = textPack.getSubimage(65, 1, 8, 10); pixelLength[i] = 7; break;
			case 'f': imgText[i] = textPack.getSubimage(81, 1, 8, 10); pixelLength[i] = 7; break;
			case 'g': imgText[i] = textPack.getSubimage(97, 1, 8, 10); pixelLength[i] = 7; break;
			case 'h': imgText[i] = textPack.getSubimage(113, 1, 8, 10); pixelLength[i] = 7; break;
			case 'i': imgText[i] = textPack.getSubimage(129, 1, 8, 10); pixelLength[i] = 7; break;
			case 'j': imgText[i] = textPack.getSubimage(145, 1, 8, 10); pixelLength[i] = 7; break;
			case 'k': imgText[i] = textPack.getSubimage(161, 1, 8, 10); pixelLength[i] = 7; break;
			case 'l': imgText[i] = textPack.getSubimage(177, 1, 8, 10); pixelLength[i] = 7; break;
			case 'm': imgText[i] = textPack.getSubimage(193, 1, 8, 10); pixelLength[i] = 7; break;
			case 'n': imgText[i] = textPack.getSubimage(209, 1, 8, 10); pixelLength[i] = 7; break;
			case 'o': imgText[i] = textPack.getSubimage(225, 1, 8, 10); pixelLength[i] = 7; break;
			case 'p': imgText[i] = textPack.getSubimage(241, 1, 8, 10); pixelLength[i] = 7; break;
			
			case 'q': imgText[i] = textPack.getSubimage(1, 17, 8, 10); pixelLength[i] = 7; break;
			case 'r': imgText[i] = textPack.getSubimage(17, 17, 8, 10); pixelLength[i] = 7; break;
			case 's': imgText[i] = textPack.getSubimage(33, 17, 8, 10); pixelLength[i] = 7; break;
			case 't': imgText[i] = textPack.getSubimage(49, 17, 8, 10); pixelLength[i] = 7; break;
			case 'u': imgText[i] = textPack.getSubimage(65, 17, 8, 10); pixelLength[i] = 7; break;
			case 'v': imgText[i] = textPack.getSubimage(81, 17, 9, 10); pixelLength[i] = 9; break;
			case 'w': imgText[i] = textPack.getSubimage(97, 17, 8, 10); pixelLength[i] = 7; break;
			case 'x': imgText[i] = textPack.getSubimage(113, 17, 8, 10); pixelLength[i] = 7; break;
			case 'y': imgText[i] = textPack.getSubimage(129, 17, 9, 10); pixelLength[i] = 9; break;
			case 'z': imgText[i] = textPack.getSubimage(145, 17, 8, 10); pixelLength[i] = 7; break;
			case ' ': imgText[i] = textPack.getSubimage(161, 17, 8, 10); pixelLength[i] = 7; break;
			//case ' ': imgText[i] = textpack.getSubimage(177, 17, 8, 10); break;
			//case ' ': imgText[i] = textpack.getSubimage(193, 17, 8, 10); break;
			//case ' ': imgText[i] = textpack.getSubimage(209, 17, 8, 10); break;
			//case ' ': imgText[i] = textpack.getSubimage(225, 17, 8, 10); break;
			//case ' ': imgText[i] = textpack.getSubimage(241, 17, 8, 10); break;
			
			case 'A': imgText[i] = textPack.getSubimage(1, 33, 9, 10); pixelLength[i] = 9; break;
			case 'B': imgText[i] = textPack.getSubimage(17, 33, 8, 10); pixelLength[i] = 7; break;
			case 'C': imgText[i] = textPack.getSubimage(33, 33, 8, 10); pixelLength[i] = 7; break;
			case 'D': imgText[i] = textPack.getSubimage(49, 33, 8, 10); pixelLength[i] = 8; break;
			case 'E': imgText[i] = textPack.getSubimage(65, 33, 8, 10); pixelLength[i] = 7; break;
			case 'F': imgText[i] = textPack.getSubimage(81, 33, 8, 10); pixelLength[i] = 7; break;
			case 'G': imgText[i] = textPack.getSubimage(97, 33, 8, 10); pixelLength[i] = 8; break;
			case 'H': imgText[i] = textPack.getSubimage(113, 33, 8, 10); pixelLength[i] = 7; break;
			case 'I': imgText[i] = textPack.getSubimage(129, 33, 8, 10); pixelLength[i] = 6; break;
			case 'J': imgText[i] = textPack.getSubimage(145, 33, 8, 10); pixelLength[i] = 6; break;
			case 'K': imgText[i] = textPack.getSubimage(161, 33, 8, 10); pixelLength[i] = 7; break;
			case 'L': imgText[i] = textPack.getSubimage(177, 33, 8, 10); pixelLength[i] = 7; break;
			case 'M': imgText[i] = textPack.getSubimage(193, 33, 8, 10); pixelLength[i] = 7; break;
			case 'N': imgText[i] = textPack.getSubimage(209, 33, 8, 10); pixelLength[i] = 7; break;
			case 'O': imgText[i] = textPack.getSubimage(225, 33, 8, 10); pixelLength[i] = 7; break;
			case 'P': imgText[i] = textPack.getSubimage(241, 33, 8, 10); pixelLength[i] = 7; break;
			
			case 'Q': imgText[i] = textPack.getSubimage(1, 49, 8, 10); pixelLength[i] = 7; break;
			case 'R': imgText[i] = textPack.getSubimage(17, 49, 8, 10); pixelLength[i] = 7; break;
			case 'S': imgText[i] = textPack.getSubimage(33, 49, 8, 10); pixelLength[i] = 7; break;
			case 'T': imgText[i] = textPack.getSubimage(49, 49, 8, 10); pixelLength[i] = 7; break;
			case 'U': imgText[i] = textPack.getSubimage(65, 49, 8, 10); pixelLength[i] = 7; break;
			case 'V': imgText[i] = textPack.getSubimage(81, 49, 8, 10); pixelLength[i] = 7; break;
			case 'W': imgText[i] = textPack.getSubimage(97, 49, 8, 10); pixelLength[i] = 7; break;
			case 'X': imgText[i] = textPack.getSubimage(113, 49, 9, 10); pixelLength[i] = 7; break;
			case 'Y': imgText[i] = textPack.getSubimage(129, 49, 9, 10); pixelLength[i] = 7; break;
			case 'Z': imgText[i] = textPack.getSubimage(145, 49, 8, 10); pixelLength[i] = 7; break;
			case '.': imgText[i] = textPack.getSubimage(161, 49, 8, 10); pixelLength[i] = 7; break;
			case '!': imgText[i] = textPack.getSubimage(177, 49, 8, 10); pixelLength[i] = 7; break;
			case ',': imgText[i] = textPack.getSubimage(193, 49, 8, 10); pixelLength[i] = 7; break;
			case ':': imgText[i] = textPack.getSubimage(209, 49, 8, 10); pixelLength[i] = 7; break;
			case ';': imgText[i] = textPack.getSubimage(225, 49, 8, 10); pixelLength[i] = 7; break;
			case '?': imgText[i] = textPack.getSubimage(241, 49, 8, 10); pixelLength[i] = 7; break;
		}
		BufferedImage returnImage = new BufferedImage(xBox, yBox, BufferedImage.TYPE_INT_RGB);
		
		returnImage.createGraphics().drawImage(textBox.getSubimage(0, 0, 3, 3), 0, 0, null);
		returnImage.createGraphics().drawImage(textBox.getSubimage(0, 4, 3, 3), 0, yBox-3, null);
		returnImage.createGraphics().drawImage(textBox.getSubimage(4, 4, 3, 3), xBox-3, yBox-3, null);
		returnImage.createGraphics().drawImage(textBox.getSubimage(4, 0, 3, 3), xBox-3, 0, null);
		
		for (int i = 3; i < (xBox-3); i++){
			returnImage.createGraphics().drawImage(textBox.getSubimage(3, 0, 1, 3), i, 0, null);
			returnImage.createGraphics().drawImage(textBox.getSubimage(3, 4, 1, 3), i, yBox-3, null);
			
		}
		for (int i = 3; i < (yBox-3); i++){
			returnImage.createGraphics().drawImage(textBox.getSubimage(0, 3, 3, 1), 0, i, null);
			returnImage.createGraphics().drawImage(textBox.getSubimage(4, 3, 3, 1), xBox-3, i, null);
		}
		
		returnImage.createGraphics().setColor(Color.yellow);
		returnImage.createGraphics().fillRect(3, 3, xBox-6, yBox-6);
		
		int returnY = 3;
		int returnX = 3;
		//this function concatenates the text image. Currently, it cuts to the next line when the characters reach the end.
		for(int i = 0; i < string.length(); i++){
			int returnXi = returnX + pixelLength[i];
			if(returnXi > (xBox - 3)){
				returnY = (returnY + 10);
				returnX = 3;
			}
			
			if ( returnY > yBox){
				System.out.println("Text was too long: i = " + i);
				return returnImage;
			}
			returnImage.createGraphics().drawImage(imgText[i], returnX, returnY, null);
			returnX = returnX + pixelLength[i];
		}
		
		return returnImage;
		
	}
	
}
