package Server;

import java.util.ArrayList;

import UIController.Controller;

public class DataManager {
	private static DataManager data;
	
	private Controller controller;
	private ArrayList<int[]> bullets;
	private int robotPos[];
	private int strikePos[];
	
	public DataManager() {
		this.controller = Controller.getInstance();
	}
	
	public void run() {
		if (robotPos != null) {
			controller.reportCoords2(robotPos[0], robotPos[1]);
			robotPos = null;
		}
		if (strikePos != null) {
			//EVALUATE HIT BOX
			System.out.println("STrike");
			strikePos = null;
		}
		if (bullets != null) {
			for (int bala[] : bullets) {
				if (bala.length < 3)continue;
				controller.addBulletFrame(bala[0], bala[1], "../RobotWars/src/Imagenes/balas/Bala.png");
				controller.reportBullet(bala[0], bala[1], bala[2]);
			}
			bullets = null;
		}
		
	}
	
	
	
	public void setBullets(ArrayList<int[]> bullets) {
		this.bullets = bullets;
	}

	public void setRobotPos(int[] robotPos) {
		this.robotPos = robotPos;
	}

	public void setStrikePos(int[] strikePos) {
		this.strikePos = strikePos;
	}
	
	

	
	public static DataManager getInstance() {
		if (data == null) {
			data = new DataManager();
		}
		return data;
	}
}
