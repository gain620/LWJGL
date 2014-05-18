package Examples;

import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class InputDemo {
	
	private List<Box> shapes = new ArrayList<Box>(16);
	private volatile boolean randomColorCooldown = false;
	
	public final static int WIDTH = 640;
	public final static int HEIGHT = 480;

	public InputDemo() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Input Demo");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		shapes.add(new Box(15,15));
		shapes.add(new Box(100,100));

		// Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		while (!Display.isCloseRequested()) {
			// Render
			glClear(GL_COLOR_BUFFER_BIT);

			while(Keyboard.next()) {
				if(Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()){
					shapes.add(new Box(15,15));
				}
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Display.destroy();
				System.exit(0);
			}

			
			for (Box box : shapes) {
				if(Mouse.isButtonDown(0)&&box.inBounds(Mouse.getX(), HEIGHT - Mouse.getY())){
					box.selected = true;
				}
				
				if(Mouse.isButtonDown(2) && box.inBounds(Mouse.getX(), HEIGHT - Mouse.getY())){
					box.randomizeColors();
					
					new Thread(new Runnable() {
						
						public void run() {
							try{
								Thread.sleep(200);
							}catch(InterruptedException e) {
								e.printStackTrace();
							}finally{
								randomColorCooldown = false;
							}
						}
					}).run();
				}
					
				
				
				if(Mouse.isButtonDown(1)){
					box.selected = false;
				}
				
				if(box.selected) {
					box.update(Mouse.getDX(), -Mouse.getDY());
				}
				box.draw();
			}
			

			// ~ Mouse Test ~
			/*int dx = Mouse.getDX();
			int dy = -Mouse.getDY();

			int mouseY = HEIGHT - Mouse.getY();

			System.out.println("(" + dx + "," + dy + ")");
			*/

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	private static class Box {
		public int x, y;
		public boolean selected = false;
		private float colorRed, colorBlue, colorGreen;

		Box(int x, int y) {
			this.x = x;
			this.y = y;

			Random randomGenerator = new Random();
			colorRed = randomGenerator.nextFloat();
			colorBlue = randomGenerator.nextFloat();
			colorGreen = randomGenerator.nextFloat();
		}

		boolean inBounds(int mouseX, int mouseY) {
			if (mouseX > x && mouseX < x + 50 && mouseY >y && mouseY < y+50)
				return true;
			else 
				return false;
		}

		void update(int dx, int dy) {
			x += dx;
			y += dy;
		}

		void randomizeColors() {
			Random randomGenerator = new Random();
			colorRed = randomGenerator.nextFloat();
			colorBlue = randomGenerator.nextFloat();
			colorGreen = randomGenerator.nextFloat();

		}

		void draw() {
			glColor3f(colorRed,colorBlue,colorGreen);
			
			glBegin(GL_QUADS);
			   glVertex2f(x, y);
			   glVertex2f(x + 50, y);
			   glVertex2f(x + 50, y + 50);
			   glVertex2f(x, y + 50);
			glEnd();
		}

	}

	public static void main(String[] args) {
		new InputDemo();

	}
}