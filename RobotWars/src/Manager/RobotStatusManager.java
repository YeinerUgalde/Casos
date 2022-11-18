package Manager;
import java.util.ArrayList;

import Constants.IConstants;
import Robot.Robot;
import UIController.Controller;
public class RobotStatusManager implements IConstants {
	private int robotIndex;
	private Robot robots[];
	private Controller controller;
	private ArrayList<int[]> entidades;
	
	public RobotStatusManager() {
		this.robots = new Robot[2];
		this.entidades = new ArrayList<int[]>();
		this.robotIndex = 0;
	}
	
	public void addRobot(Robot pRobot) {
		robots[robotIndex] = pRobot;
		System.out.println(robots[robotIndex].getY());
		robotIndex++;
	}
	
	public void addEntidad(int[] pEntidad) {
		this.entidades.add(pEntidad);
	}
	
	public void reportHit(int pEnergy) {
		controller.reportHitMulti(pEnergy);
	}
	
	public boolean evaluateHitBox(int pID, int pos[]) {
		int i = 0;
		for (Robot robot: robots) {
			if (robot == null)continue;
			//System.out.println("pos: "+ pos[0] +  " " + pos[1] + " robot: " + robot.getX() + " " +robot.getY());
			if(pos[0] >= robot.getX() && pos[0] <= robot.getX()+180 && pos[1] >= robot.getY() && pos[1] <= robot.getY()+180) {
				System.out.println("Se bajo la vida del robot");
				if(i==0) {
					if(robot.getEnergy()-DISMINUCION_DE_ENERIA >= 0) {
						robot.setEnergy(robot.getEnergy()-DISMINUCION_DE_ENERIA);
					}					
					controller.reportEnergyFrame(i, robot.getEnergy());
					this.reportHit(robot.getEnergy());
				}
				controller.reportOverBullet(pID);
				return true;
			}
			i++;
		}
		return false;
	}
	
	
	public boolean evaluateHitBoxEntidades(int x, int y) {
		
		for(int[] entidad: entidades) {
			//if(entidad[0] >= x && entidad[0] <= x+180 && entidad[1] >= y && entidad[1] <= y +180) {
			
			int entidadRectangle[] = {entidad[0],entidad[1],entidad[0]+100,entidad[1]+100};
			int robotRectangle[] = {x,y,x+180,y+180};
			
			if(entidadRectangle[0] <= robotRectangle[2] && entidadRectangle[2] >= robotRectangle[0] && entidadRectangle[1] <= robotRectangle[3] && entidadRectangle[3] >= robotRectangle[1]) {
				//System.out.println("En trampa");
				
				
				if(controller.getEnergyRobot()-DISMINUCION_DE_ENERIA >= 0) {
					controller.reportEnergyRobot(controller.getEnergyRobot()-DISMINUCION_DE_ENERIA);
				}	
				controller.reportEnergyFrame(0,controller.getEnergyRobot());
				this.reportHit(controller.getEnergyRobot());
				return true;
			}
		}
			
		
		return false;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
