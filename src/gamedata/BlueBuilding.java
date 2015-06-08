package gamedata;

import java.awt.Polygon;

public class BlueBuilding extends Doodad {
	
	public BlueBuilding(Tile inTile, int posZ, byte rotation){
		height = 8;
		doodadID = 4;
		this.inTile = inTile;
		this.posZ = posZ;
		
		this.imgStartX = inTile.imgStartX - (3 * tileWidth);
		this.imgStartY = inTile.imgStartY - (((height + posZ - 1) * 6) + 2*tileHeight);
		createHLShape();
		hlStartX = imgStartX;
		hlStartY = imgStartY;
		zDepth = inTile.zDepth + zDepthMinDif*posZ;
		
		//add Dummy Doodads to fill space nearby
		
		
		
	}
	
	@Override
	public void createHLShape()
	{
		int[] pX = {imgStartX + 0, imgStartX + 34, imgStartX + 52, imgStartX + 18};
		int[] pY = {imgStartY + 18, imgStartY +26, imgStartY +7, imgStartY };
		hlShape = new Polygon(pX, pY, 4);
	}
	
	@Override
	public void renderThisDoodad(){
		if (isSelected){
			simpleAlphaQuadRender(0, 0, zDepth, 0, 0, 53, 32);
			isSelected = false;
		}
		else{
			simpleAlphaQuadRender(0, 0, zDepth, 0, 0, 53, 32);
		}
	}
}
