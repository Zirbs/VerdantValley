package gamedata;

public class DummyDoodad extends Doodad {
	//dummy doodads do not render or tick, but when referenced, they refer back to their "primary" doodad
	int relativeNumber;
	
	public DummyDoodad(int relativeNumber, Tile inTile, Doodad primaryDoodad, int height){
		this.inTile = inTile;
		this.primaryDoodad = primaryDoodad;
		this.relativeNumber = relativeNumber;
		this.height = height;
	}

}
