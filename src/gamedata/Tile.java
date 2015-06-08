package gamedata;

import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Tile extends Doodad {
	public int height;
	public List<Doodad> inTileDoodads;
	BufferedImage[] tilePieces;
	public int tileNum;
	public long occupiedHeights; // 64 bits, might need doubling. 00000000000000000011001001111111111 Ground
	
	
	//A Tile is both a specific section on the map and the base of that section. If there was a cave below the surface, the tile
	//represents the cave floor, with dirt/rock doodads above it.
	
	public Tile(int x, int y, int h, int p){
		doodadID = 1;
		occupiedHeights = 1; //bottommost bit
		posX = x;
		posY = y;
		height = h;
		
		doodadListPosTileRelative = 0; //because the tile doodad is always on the bottom
		
		zDepth = -1F + ((1 + p) * zDepthMinDif); //lower = further away, but -1.0 is too far. float limit = 32 bits
		doodadListPos = p;
		
		this.inTile = this; //Tiles are the only constant on the map. Every doodad belongs to at least one tile
		
		setupImgCoordinates();
		createHLShape();
		inTileDoodads = new ArrayList<Doodad>();
		isSelected = false;
	}
	
	@Override
	public void createHLShape()
	{
		int[] pX = {imgStartX + 0, imgStartX + 34, imgStartX + 52, imgStartX + 18};
		int[] pY = {imgStartY + 18, imgStartY +25, imgStartY +7, imgStartY};
		hlShape = new Polygon(pX, pY, 4);
	}
	
	@Override
	public void renderThisDoodad(){
		if (isSelected){
			simpleAlphaQuadRender(0, 0, zDepth, 0, 12, 53, 26);
			isSelected = false;
		}
		else{
			simpleAlphaQuadRender(0, 0, zDepth, 0, 38, 53, 26);
		}
		
	}
	
	//Really, this code should be in every doodad with an override for different images
	public void setupImgCoordinates() {
		imgStartX = (posX*35)-(posY*19);
		imgStartY = (posX*7)+(posY*19) - 4*height;
	}
	
	public Doodad getLatestDoodad(){
		if (!inTileDoodads.isEmpty()){
			return inTileDoodads.get(inTileDoodads.size() - 1);
		}
		return this;
	}
	
	public boolean addToOccupiedHeights(long newData){
		if ((occupiedHeights & newData)==0){
			occupiedHeights = occupiedHeights | newData;
			return true;
		}
		return false;
	}
	
	public boolean removeFromOccupiedHeights(long newData){
		occupiedHeights = occupiedHeights & (~newData);
		return true;
	}
	
	

	public void addDoodadToTile(int doodadType, int posZ) {
		Doodad addedDoodad = getNewDoodadFromType(doodadType, posZ);
		long newHeight2 = (long) Math.pow(2, posZ);
		occupiedHeights = occupiedHeights | newHeight2;
		inTileDoodads.add(addedDoodad);
	}

	private Doodad getNewDoodadFromType(int doodadType, int locationZ) {
		//needed: a big list of all possible doodads to add, and their associated codes
		return new DirtBlockShort(this, locationZ, (byte) 0);
	}

	//Checks the tile for open height cells at the location, corresponding to the height of the doodadType
	public boolean hasRoomForDoodad(int doodadType, int posZ) {
		//code requires: returning static doodad heights based on "doodadType"
		//height should be a static function dependent on the doodadTileID, in turn dependent on rotation and the tile in question.
		//Oddly shaped doodads, after all, have differing heights.
		long newHeight = (long) Math.pow(2, posZ);
		if ((occupiedHeights | newHeight) != occupiedHeights ){
			return true;
		}
		return false;
	}
	
}
