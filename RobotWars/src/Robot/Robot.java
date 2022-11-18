package Robot;

import java.awt.Graphics;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import UIController.Controller;

public class Robot extends IRobot{
	private int movements [] = {-ROBOT_SPEED_DEFAULT,ROBOT_SPEED_DEFAULT};
	private ArrayList<String> imagenes = new ArrayList<String>(); //Hay que hacer una clase objeto, imagenesWork/////
	private boolean enable;
	private Timer timer1 = null;
	private Timer timer2 = null;
	private TimerTask task = null;
	private MOVEMENT lastMove;
	
	private Controller controller; // QUITAR CUADNO SE HAGA EL OBSERVER
	
	public Robot() {
		this.enable = true;
		this.lastMove = MOVEMENT.RIGHT;
		//this.posX = 100;
		//this.posY = ARENA_HEIGTH/2;
	}
	
	
	
	public void move(MOVEMENT pMove) {	
		switch(pMove) {
		
		  case LEFT:
			if (posX + movements[0] < 0) break;
			posX+= movements[0];
			this.controller.reportCoords(posX, posY);
			this.controller.reportRobotImage(0);
			this.currentOrientation = ORIENTATION.WEST;
		    break;
		    
		  case RIGHT:
			if (posX + movements[1] > this.ARENA_WIDTH) break;
			posX+= movements[1];			
			this.controller.reportCoords(posX, posY);
			this.controller.reportRobotImage(1);
			this.currentOrientation = ORIENTATION.EAST;
		    break;
		    
		  case UP:
			 if (posY + movements[0] < 0) break;
			 posY+= movements[0];
			 this.controller.reportCoords(posX, posY);
			 //this.currentOrientation = ORIENTATION.NORTH;
			 break;
			 
		  case DOWN:
			 if (posY + movements[1] > this.ARENA_HEIGTH) break;
			 posY+= movements[1];
			 this.controller.reportCoords(posX, posY);
			 //this.currentOrientation = ORIENTATION.SOUTH;
			 break;
			 
		}
		
	}	
	
	
	public void move(MOVEMENT pMove, LocalTime pTime) {	
	
		task = new TimerTask() {
		
			@Override
			public void run() {
				switch(pMove) {
				
				  case LEFT:
					if (posX + movements[0] < 0) break;
					posX+= movements[0];
					controller.reportCoords(posX, posY);
					controller.reportRobotImage(0);
					currentOrientation = ORIENTATION.WEST;
					lastMove = MOVEMENT.LEFT;
				    break;
				    
				  case RIGHT:
					if (posX + movements[1] > ARENA_WIDTH) break;
					posX+= movements[1];			
					controller.reportCoords(posX, posY);
					controller.reportRobotImage(1);
					currentOrientation = ORIENTATION.EAST;
					lastMove = MOVEMENT.RIGHT;
				    break;
				    
				  case UP:
					 if (posY + movements[0] < 0) break;
					 posY+= movements[0]/1.3;
					 controller.reportCoords(posX, posY);
					 //this.currentOrientation = ORIENTATION.NORTH;
					 //lastMove = MOVEMENT.UP;
					 break;
					 
				  case DOWN:
					 if (posY + movements[1] > ARENA_HEIGTH) break;
					 posY+= movements[1]/1.3;
					 controller.reportCoords(posX, posY);
					 //this.currentOrientation = ORIENTATION.SOUTH;
					 //lastMove = MOVEMENT.DOWN;
					 break;
					 
				}
				
				
			}
					
		};
		
		if (pMove == MOVEMENT.LEFT || pMove == MOVEMENT.RIGHT) {
			if (timer1 == null) {
				timer1 = new Timer();
				timer1.scheduleAtFixedRate(task, 3, 10);
			}else if (pMove != lastMove){

				timer1.purge();
				timer1.cancel();
				timer1 = null;
				
				
				timer1 = new Timer();
				timer1.scheduleAtFixedRate(task, 3, 10);
						
				
				
			}
		}else {
			if (timer2 == null) {
				timer2 = new Timer();
				timer2.scheduleAtFixedRate(task, 3, 10);
			}else{

				timer2.purge();
				timer2.cancel();
				timer2 = null;
				
				timer2 = new Timer();
				timer2.scheduleAtFixedRate(task, 3, 10);
			}
		}
		
		
		
	}
	
	public void fire(int pWeaponId) {
		switch(lastMove) {
			case LEFT:
				this.weapons[pWeaponId].fire(this.posX, this.posY+30, this.currentOrientation);
				break;
			case RIGHT:
				this.weapons[pWeaponId].fire(this.posX+180, this.posY+30, this.currentOrientation);
				break;
			case UP:
				break;
			case DOWN:
				break;
		}	
	}
	
	public void hit(int pStrikeId) {
		switch(lastMove) {
			case LEFT:
				this.strikes[pStrikeId].fire(this.posX, this.posY+30, this.currentOrientation);
				break;
			case RIGHT:
				this.strikes[pStrikeId].fire(this.posX+180, this.posY+30, this.currentOrientation);
				break;
			case UP:
				break;
			case DOWN:
				break;
		}	
	}
	
	public void setImagenes(String image) {
		this.imagenes.add(image);
	}
	
	public String getImage(int pID) {
		
		return this.imagenes.get(pID);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void cancelTimer1() {
		if (timer1 != null) {
			timer1.purge();
			this.timer1.cancel();
		}
		task = null;
		this.timer1 = null;
	}
	
	public void cancelTimer2() {
		if (timer2 != null) {
			timer2.purge();
			this.timer2.cancel();
		}
		task = null;
		this.timer2 = null;
	}
	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
	
	public void setX(int pPosX) {
		this.posX = pPosX; 
	}
	
	public void setY(int pPosY) {
		this.posY = pPosY; 
	}
	
	
	@Override
	protected void refreshMove(MOVEMENT pMove, LocalTime pActionTime, Graphics g) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
