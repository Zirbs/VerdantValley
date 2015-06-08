package gamedata;

import java.awt.Polygon;

public class DirtBlockShort extends Doodad {
	
	public boolean render0;
	public boolean render1;
	public boolean render2;
	public boolean render3;
	
	
	public DirtBlockShort(Tile inTile, int posZ, byte rotation){
		height = 1;
		doodadID = 0;
		this.inTile = inTile;
		this.posZ = posZ;
		
		//System.out.println("posZ of new ShortDirt = " +posZ);
		
		render0 = true;
		render1 = true;
		render2 = true;
		render3 = true;
		this.imgStartX = inTile.imgStartX;
		this.imgStartY = inTile.imgStartY - (((height + posZ - 1) * 6));
		createHLShape();
		hlStartX = imgStartX;
		hlStartY = imgStartY;
		zDepth = inTile.zDepth + zDepthMinDif*posZ;
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
			simpleAlphaQuadRender(0, 0, zDepth, 0, 32, 53, 32);
		}
	}
}
