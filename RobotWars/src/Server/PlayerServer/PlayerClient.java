package Server.PlayerServer;


import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import Manager.RobotStatusManager;
import Robot.ORIENTATION;
import Robot.Robot;
import Server.Comunication.SocketClient;
import UI.Menu;
import UI.RobotWindowTest;
import UIController.Controller;
import Usable.Armas.BananaGun;
import Usable.Golpes.Espada;


public class PlayerClient {
	public static void main(String[] args) throws JSONException {
	
		
		Controller controller = Controller.getInstance();
		RobotWindowTest test = new RobotWindowTest(controller);
		Menu menu = new Menu(controller);
		
		controller.setFrame(test);
		
		Robot robot = controller.getRobot();
		
		
		try {
			

			SocketClient clienteJugador = new SocketClient("192.168.18.18", 10000);
			String pos = "";

			while (true) {
				
				
				if (robot == null) {
					robot = controller.getRobot();
				}
				if (robot == null) {
					continue;
				}
				
				String pos2 = robot.getX() + "," + robot.getY();
				int[] coord = controller.getLastBullet();
				if (coord != null) {
					
					String bull = ":";
					bull+=coord[0]+",";
					bull+=coord[1]+",";
					bull+=coord[2];
					
			
		
					pos2+=bull;
					//System.out.println(pos2 +"\n\n");
				}
								
				if (pos != pos2) {
					
					clienteJugador.sendMsg(pos2);
					pos = pos2;
				}

				
			
				
				
				Thread.sleep(6);
			}
			
		} catch (Exception ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
	}

}
