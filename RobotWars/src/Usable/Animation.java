package Usable.Golpes;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import UIController.Controller;

public class Animation {
	
	private int animacionIndex = 0;
	private Controller controller;
	private TimerTask task;
	private Timer timer;
	
	
	public Animation (Controller controller) {
		this.controller = controller;
	}
	
	public void runTimer() {
		if (timer == null) {
			timer = new Timer();
		}
		timer.scheduleAtFixedRate(task, 100, 100);
		//timer.schedule(task, 100);
		
		
	}
	
	public void startAnimation(int pPosX, int pPosY, String pAnimacion[]) {

		task = new TimerTask() {
			//LocalTime velocidad de ataque.....
			@Override
			public void run() {
				
				controller.deletStrike();
				controller.addStrikeFrame(pPosX, pPosY+45, pAnimacion[animacionIndex]);
				if (animacionIndex+1 < pAnimacion.length) animacionIndex++;				
				else {
					this.cancel();
					task = null;
					controller.deletStrike();
					timer.purge();
					timer.cancel();				
					timer = null;
					return;
					
					
				}
				//controller.evaluateHitBox(0, [pPosX,pPosY];)
				
			}
			
			
			
		};
		runTimer();
	}
	
	public void setAnimationIndex0() {
		this.animacionIndex = 0;
	}
}
