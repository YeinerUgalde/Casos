package Usable.Armas;

import Animation.AnimationWeapon;
import Robot.ORIENTATION;
import Robot.Weapon;
import UIController.Controller;
import UIController.SController;

public class BananaGun extends Weapon implements SController{
		

	private String bala = "../RobotWars/src/Imagenes/balas/Bala.png";
	private String BananaGunIz = "../RobotWars/src/Imagenes/Armas/BananaGunIz.png";
	private String BananaGunRg = "../RobotWars/src/Imagenes/Armas/BananaGunRg.png";
	private AnimationWeapon animacionDebug;
	private String animacion[];
	
	public BananaGun() {
		super(1000, 0,800);
		animacion = new String[1];
		animacion[0] = "../RobotWars/src/Imagenes/Armas/BananaGunRg.png";
		// TODO Auto-generated constructor stub
	}
	
	public void Animation() {
		if (animacionDebug == null) animacionDebug = new AnimationWeapon(controller);
		animacionDebug.setAnimationIndex0();
		animacionDebug.startAnimation(posX+20, posY, animacion);	
	}

	@Override
	public void triggerWeapon(int pPosX, int pPosY, ORIENTATION pDirection) {
		this.setPosX(pPosX);
		this.setPosY(pPosY);
		controller.addBulletFrame(pPosX, pPosY, bala);
		switch (pDirection) {
			case EAST:
				
				controller.addWeaponFrame(pPosX, pPosY, BananaGunRg);
				animacion[0] = BananaGunRg;
				Animation();
				
				controller.reportBullet(pPosX, pPosY,0);				
				break;
			case WEST:
				
				animacion[0] = BananaGunIz;
				controller.addWeaponFrame(pPosX, pPosY, BananaGunIz);
				Animation();
				
				controller.reportBullet(pPosX, pPosY,1);
				break;
			case NORTH:
				controller.reportBullet(pPosX, pPosY,2);
				break;
			case SOUTH:
				controller.reportBullet(pPosX, pPosY,3);
				break;
		}
		
		
		
	}


}
