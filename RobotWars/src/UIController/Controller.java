package UIController;

import UI.RobotWindowTest; 
import Time.TimeMain;

import java.time.LocalTime;
import java.util.ArrayList;

import Manager.RobotStatusManager;
import Mapa.Trampas;
import Robot.MOVEMENT;
import Robot.Robot;
import Robot.Weapon;
import Server.Comunication.SocketClient;
import Factory.*;

public class Controller {
	
	private static Controller controller;
	
	private Robot robot;
	private Robot robot2;
	private RobotWindowTest frame;
	private TimeMain time = null;
	private RobotStatusManager manager;
	private ArrayList<int[]> bullets = null;
	private ArrayList<int[]> trampasCoords = null;
	private Trampas trampas = Trampas.getInstance();
	private boolean inicio = false;
	
	private boolean segundoJugador = false;
	
	private int []armasID = {};
	private int strikeID = 0;
	private int robotID = 0;
	
	private SocketClient cliente;
	
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
		
		this.frame.changePanelVisivility(pX, pY);//EN WORK
	
	}
	
	public void reportCoords2(int pX, int pY) {
		if (robot2 == null)return;
		this.robot2.setX(pX);
		this.robot2.setY(pY);
		this.frame.setCoords2(pX, pY);
		if(!inicio) {
			inicio = true;
			this.reportTrampasMulti();
			this.reportHitMulti(robot.getEnergy());
			
		}
		
	}
	
	public void reportEnergyFrame(int pID, int pEnergy) {
		if(pID == 0) {
			frame.setVidaRobot1(pEnergy);
		}else {
			frame.setVidaRobot2(pEnergy);
		}
	}
	
	public void reportEnergyRobot(int pEnergy) {
		this.robot.setEnergy(pEnergy);
	}
	
	public int getEnergyRobot() {
		return this.robot.getEnergy();
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
		//if(!manager.evaluateHitBoxEntidades()) {////
			//this.robot.move( MOVEMENT.values()[pMove], pTime);
		//}
		if(robot.getEnable()) {
			this.robot.move( MOVEMENT.values()[pMove], pTime);
		}
		
		
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
		
		String ruta = "C:/Imagenes/Robots/RobotRojo.png";
		String ruta2 = "C:/Imagenes/Robots/RobotRojoD.png";
		robot.setImagenes(ruta);
		robot.setImagenes(ruta2);
		
		frame.initRobotLabel();
		frame.setVidaRobot1(robot.getEnergy());
		
		this.setRobot(robot);
		this.robot.setX(0);
		this.robot.setY(300);
		this.reportCoords(this.robot.getX(), this.robot.getY());

		
		createRobot2();
	}
	
	
	public void createRobot2() {
		Robot robot = new Robot();
		//Weapon weapon1 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.values()[pArmas[0]]);
		//Weapon weapon2 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.values()[pArmas[1]]);
		Weapon weapon1 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.values()[armasID[0]]);
		Weapon weapon2 = WeaponFactory.Weapon(WeaponFactory.WEAPONS.values()[armasID[1]]);
		
		Weapon strike = StrikeFactory.Strike(StrikeFactory.STRIKES.values()[strikeID]);
		
		robot.addWeapon(weapon1);
		robot.addWeapon(weapon2);
		robot.addStrike(strike);
		robot.setController(this);
		
		manager.addRobot(robot);
		
		String ruta = "C:/Imagenes/Robots/RobotRojo.png";
		String ruta2 = "C:/Imagenes/Robots/RobotRojoD.png";
		robot.setImagenes(ruta);
		robot.setImagenes(ruta2);
		
		this.frame.initRobotLabel2();
		
		this.setRobot2(robot);
		
		//this.reportCoords2(300, 300);
		
		
		
	}
	
	
	
	
	
	
	
	
	public void addBulletFrame(int posX, int posY,String Ruta) {
		if (Ruta == "")Ruta = "C:/Imagenes/balas/Bala.png";
		this.frame.addLabel(posX,posY+20,Ruta); //
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
		frame.repotCBullets(pID, pPosX, pPosY+20);
	}

	public void reportBullet(int pPosX, int pPosY, int pDirec) {
		if (this.time == null) {
			this.time = new TimeMain();
			this.time.setController(this);
			this.time.iniciar();
		}
		time.añadirProceso(pPosX,pPosY,pDirec);		
	}
	
	public void reportMultiBullet(int pPosX, int pPosY, int pDirec) {
		if (this.time == null) {
			this.time = new TimeMain();
			this.time.setController(this);
			this.time.iniciar();
		}
		time.añadirMultiProceso(pPosX,pPosY,pDirec);		
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
			int pos[] = time.getLastBullet();
			//time.setLastBulletNull();
			return  pos;
		}
		return null;
	}
	
	public ArrayList<int[]> getLastBullets(){
		if (time!= null) {
			return  time.getLastBullets();
		}
		return null;
	}
	
	public void clearBullets() {
		frame.clearBullets();
	}
	
	public void initComponents() {
		this.frame.initComponents();
	}

	public ArrayList<int[]> getTrampasCoords() {
		if (trampasCoords!= null) {
			return trampasCoords;
		}
		System.out.println();
		return trampasCoords;
	}

	public void addTrampasFrame(ArrayList<int[]> pTrampasCoords) {
		if(pTrampasCoords.size() == 0) {
			return;
		}
		for(int[] coord : pTrampasCoords) {
			
			frame.addTrampas(coord[0], coord[1], trampas.getTrampasImageINDEX(coord[2]));
			manager.addEntidad(coord);
		}
		
	}
	
	public boolean evaluateHitboxEntidad(int x, int y) {
		return manager.evaluateHitBoxEntidades(x, y);
	}
	public void segundoJugador() {
		
		int nuevoX =robot.getX()+1200;
		int nuevoY =robot.getY();
		nuevoX+=500;
		this.robot.setX(nuevoX);
		this.robot.setY(nuevoY);
		this.reportCoords(nuevoX, nuevoY);

		if(trampasCoords == null) {
			this.reportMoveMulti(robot.getX(), robot.getY());
			return;
		}
		for(int[] coord : this.trampasCoords) {
	
			coord[0]+=1200;
		}
		
		this.reportMoveMulti(robot.getX(), robot.getY());

		
	}
	public void setTrampasCoords(ArrayList<int[]> trampasCoords) {
		//System.out.println("Set");
		this.trampasCoords = trampasCoords;
		if(segundoJugador) {
			segundoJugador();
		}
		addTrampasFrame(trampasCoords);
		
	}

	public void setCliente(SocketClient cliente) {
		this.cliente = cliente;
	}
	
	public static String createString(ArrayList<int[]> bullets) {
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
		//System.out.println(bull);
		return bull;
	}
	
	public void reportBulletMulti(ArrayList<int[]> pBullets) {
		if(cliente != null && robot!=null) {
			
			String pos = robot.getX() + "," + robot.getY();
			String bull1 = createString(pBullets);
			pos+=bull1;
			
			cliente.sendMsg(pos);
		}
	}
	
	public void reportMoveMulti(int x , int y) {
		if(cliente != null && robot!=null) {
			
			String pos = robot.getX() + "," + robot.getY();
			
			cliente.sendMsg(pos);
		}
	}
	
	public void reportTrampasMulti() {
		if(cliente != null && robot!=null) {
			
			String pos = robot.getCoords()+"::";
			pos+=createString(this.trampasCoords);
			cliente.sendMsg(pos);
			//System.out.println("reportando trampas");
		}
	}
	
	public void reportHitMulti(int pEnergy) {
		if(cliente != null && robot!=null) {
			
			String pos = robot.getCoords()+":::";
			pos+=":"+pEnergy;
			cliente.sendMsg(pos);
			System.out.println("Reportando daño: "+pos);
		}
	}

	public void setSegundoJugador() {
		this.segundoJugador = true;
	}

	public void setArmas(int [] armas) {
		this.armasID = armas;
	}

	public void setStrike(int strike) {
		this.strikeID = strike;
	}

	public void setRobotID(int robotID) {
		this.robotID = robotID;
	}
	
	
	
}
