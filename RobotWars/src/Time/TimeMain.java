package Time;

import java.util.ArrayList;

import UIController.Controller;

public class TimeMain implements Runnable {
	
	private Thread hilo;
	private boolean enable = false;
	private Controller controller;
	private ArrayList<int[]> bullets = new ArrayList<int[]>();
	private int[]lastBullet = null;
	private int[]lastBullet2 = null;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (enable) {
			try {
				
				if (this.bullets.size() > 0) {
					
					for (int i = 0; i< bullets.size() ;i++){ //Sttremeang
						
						int pos [] = (int[]) bullets.get(i);
						
						this.lastBullet = pos;
						
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
		
						
						if (pos[0] > 1000 || pos[0] < 0 || pos[1] > 600 || pos[1] < 0) {
							//System.out.println(pos[0]+" "+pos[1]+" "+pos[2]);
							this.bullets.remove(i);
							this.controller.reportOverBullet(i);
						}
					}
				}
				else {
					this.enable = false;
					break;
				}
					
				Thread.sleep(10);
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
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
		//System.out.println(pos[0]+" "+pos[1]+" "+pos[2]);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public int[] getLastBullet() {
		//if (lastBullet!=null && lastBullet != lastBullet2) {
		if (lastBullet!=null ) {
			//lastBullet2 = lastBullet;
			//lastBullet = null;
		
			return this.lastBullet;
		}
		return null;
	}

}
