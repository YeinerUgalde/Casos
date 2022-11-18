package UIController;

import UI.RobotWindowTest; 
import Time.TimeMain;

import java.time.LocalTime;
import java.util.ArrayList;

import Manager.RobotStatusManager;
import Robot.MOVEMENT;
import Robot.Robot;
import Robot.Weapon;
import Factory.*;

public class Controller {
	
	private static Controller controller;
	
	private Robot robot;
	private Robot robot2;
	private RobotWindowTest frame;
	private TimeMain time = null;
	private RobotStatusManager manager;
	private ArrayList<int[]> bullets = null;
	
	public Boolean isEnabled() {
		if(this.frame != null) {
			return true;
		}
		return false;
	}
	
	public RobotWindowTest getFrame() {
		return frame;
	}
	
	public static Controller getInstance() {
		if (Controller.controller == null) {
			Controller.controller = new Controller();			
		}
		return controller;
	}
	

	public void setFrame(RobotWindowTest frame) {
		this.frame = frame;
	}
	
	
	
		
	public Robot getRobot() {
		return robot;
	}
	
	public void reportCoords(int pX, int pY) {
		this.frame.setCoords(pX, pY);
	}
	
	public void reportCoords2(int pX, int pY) {
		if (robot2 == null)return;
		this.robot2.setX(pX);
		this.robot2.setY(pY);
		this.frame.setCoords2(pX, pY);
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
		this.robot.setController(this);
		this.frame.setRobotImage(robot.getImage(0));
	}
	
	public void setRobot2(Robot robot) {
		this.robot2 = robot;
		this.robot2.setController(this);
		this.frame.setRobotImage2(robot2.getImage(0));
	}
	
	public void reportRobotImage(int pID) {
		this.frame.setRobotImage(robot.getImage(pID));
	}
	
	public void reportRobotImage2(int pID) {
		this.frame.setRobotImage2(robot2.getImage(pID));
	}

	public void reportMOVEMENT(int pMove, LocalTime pTime) {
		//int move = MOVEMENT.values()[pMove].getValue();
		this.robot.move( MOVEMENT.values()[pMove], pTime);
	}
	public void stopMove1() {
		this.robot.cancelTimer1();
	}
	public void stopMove2() {
		this.robot.cancelTimer2();
	}
	
	public void fire() {
		this.robot.fire(0);
	}
	
	public void createRobot(int pRobotID, int pArmas[], int pStrikeID) {
		//Robot robot = RobotsFactory.Robot(pRobotID);
		Robot robot = new Robot();
		//Weapon weapon1 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.values()[pArmas[0]]);
		//Weapon weapon2 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.values()[pArmas[1]]);
		Weapon weapon1 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.BananaGun);
		Weapon weapon2 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.BananaGun);
		
		Weapon strike = StrikeFactory.Strike(StrikeFactory.STRIKES.Espada);
		
		robot.addWeapon(weapon1);
		robot.addWeapon(weapon2);
		robot.addStrike(strike);
		robot.setController(this);
		
		RobotStatusManager manager = new RobotStatusManager();
		manager.addRobot(robot);
		
		this.setManager(manager);
		
		String ruta = "../RobotWars/src/Imagenes/Robots/RobotRojo.png";
		String ruta2 = "../RobotWars/src/Imagenes/Robots/RobotRojoD.png";
		robot.setImagenes(ruta);
		robot.setImagenes(ruta2);
		
		this.setRobot(robot);
		
		createRobot2();
	}
	
	
	public void createRobot2() {
		Robot robot = new Robot();
		//Weapon weapon1 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.values()[pArmas[0]]);
		//Weapon weapon2 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.values()[pArmas[1]]);
		Weapon weapon1 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.BananaGun);
		Weapon weapon2 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.BananaGun);
		
		Weapon strike = StrikeFactory.Strike(StrikeFactory.STRIKES.Espada);
		
		robot.addWeapon(weapon1);
		robot.addWeapon(weapon2);
		robot.addStrike(strike);
		robot.setController(this);
		
		manager.addRobot(robot);
		
		String ruta = "../RobotWars/src/Imagenes/Robots/RobotRojo.png";
		String ruta2 = "../RobotWars/src/Imagenes/Robots/RobotRojoD.png";
		robot.setImagenes(ruta);
		robot.setImagenes(ruta2);
		
		this.frame.initRobotLabel2();
		
		this.setRobot2(robot);
		
		this.reportCoords2(300, 300);
		
		
		
	}
	
	
	
	
	
	
	
	
	public void addBulletFrame(int posX, int posY,String Ruta) {
		if (Ruta == "")Ruta = "../RobotWars/src/Imagenes/balas/Bala.png";
		this.frame.addLabel(posX,posY,Ruta);
	}
	
	
	public void addStrikeFrame(int posX, int posY,String Ruta) {
		this.frame.addStrike(posX, posY, Ruta);
	}
	
	public void deletStrike() {
		this.frame.deletStrike();
	}
	
	
	
	public void addWeaponFrame(int posX, int posY,String Ruta) {
		this.frame.addWeapon(posX, posY, Ruta);
	}
	
	public void deleteWeapon() {
		this.frame.deleteWeapon();
	}
	

	
	public void reportBulletFrame(int pID, int pPosX, int pPosY) {
		frame.repotCBullets(pID, pPosX, pPosY);
	}

	public void reportBullet(int pPosX, int pPosY, int pDirec) {
		if (this.time == null) {
			this.time = new TimeMain();
			this.time.setController(this);
			this.time.iniciar();
		}
		time.añadirProceso(pPosX,pPosY,pDirec);		
	}
	
	public void reportOverBullet(int pID) {
		frame.deletCBullet(pID);
	}
	
	
	
	
	public void setTimeNull() {
		this.time = null;
	}
	
	public void setTime(TimeMain time) {
		this.time = time;
	}
	
	public void setManager(RobotStatusManager manager) {
		this.manager = manager;
		this.manager.setController(this);
	}

	public boolean evaluateHitBox(int pID, int pos[]) {
		return this.manager.evaluateHitBox(pID,  pos);
	}

	public void hit() {
		this.robot.hit(0);	
	}
	
	public void stopHit() {
			
	}
	
	public int[] getLastBullet(){
		if (time!= null) {
			
			return time.getLastBullet();
		}
		return null;
	}
	
	public void initComponents() {
		this.frame.initComponents();
	}
	
	
	
}
