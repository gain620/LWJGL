package Examples;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class GamePadDemo {

	static Controller controller;

	public static void main(String[] args) {
		try {
			Controllers.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Controllers.poll();

		for (int i = 0; i < Controllers.getControllerCount(); i++) {
			controller = Controllers.getController(i);

			System.out.println(i + "번째 컨트롤러 : " + controller.getName());
		}
	}

}