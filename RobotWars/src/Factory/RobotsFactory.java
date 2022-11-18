package Factory;

import java.util.ArrayList;

import Robot.Robot;


public class RobotsFactory {
	
	private static ArrayList<String> skins = new ArrayList<String>();
	
	public static Robot Robot(int pINDEX) {
		Robot robot = new Robot();
		if (pINDEX >= 0 && pINDEX < skins.size()) {
			robot.setImagenes(null);
		}
		
		return robot;
	}

}