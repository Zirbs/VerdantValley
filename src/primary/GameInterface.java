package primary;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;
import gamedata.ImageLibrary;

public class GameInterface {
	//Basically the menu bar
	
	int buttonsClickedVisualBooleans;
	int squareSize = 2048;
	
	int menuBarHeight = 120;
	public void render(){
		
		glBindTexture(GL_TEXTURE_2D, ImageLibrary.hudbase.getTextureID());
	simpleAlphaQuadRender(0, Main.HEIGHT - menuBarHeight, -.0001F, 0, squareSize - menuBarHeight, Main.WIDTH, menuBarHeight);
		
		//makeGLButton(1, 200);
	}

	public void simpleAlphaQuadRender(int xOffset, int yOffset, float zDepth, int sheetOffsetX, int sheetOffsetY, int sizeX, int sizeY){
		// This render method assumes a texture has already been bound.
		// The sheetOffsets are somewhere from 0 to 1.
		float realSheetX1 = (float)sheetOffsetX / (float) squareSize;
		float realSheetY1 = (float)sheetOffsetY / (float) squareSize;
		
		float realSheetX2 = realSheetX1 + (float)sizeX / (float) squareSize;
		float realSheetY2 = realSheetY1 + (float)sizeY / (float) squareSize;
		
		glBegin(GL_QUADS);
			glTexCoord2f(realSheetX1, realSheetY1); glVertex3f(xOffset,yOffset, zDepth); //bottom left
			glTexCoord2f(realSheetX1, realSheetY2); glVertex3f(xOffset,yOffset+sizeY, zDepth); //bottom right
			glTexCoord2f(realSheetX2, realSheetY2); glVertex3f(xOffset+sizeX,yOffset+sizeY, zDepth); //upper right
			glTexCoord2f(realSheetX2, realSheetY1); glVertex3f(xOffset+sizeX,yOffset, zDepth); //upper left	
		glEnd();
		
	}
}
