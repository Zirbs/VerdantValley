package gamedata;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

public class Map {
	
	public int mapModX;
	public int mapModY;
	public int sizeX;
	public int sizeY;
	public Tile[] tiles;
	public boolean drawHighlight = true;
	private static int numDoodadTypes;
	
	private int currentTexture;
	
	public List<Doodad> doodads; //doodads are any rendered objects, but without the confusion of java Objects and in-game objects. No relation to Starcraft's "doodad" terrain pieces. These are ordered back to front, so the last item is rendered on top.
	public List<Shape> doodadShapes; //doodadShapes is ordered front to back: the first visible doodad is therefore the first selectable doodad.
	//RL = Render list
	public ArrayList<Doodad> dirtBlocksRL = new ArrayList<Doodad>();
	public ArrayList<Doodad> bedrockTilesRL;
	public ArrayList<Doodad> blueBuildingRL;
	public ArrayList<Doodad> machine1RL;
	public ArrayList<Doodad> machine2RL;
	
	
	
	public Map(){
		//this is a cheap map generator
		sizeX = 100;
		sizeY = 200;
		mapModX = 0;
		mapModY = 0;
		numDoodadTypes = 4; //for laymen, the actual number of types is 1 + this many
		
	
		bedrockTilesRL = new ArrayList<Doodad>(); //gah, I hate initializing each ArrayList like this.
		System.out.println("BEDROCKlIST = " + bedrockTilesRL);
		doodads = new ArrayList<Doodad>();
		tiles = new Tile[sizeX*sizeY];
		
		for(int h = 0; h < sizeX*sizeY; h++){
				tiles[h] = new Tile((h%sizeX), (h/sizeX), 0, h); // 7 down, 35 over or 19 down, 19 back	
				addDoodadToRenderListsFromListPos(tiles[h], h);
		}		
	}
	
	public void addDoodadToRenderListsFromListPos (Doodad doodad, int pos){
		doodads.add(pos, doodad);
		for (int i = (pos+1); i < doodads.size(); i++){
			doodads.get(i).doodadListPos++;
		}
		getDoodadListAndSetTextureFromID(doodad.doodadID).add(doodad);
	}
	
	//Sets proper textures for render and returns the render list for that texture sheet.
	private ArrayList<Doodad> getDoodadListAndSetTextureFromID(int objectType) {	
		switch(objectType){
			case 0: currentTexture = ImageLibrary.dirtBlock1.getTextureID(); return dirtBlocksRL;
			case 1: currentTexture = ImageLibrary.bedRock.getTextureID(); return bedrockTilesRL;
			case 2: currentTexture = ImageLibrary.blastFurnace.getTextureID(); return machine1RL;
			case 3: currentTexture = ImageLibrary.blastFurnace.getTextureID(); return machine2RL;
			case 4: currentTexture = ImageLibrary.blueBuilding.getTextureID(); return blueBuildingRL;
			default: System.out.println("DoodadList fail: improper ID");
		}
		System.out.println("DoodadList fail: null return");
		return null;
	}
	
	
	//In game Map Renderer
	//Super complex, yo.
	public void render(){
		for(int i = 0; i < numDoodadTypes; i++ ){
			ArrayList<Doodad> currentList = getDoodadListAndSetTextureFromID(i);
			if (currentList != null){
				glBindTexture(GL_TEXTURE_2D, currentTexture);
				for(int j = 0; j < currentList.size(); j++){
					if (currentList.get(j).isOnScreen(mapModX, mapModY)){
						currentList.get(j).renderThisDoodad();
					}
					
				}
			}
		}
	}

	public int getSize() {
		return (sizeX*sizeY);
	}
}

