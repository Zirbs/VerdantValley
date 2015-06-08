package primary;

import gamedata.Doodad;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import primary.Main;

/*	The Mouse Checker analyzes both what the mouse is currently over 
 * (the top-most rendered item) and its position relative to the 
 * screen, for moving around. It will eventually be tied to ticks
 * 
 * 
 */
public class MouseChecker {
	
	
	public static int mouseX; //these are the locations of the mouse relative to the canvas. used for GUI checks, scrolling, etc.
	public static int mouseY;
	public static boolean mouseButtonLeft;
	
	
	public static int mapMouseX; //these are the locations of the mouse relative to the map. Used for highlighting and map interaction
	public static int mapMouseY;
	
	public static boolean click = false;
	public static boolean overButton; //this is to cancel out any actions like highlighting the block behind the button.
	public boolean holding = false;
	public int tickDelay = 0;
	public int pressedButton = 1;
	
	public static int slowScrollSpeed = 1;
	public static int fastScrollSpeed = 3;
	public static boolean disableScroll = true;
	public static Doodad cursorDoodad = new Doodad();
	
	public static ArrayList<Doodad> potentialCursorDoodads = new ArrayList<Doodad>();
	public static ArrayList<Doodad> dragCursorDoodads = new ArrayList<Doodad>();
	
	public static int mapModXLimitPos = 2100;
	public static int mapModXLimitNeg = 1900;
	public static int mapModYLimitPos = 200;
	public static int mapModYLimitNeg = 1900;
	
	public static void tick() {
		int mapModX = Main.game.map.mapModX;
		int mapModY = Main.game.map.mapModY;
		
		mapMouseX = (mapModX + mapModXLimitNeg) +mouseX; 	//non-negative coordinates of the whole map
		mapMouseY = (mapModY + mapModYLimitNeg) + mouseY;	//
		
		inputMouseCoordinates();
		checkScrolling(mapModX, mapModY);
		if(checkHighlightingDoodad()){
			cursorDoodad.setIsSelected();
			
			if(mouseButtonLeft){
				click = true;
				if (!dragCursorDoodads.contains(cursorDoodad)){
					dragCursorDoodads.add(cursorDoodad);
				}
				for (int i = 0; i < dragCursorDoodads.size(); i++){
					dragCursorDoodads.get(i).setIsSelected();
				}
			}
			
			if(!mouseButtonLeft && click){
				//System.out.println("doodadAdd try");
				for (int i = 0; i < dragCursorDoodads.size(); i++){
					dragCursorDoodads.get(i).addDoodad(23, dragCursorDoodads.get(i).posZ + 1);
				}
				click = false;
				dragCursorDoodads.clear();
			}
		}
		else if(checkHighlightingButton()){
		}
	}

	private static void checkScrolling(int mapModX, int mapModY) {
		
		//moveLeft
		if(mouseX <= 90 && mapModX > -mapModXLimitNeg && mouseX >= 0){
			if(mouseX <= 40) mapModX = mapModX -fastScrollSpeed;
			else mapModX = mapModX -slowScrollSpeed;
		}
		//moveRight
		if(mouseX >= (Main.getWindowWidth() - 90) && mapModX < mapModXLimitPos && mouseX <= Main.getWindowWidth()){
			if(mouseX >= (Main.getWindowWidth() - 40)) mapModX = mapModX + fastScrollSpeed;
			else mapModX = mapModX +slowScrollSpeed;
		}
		//moveUp
		if(mouseY >= 90 && mapModY < mapModYLimitPos && mouseY >= 0){
			if(mouseY >= 40) mapModY = mapModY +fastScrollSpeed;
			else mapModY = mapModY +slowScrollSpeed;
		}
		//moveDown
		if(mouseY <= (Main.getWindowHeight() - 90) && mapModY > -mapModYLimitNeg && mouseY <= Main.getWindowWidth()){
			if(mouseY <= (Main.getWindowHeight() - 40)) mapModY = mapModY - fastScrollSpeed;
			else mapModY = mapModY -slowScrollSpeed;
		}
		
		Main.getMap().mapModX = (mapModX <= -mapModXLimitNeg) ? -mapModXLimitNeg: mapModX;
		Main.getMap().mapModY = (mapModY <= -mapModYLimitNeg) ? -mapModYLimitNeg: mapModY;
	}
	
	private static boolean checkHighlightingDoodad() {
		potentialCursorDoodads.clear();
		for (int i =  0; i < Main.game.map.doodads.size() ; i++){
			Doodad checkDoodad = Main.game.map.doodads.get(i);
			//System.out.println("newDoodadRelativeListPos = " + newDoodadRelativeListPos);
			if(checkDoodad.hlShape.contains((double) mapMouseX - mapModXLimitNeg, (double)  Main.HEIGHT - (mapMouseY - mapModYLimitNeg))){
				potentialCursorDoodads.add(checkDoodad);
				//System.out.println("PotentialCursorAdded " + checkDoodad.zDepth);
			}
		}
		if (!potentialCursorDoodads.isEmpty()){
			float k = -1;
			for ( int j = 0; j < potentialCursorDoodads.size(); j++){
				if (potentialCursorDoodads.get(j).zDepth > k){
					k = potentialCursorDoodads.get(j).zDepth;
					cursorDoodad = potentialCursorDoodads.get(j);
				}
			}
			//System.out.println("return true");
			return true;
		}
		return false;
		//Don't forget that in certain situations, no tiles at all are highlighted.
	}

	private static boolean checkHighlightingButton() {
		// Assuming the pointer's not on a tile
		return true;
	}

	private static void inputMouseCoordinates() {
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		mouseButtonLeft = Mouse.isButtonDown(0);
	}

	public void stopScroll(){
		disableScroll = true;
	}

	public void notifyClick(){
		
		
	}
	
	
	//Analyzes Mouse position and returns new mapModX
	public static int mapEdgeListenerX(int mapModX) {
		
		//moveLeft
		if(mouseX <= 90 && mapModX < 1800 && mouseX >= 0){
			if(mouseX <= 40){
				return (mapModX +fastScrollSpeed);
			}
			return (mapModX +slowScrollSpeed);
		}
		//moveRight
				return(mapModX -fastScrollSpeed);
	}
	
	public static int mapEdgeListenerY(int mapModY) {
		
		//moveUp
		if(mouseY <= 90 && mapModY < 500 && mouseY >= 0 && !disableScroll){
			if(mouseY <= 40){
				return (mapModY +fastScrollSpeed);
			}
			return (mapModY +slowScrollSpeed);
		}
		//moveDown
				return(mapModY -fastScrollSpeed);
	}

	public void notifyPressed() {
		click = true;
		holding = true;
	}

	public void notifyReleased() {
		holding = false;
		
	}



	public void setPressedButton(int button) {
		this.pressedButton = button;
		
	}

}
