package Usable.Golpes;

import java.util.Timer;
import java.util.TimerTask;

import Animation.Animation;
import Robot.ORIENTATION;
import Robot.Weapon;
//import UIController.Controller;
import UIController.SController;

public class Espada extends Weapon implements SController{
	
	//private Controller controller;
	private String espadaRg = "../RobotWars/src/Imagenes/ArmasCortoAlcance/EspadaRg.png";
	private String espadaIz = "../RobotWars/src/Imagenes/ArmasCortoAlcance/EspadaIz.png";
	private String animacion[];
	private Animation animacionDebug;

	
	public Espada() {
		super(1000, 0,800);
		// TODO Auto-generated constructor stub
		animacion = new String[1];
		animacion[0] = "../RobotWars/src/Imagenes/ArmasCortoAlcance/EspadaAnimation/0.png";
		//animacion[1] = "../RobotWars/src/Imagenes/ArmasCortoAlcance/EspadaAnimation/1.png";
		//animacion[2] = "../RobotWars/src/Imagenes/ArmasCortoAlcance/EspadaAnimation/2.png";
		//animacion[3] = "../RobotWars/src/Imagenes/ArmasCortoAlcance/EspadaAnimation/3.png";
					
	}
	
	public void Animation() {
		if (animacionDebug == null) animacionDebug = new Animation(controller);
		animacionDebug.setAnimationIndex0();
		animacionDebug.startAnimation(posX, posY, animacion);	
	}

	@Override
	public void triggerWeapon(int pPosX, int pPosY, ORIENTATION pDirection) {
		this.setPosX(pPosX);
		this.setPosY(pPosY);
		
		switch (pDirection) {
			case EAST:
				controller.addStrikeFrame(pPosX-60
						, pPosY+45, espadaRg);
				animacion[0] = espadaRg;
				this.Animation();
				//controller.reportBullet(pPosX, pPosY,0);
				break;
			case WEST:
				controller.addStrikeFrame(pPosX-30, pPosY+45, espadaIz);
				animacion[0] = espadaIz;
				this.Animation();
				//controller.reportBullet(pPosX, pPosY,1);				
				break;
			case NORTH:
				//controller.reportBullet(pPosX, pPosY,2);
				break;
			case SOUTH:
				//controller.reportBullet(pPosX, pPosY,3);
				break;
		}
		
		
		
	}

	//public void setController(Controller controller) {
		//this.controller = controller;
	//}
}
