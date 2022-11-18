package Time;

import java.util.ArrayList;

import UIController.Controller;

public class TimeMain implements Runnable {
	
	private Thread hilo;
	private boolean enable = false;
	private Controller controller;
	private ArrayList<int[]> bullets = new ArrayList<int[]>();
	private ArrayList bulletsDistance = new ArrayList();
	private int[]lastBullet = null;
	private int[]lastBullet2 = null;
	
	private ArrayList<int[]> lastBullets = new ArrayList<int[]>();;

	
	public void bulletAnimation() {
		for (int i = 0; i< bullets.size() ;i++){ //Sttremeang
			
			int pos [] = (int[]) bullets.get(i);
			if (pos == null || pos.length < 3)continue;
			//this.lastBullet = pos;
			
			switch(pos[2]) {
				case 0:
					pos[0]+=10;
					break;
				case 1:
					pos[0]-=10;
					break;
				case 2:
					pos[1]-=10;
					break;
				case 3:
					pos[1]+=10;
					break;
			}
			
			this.controller.reportBulletFrame(i,pos[0],pos[1]);//Cambiar a streaming
			
			this.bullets.remove(i);
			
			
			
			if (this.controller.evaluateHitBox(i, pos)) continue;
			
			
		
			this.bullets.add(i, pos);

			
			if (pos[2] == 0 && pos[0] >= (int)bulletsDistance.get(i) || pos[2] == 1 && pos[0] <= (int)bulletsDistance.get(i)) {
				//System.out.println(pos[0]+" "+pos[1]+" "+pos[2]);
				this.bullets.remove(i);
				this.bulletsDistance.remove(i);
				this.controller.reportOverBullet(i);
			}
			
			else if (pos[2] == 0 && pos[0] >= 2300|| pos[2] == 1 && pos[0] <= -1200) {
				//System.out.println(pos[0]+" "+pos[1]+" "+pos[2]);
				this.bullets.remove(i);
				this.bulletsDistance.remove(i);
				this.controller.reportOverBullet(i);
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (enable) {
			try {
				
				if (this.bullets.size() > 0 && this.bullets.size() < 30) {
					this.bulletAnimation();
					
				}
				else {
					this.enable = false;
					controller.clearBullets();//
					break;
				}
					
				Thread.sleep(5);
				
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		for (int i = 0; i< bullets.size() ;i++){
			this.bullets.remove(i);
			this.bulletsDistance.remove(i);
			this.controller.reportOverBullet(i);
		}
		controller.clearBullets();//
		controller.setTimeNull();
		
	}
	
	public void iniciar() {
		if (!this.enable) {
			this.enable = true;
			this.hilo = new Thread(this);
		}
		if (enable)hilo.start();
	}
	
	public void añadirProceso(int pPosX, int pPosY,int pDirect) {	
		int pos [] = {pPosX,pPosY,pDirect};
		this.bullets.add(pos);
		int[] distance = {600,-600};
		this.bulletsDistance.add(pPosX+distance[pDirect]);
		this.lastBullet = pos.clone();
		if (this.lastBullets.size() >= 3) {
			this.lastBullets.clear();
		}
		
		
		
		this.lastBullets.add(lastBullet);
		controller.reportBulletMulti(lastBullets);
		
		lastBullets.clear();//QUITAR
		
		//System.out.println(pos[0]+" "+pos[1]+" "+pos[2]);
	}
	
	public void añadirMultiProceso(int pPosX, int pPosY,int pDirect) {	
		int pos [] = {pPosX,pPosY,pDirect};
		this.bullets.add(pos);
		int[] distance = {600,-600};
		this.bulletsDistance.add(pPosX+distance[pDirect]);
		//System.out.println(pos[0]+" "+pos[1]+" "+pos[2]);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public int[] getLastBullet() {
		if (lastBullet != null && bullets.size() >= 1) {
			return lastBullet;
				
		}
		return null;
	}
	
	public ArrayList<int[]> getLastBullets() {
		ArrayList<int[]> nuevoArray = (ArrayList<int[]>) lastBullets.clone();
		if (lastBullets.size() > 0) {
			lastBullets.remove(0);
			
			return nuevoArray;		
		}
		return null;
	}
	
	public void setLastBulletNull() {
		this.lastBullet = null;
	}

}
