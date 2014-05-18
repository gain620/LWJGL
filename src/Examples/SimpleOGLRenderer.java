package Examples;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class SimpleOGLRenderer {
	
	public final static int HEIGHT = 640;
	public final static int WIDTH = 480;

	public SimpleOGLRenderer() {
		try {
			Display.setDisplayMode(new DisplayMode(HEIGHT, WIDTH));
			Display.setTitle("Hello, LWJGL!");
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
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBegin(GL_QUADS);
			   glVertex2i(400,400); // upper-left
			   glVertex2i(450,400); // upper-right
			   glVertex2i(450,450); // bottom-right
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

	public static void main(String[] args) {
		new SimpleOGLRenderer();

	}
}