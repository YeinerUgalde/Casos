package Main;

import UI.Menu;
import UI.RobotWindowTest;

import Manager.RobotStatusManager;
import UIController.Controller;
import UIController.SController;
import Usable.Armas.BananaGun;
import Usable.Golpes.Espada;

import java.util.ArrayList;
import java.util.HashMap;

import Robot.MOVEMENT;
import Robot.Robot;
import Robot.Weapon;
import Factory.WeaponFactory;
import JSON.JSONDeserializing;
import JSON.JSONSerializing;
import Robot.ORIENTATION;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main1 implements SController{

	public static void main(String[] args) throws JSONException, InterruptedException {
		/*
		Robot robot = new Robot();
		String ruta = "../RobotWars/src/Imagenes/Robots/RobotRojo.png";
		String ruta2 = "../RobotWars/src/Imagenes/Robots/RobotRojoD.png";
		
	
		
		
		robot.setImagenes(ruta);
		robot.setImagenes(ruta2);
		
		
		//Weapon arma =	WeaponFactory.Weapon(WeaponFactory.WEAPONS.BananaGun); 
		BananaGun arma = new BananaGun();
		Espada espada = new Espada();
		
		
		robot.addWeapon(arma);
		robot.addStrike(espada);
		
		*/
		
		
		//Controller controller = new Controller();
		
		RobotWindowTest test = new RobotWindowTest(controller);
		Menu menu = new Menu(controller);
		
		controller.setFrame(test);
		
		//HashMap<String, String> data = new HashMap<String, String>();
		
		
		//{"StrikePosX":1,"StrikePosY":2,"Bullets":[[1,2,0],[1,2,0]],"RobotPosY":100,"RobotPosX":100}
		
		
		
		String pos2 = 100+ "," + 100;
		ArrayList<int[]> bullets = new ArrayList<int[]>();
		int coord[] = {1,2,0}; 
		bullets.add(coord);
		bullets.add(coord);
		
		if (bullets != null) {
			String bull = ":";
			for(int i = 0; i < bullets.size(); i++) {
				int[] coord1 = bullets.get(i);
				bull+=coord1[0]+",";
				bull+=coord1[1]+",";
				if(i+1 < bullets.size()) {
					bull+=coord1[2]+";";
				}else {
					bull+=coord1[2];
				}
				
			}
			pos2+=bull;
		}
		JSONSerializing json = new JSONSerializing();
		
		JSONObject object = json.parseJSON(pos2);
		
		
		/*
		
		int a [] = {100,100};
		int b [] = {};
		int c [] = {1,2,0};
		ArrayList<int[]> prueba = new ArrayList<int[]> ();
		prueba.add(c);
		prueba.add(c);
	

		JSONObject jsonOB = json.Serializate(a, b , prueba);*/
		
		Thread.sleep(5000);
		
		JSONDeserializing des = new JSONDeserializing();
		
	
		
		des.Deserializing(object);
		
		//des.Deserializing(jsonOB);
		
		
		
		
		
		
		//controller.setRobot(robot);
		
		//espada.setController(controller);
		
		//RobotStatusManager manager = new RobotStatusManager();
		//manager.addRobot(robot);
		
		//controller.setManager(manager);
		
		
	
		
		
		
		
	}

}
