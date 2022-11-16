package Manager;
import Robot.Robot;
import UIController.Controller;
public class RobotStatusManager {
	private int robotIndex;
	private Robot robots[];
	private Controller controller;
	
	public RobotStatusManager() {
		this.robots = new Robot[2];
		this.robotIndex = 0;
	}
	
	public void addRobot(Robot pRobot) {
		robots[robotIndex] = pRobot;
		System.out.println(robots[robotIndex].getY());
		robotIndex++;
	}
	
	public boolean evaluateHitBox(int pID, int pos[]) {
		for (Robot robot: robots) {
			if (robot == null)continue;
			//System.out.println("pos: "+ pos[0] +  " " + pos[1] + " robot: " + robot.getX() + " " +robot.getY());
			if(pos[0] >= robot.getX() && pos[0] <= robot.getX()+180 && pos[1] >= robot.getY() && pos[1] <= robot.getY()+180) {
				System.out.println("Se bajo la vida del robot");
				controller.reportOverBullet(pID);
				return true;
			}
		}
		return false;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
