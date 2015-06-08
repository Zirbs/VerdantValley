package primary;

import gamedata.Map;

public class Game {
	//May be replaced, not sure of usefulness
	Map map;
	int mapSize;
	GameInterface gi;
	public Game(Map map){
		this.map = map;
		this.mapSize = map.getSize();
		gi = new GameInterface();
		
	}
	
	public void getInput(){
		MouseChecker.tick();
	}
	
	public void tick(){
		
	}
	
	public void render(){
		map.render();
		gi.render();
		
		//render HUD and cursor
	}
	

}
