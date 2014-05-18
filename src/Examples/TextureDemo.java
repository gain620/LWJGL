package Examples;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureDemo {
	
	public final static int HEIGHT = 640;
	public final static int WIDTH = 480;
	
	private Texture wood;

	public TextureDemo() {
		try {
			Display.setDisplayMode(new DisplayMode(HEIGHT, WIDTH));
			Display.setTitle("Texture Demo");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		try {
			Texture texture = TextureLoader.getTexture("JPG", new FileInputStream(new File("res/texture/wood.jpg")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		wood = loadTexture("wood");

		// Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, HEIGHT, WIDTH, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		while (!Display.isCloseRequested()) {
			// Render
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			
			
			glBegin(GL_QUADS);
			   glTexCoord2f(0, 0);
			   glVertex2i(400,400); // upper-left
			   glTexCoord2f(1, 0);
			   glVertex2i(450,400); // upper-right
			   glTexCoord2f(1, 1);
			   glVertex2i(450,450); // bottom-right
			   glTexCoord2f(0, 1);
			   glVertex2i(400,450); // bottom-left
			glEnd();

			glBegin(GL_LINES);
			   glVertex2i(100, 100);
			   glVertex2i(200, 200);
			glEnd();
			

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}
	
	private Texture loadTexture(String key) {
		try {
			return TextureLoader.getTexture("JPG", new FileInputStream(new File("res/texture/"+key+".jpg")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		new TextureDemo();

	}
}