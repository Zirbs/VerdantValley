package primary;

import gamedata.Map;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

/*
 * Erry'body
 * All dis here codin' be mine
 * -Connor Zirbel
 * 
 * V .011 -- From Scratch
 * 
 */


public class Main {
		//Fuck you Jackson
	static int WIDTH = 1200;
	static int HEIGHT = 900;
	static int quickTime;
	static long lastFPS;
	static int fps;
	static long time;
	
	public static Game game;
	
	public static void main(String[] args){
		initDisplay();
		initGL();
		initGame();
		gameLoop();
		
		cleanUp();
	}

	private static void gameLoop() {
		while(!Display.isCloseRequested()){
			
			takeInputs();
			tick();
			render();
			updateFPS();
		}
		
	}

	private static void takeInputs() {
		game.getInput();
		
	}

	private static void tick() {
		game.tick();
		
	}

	private static void render() {
		//glClearDepth(1.0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//glLoadIdentity();
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		game.render();
		
		Display.update();
		Display.sync(60);
		
		quickTime++;
		if(quickTime >= 60){
			quickTime = 0;
			System.out.println("A second has passed");
			
			
			//System.out.println("cursorDoodad =" + MouseChecker.cursorDoodad.posX + MouseChecker.cursorDoodad.posY);
		}

	}	

	private static void cleanUp() {
		Display.destroy();
		Keyboard.destroy();
		
	}

	private static void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 0, 1); //maybe backwards?
		
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
		glAlphaFunc(GL_GREATER, 0.5f);
		glEnable(GL_ALPHA_TEST);// I have been informed this only works for 0 - 1 alpha
		glDepthFunc(GL_LESS);
		glEnable(GL_TEXTURE_2D);	
		glEnable(GL_BLEND);
		
		
		
	}

	private static void initDisplay() {
			try {
				Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
				Display.create();
				Keyboard.create();
				Display.setVSyncEnabled(true);
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	private static void initGame() {
		game = new Game(new Map());
		for (int i = 0; i < getMap().tiles.length; i++){
			getMap().tiles[i].addDoodad(23, getMap().tiles[i].posZ + 1);
			System.out.println("build % = " + (100*i)/ getMap().tiles.length);
		}
		lastFPS = getTime();
	}
	
	public static void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle("FPS: " + fps); 
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}

	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static int getWindowHeight() {
		return HEIGHT;
	}

	public static int getWindowWidth() {
		return WIDTH;
	}
	
	public static Map getMap() {
		return game.map;
	}
}
