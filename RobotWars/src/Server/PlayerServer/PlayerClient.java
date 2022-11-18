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
	public static String getBullets(ArrayList<int[]> bullets) {
		if (bullets == null) {
			return "";
		}
		String bull = ":";
		for (int i = 0; i < bullets.size(); i++) {
			int[] coord = bullets.get(i);
			bull += coord[0]+",";
			bull += coord[1]+",";
			if(i+1 >= bullets.size()) {
				bull += coord[2];
			}else {
				bull += coord[2]+";";	
			}			
		}

		return bull;
	}
	
	public static void main(String[] args) throws JSONException {
	
		
		Controller controller = Controller.getInstance();
		RobotWindowTest test = new RobotWindowTest(controller);
		Menu menu = new Menu(controller);
		
		controller.setFrame(test);
		
		Robot robot = controller.getRobot();
		String pos = "";
	
		
		int fps = 0;
		long obtener_tiempo;
		long obtener_tiempo_despues = 0;
		
		try {
			

			SocketClient clienteJugador = new SocketClient("192.168.18.18", 10000);
			controller.setCliente(clienteJugador);
			

			while(true) {
				if (robot == null) {
					robot = controller.getRobot();
					continue;
				}

				else {

					break;
				}
			}
		
			while (true) {
				
				obtener_tiempo = System.nanoTime();
				
				
				
			
				clienteJugador.sendMsg(robot.getCoords());
				
				
				
			
				obtener_tiempo_despues = System.nanoTime();
								
	           
				fps = (int) (obtener_tiempo_despues-obtener_tiempo)/1000;
				
				/*if(fps > 60) {
					System.out.println("Altos "+fps);
				}
				else if(fps > 50 && fps < 61) {
					System.out.println(fps);
				}
				else if(fps > 40 && fps < 50) {
					System.out.println("Bajos fps "+fps);
				}
				else if( fps < 40) {
					System.out.println("Muy Bajos fps "+fps);
				}*/
				
				Thread.sleep(4);
				
			}
			
		} catch (Exception ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
	}

}
