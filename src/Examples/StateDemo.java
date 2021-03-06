package Examples;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class StateDemo {
	
	private static enum State {
		INTRO, MAIN_MENU, GAME;
	}
	
	private State state = State.INTRO;
	
	public final static int HEIGHT = 640;
	public final static int WIDTH = 480;

	public StateDemo() {
		try {
			Display.setDisplayMode(new DisplayMode(HEIGHT, WIDTH));
			Display.setTitle("State Demo Test");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		// Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, HEIGHT, WIDTH, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		while (!Display.isCloseRequested()) {
			// Render
			
			checkInput();
			glClear(GL_COLOR_BUFFER_BIT);
			
			render();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}
	
	private void render(){
		switch(state){
		case INTRO:
			glColor3f(1.0f, 0f, 0f);
			glRectf(0,0,HEIGHT,WIDTH);
			break;
		case GAME:
			glColor3f(0f, 1.0f, 0f);
			glRectf(0,0,HEIGHT,WIDTH);
			break;
		case MAIN_MENU:
			glColor3f(0f, 0f, 1.0f);
			glRectf(0,0,HEIGHT,WIDTH);
			break;
		}
	}
	
	private void checkInput(){
		switch(state){
		case INTRO:
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				state = State.MAIN_MENU;
			}
			break;
		case GAME:
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
				System.exit(0);
			}
			break;
		case MAIN_MENU:
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
				state = State.GAME;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_1)){
				state = State.INTRO;
			}
			break;
		}
	}

	public static void main(String[] args) {
		new StateDemo();

	}
}