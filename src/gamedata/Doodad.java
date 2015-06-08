package gamedata;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import primary.Main;

public class Doodad {
	public int doodadID;
	public int height;
	public int posX;
	public int posY;
	public int posZ;

	public int tileWidth = 52;
	public int tileHeight = 23;
	
	private int cameraClipBuffer = 80;
	
	public int doodadListPos;
	public int doodadListPosTileRelative;
	
	public int imgStartX;
	public int imgStartY;
	public int squareSize = 64;
	public float zDepth;
	public float zDepthMinDif = (1F / 65536F); //Determined by try-and-check. Should be redetermined with a better understanding of floats
	
	public Tile inTile;
	public Doodad primaryDoodad;
	
	public int hlStartX;
	public int hlStartY;
	public Shape hlShape;
	
	public boolean isSelected;
	
	public BufferedImage hlImg;
	
	public Doodad(){
		primaryDoodad = this;
	}
	
	public void renderThisDoodad(){
		//Of course, this is special rendering code that needs to be over-ridden in each doodad class.
		//The render code for each doodad depends on a lot of that doodad's variables: decay, current functions, etc
	}
	
	public void createHLShape(){ //Highlight shapes should occupy the same coordinates as their image, obviously
		hlShape = new Polygon();
	}
	
	public void simpleAlphaQuadRender(int xOffset, int yOffset, float zDepth, int sheetOffsetX, int sheetOffsetY, int sizeX, int sizeY){
		// This render method assumes a texture has already been bound.
		// The sheetOffsets are somewhere from 0 to 1.
		float realSheetX1 = (float)sheetOffsetX / (float) squareSize;
		float realSheetY1 = (float)sheetOffsetY / (float) squareSize;
		
		float realSheetX2 = realSheetX1 + (float)sizeX / (float) squareSize;
		float realSheetY2 = realSheetY1 + (float)sizeY / (float) squareSize;
		
		xOffset = xOffset - Main.getMap().mapModX;
		yOffset = yOffset + Main.getMap().mapModY;
		
		glBegin(GL_QUADS);
			glTexCoord2f(realSheetX1, realSheetY1); glVertex3f(imgStartX + xOffset,imgStartY + yOffset, zDepth); //bottom left
			glTexCoord2f(realSheetX1, realSheetY2); glVertex3f(imgStartX + xOffset,imgStartY + yOffset+sizeY, zDepth); //bottom right
			glTexCoord2f(realSheetX2, realSheetY2); glVertex3f(imgStartX + xOffset+sizeX,imgStartY + yOffset+sizeY, zDepth); //upper right
			glTexCoord2f(realSheetX2, realSheetY1); glVertex3f(imgStartX + xOffset+sizeX,imgStartY + yOffset, zDepth); //upper left	
		glEnd();
		
	}
	
	public boolean isOnScreen(int mapModX, int mapModY){
		if((imgStartX - mapModX + cameraClipBuffer > 0) && (imgStartX - mapModX < Main.getWindowWidth())){
			if((imgStartY + cameraClipBuffer + mapModY > 0) && (imgStartY + mapModY < Main.getWindowHeight())){
				return true;
			}
		}
		return false;
	}
	
	public void addDoodad(int doodadType, int posZ){
		//requires expanding for multi-tile doodads
		if (inTile.hasRoomForDoodad(doodadType, posZ)){
			inTile.addDoodadToTile(doodadType, posZ);
			Main.getMap().addDoodadToRenderListsFromListPos(inTile.getLatestDoodad(), 0);
		}
	}

	public void setIsSelected(){
		//height++;
		isSelected = true;
		//Feeling rigorous. I think there should be some other exceptions here later
	}
	
	public Shape getShape() {
		int[] xS = {20,20, 100, 100};
		int[] yS = {20, 100, 100, 20};
		return new Polygon(xS, yS, 4);
		
	};

}
