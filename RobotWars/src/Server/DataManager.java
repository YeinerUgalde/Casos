package Server;

import java.util.ArrayList;

import UIController.Controller;

public class DataManager {
	private static DataManager data;
	
	private Controller controller;
	private ArrayList<int[]> bullets;
	private ArrayList<int[]> trampas;
	private int robotPos[];
	private int strikePos[];
	private String energy;
	private Boolean segundoJugador = null;
	
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
				controller.addBulletFrame(bala[0], bala[1], "C:/Imagenes/balas/Bala.png");
				controller.reportMultiBullet(bala[0], bala[1], bala[2]);
			}
			bullets.clear();
			bullets = null;
		}
		
		if (trampas != null) {
			if(trampas.size()>0) {
				//ntroller.setTrampasCoords(trampas);
	
				controller.addTrampasFrame(trampas);
							
			}
			trampas.clear();
			trampas = null;
			
		}
		
		if (energy != null) {
			if(energy.length()>0) {
							
				controller.reportEnergyFrame(1, Integer.parseInt(energy));
							
			}
			energy = null;
			
		}
		
		if(segundoJugador != null) {
			if(segundoJugador != false) {
				controller.setSegundoJugador();
			}
		}
		
	}
	
	public void setSegundoJugador(Boolean pSegundo) {
		this.segundoJugador = pSegundo;
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
	
	public void setTrampas(ArrayList<int[]> trampas) {
		this.trampas = trampas;
	}

	public static DataManager getInstance() {
		if (data == null) {
			data = new DataManager();
		}
		return data;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}


}
