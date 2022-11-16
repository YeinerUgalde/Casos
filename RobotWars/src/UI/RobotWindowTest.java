package UI;

import UIController.Controller;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import java.util.ArrayList;

public class RobotWindowTest extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JPanel mainPanel;
	private JLabel robotImage;
	private JLabel robotImage2;
	private int posXRobot;
	private int posXRobot2;
	private int posYRobot;
	private int posYRobot2;
	private ArrayList<JLabel> CBullets = new ArrayList<JLabel>();
	private JLabel strike,weapon;
	
	
	public RobotWindowTest(Controller pController) {
		this.controller = pController;
		this.setTitle("test");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(0,0,1200,675); //Cambiar // tomarlas del winaApi
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.addKeyListener(this);

		this.initComponents();
		this.setVisible(false);
	
	}
	public void refresh() {
		this.robotImage2.setLocation(posXRobot2, posYRobot2);
		this.robotImage.setLocation(posXRobot, posYRobot);
		
	}
	
	public void setCoords(int pX, int pY) {

		if (pX <= this.mainPanel.getBounds().getMaxX()-this.robotImage.getWidth()) {
			this.posXRobot = pX;
		}
		if (pY+this.robotImage.getHeight() <= this.mainPanel.getBounds().getMaxY()) {
			this.posYRobot = pY;
		}
		this.robotImage.setLocation(posXRobot,posYRobot);
		//refresh();
	}
	
	public void setCoords2(int pX, int pY) {

		if (pX <= this.mainPanel.getBounds().getMaxX()-this.robotImage2.getWidth()) {
			this.posXRobot2 = pX;
		}
		if (pY+this.robotImage2.getHeight() <= this.mainPanel.getBounds().getMaxY()) {
			this.posYRobot2 = pY;
		}
		this.robotImage2.setLocation(posXRobot2,posYRobot2);
		//refresh();
	}
	
	public JLabel setImage(JLabel pLabel, String ruta) {
		ImageIcon image = new ImageIcon(ruta); 
		image.getImage();
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(pLabel.getWidth(), pLabel.getHeight(), Image.SCALE_DEFAULT));	
		pLabel.setIcon(icon); 
		return pLabel;
	}
	
	
	public void deletCBullet(int pID) {
		if (pID < this. CBullets.size()) {
			this.mainPanel.remove(CBullets.get(pID));
			this.CBullets.remove(pID);
		}
		
		//this.mainPanel.repaint();
	}
	public void repotCBullets(int pID, int pPosX, int pPosY) {
		if (pID>= 0 && pID < this.CBullets.size()) {
			this.CBullets.get(pID).setLocation(pPosX, pPosY); //TEST
		}	
		
	}
	
	public void addLabel(int pPosX, int pPosY,String pRuta) {
		JLabel label = new JLabel();
		label.setBounds(pPosX,pPosY,40,30);
		label = this.setImage(label, pRuta);
		
		this.CBullets.add(label); //CAMBIAR A UNA LISTA DISTINTA Y O FUNCION
		
		this.mainPanel.add(label);
	}
	
	public void addWeapon(int pPosX, int pPosY,String pRuta) {
		if (this.weapon == null) {
			this.weapon = new JLabel();
			this.weapon.setBounds(pPosX,pPosY,100,60);
			this.weapon = this.setImage(weapon, pRuta);
			this.mainPanel.add(weapon);
			//this.robotImage.repaint();
			//this.repaint();
		}else {
			this.weapon.removeAll();
			this.weapon = this.setImage(weapon, pRuta);
			this.weapon.setVisible(true);
			this.weapon.setLocation(pPosX,pPosY);
		}
		
	}
	public void deleteWeapon() {
		if (weapon == null)return;
		else {
			this.weapon.setVisible(false);
			
		}
	}
	
	public void addStrike(int pPosX, int pPosY,String pRuta) {
		if (this.strike == null) {
			this.strike = new JLabel();
			this.strike.setBounds(pPosX,pPosY,100,60);
			this.strike = this.setImage(strike, pRuta);
			this.mainPanel.add(strike);
			//this.robotImage.repaint();
			//this.repaint();
		}else {
			this.strike.removeAll();
			this.strike = this.setImage(strike, pRuta);
			this.strike.setVisible(true);
			this.strike.setLocation(pPosX,pPosY);
		}
		
	}
	
	public void deletStrike() {
		if (strike == null)return;
		else {
			this.strike.setVisible(false);
			
		}
		/*
		this.mainPanel.remove(strike);
		this.strike.removeAll();
		this.strike = null;
		this.mainPanel.repaint();*/
	}

	public void initRobotLabel() {
		this.robotImage = new JLabel();
		this.robotImage.setBounds(this.posXRobot,this.posYRobot,180,180);
		this.robotImage.setLayout(null);
	}
	
	public void initRobotLabel2() {
		this.robotImage2 = new JLabel();
		this.robotImage2.setBounds(this.posXRobot2,this.posYRobot2,180,180);
		this.robotImage2.setLayout(null);
		
		this.mainPanel.add(robotImage2);
		
	}
	
	public void updateWeapon() {
		if (weapon != null && weapon.isVisible()) {
			
			if (weapon.getX() < this.posXRobot) {
				this.weapon.setLocation(posXRobot-60,posYRobot);
			}
			else {
				this.weapon.setLocation((int) this.robotImage.getBounds().getMaxX()-35,posYRobot+70);
			}
			  //getMousePosition()
		}
	}
	
	public void updateStrike() {
		if (strike != null && strike.isVisible()) {
			
			if (strike.getX() < this.posXRobot) {
				this.strike.setLocation(posXRobot-45,posYRobot+70);
			}
			else {
				this.strike.setLocation((int) this.robotImage.getBounds().getMaxX()-60,posYRobot+70);
			}
			  //getMousePosition()
		}
	}
	
	
	public void setRobotImage(String pRuta) {
		this.robotImage.removeAll();
		this.robotImage = this.setImage(robotImage, pRuta);
		updateStrike();
		updateWeapon();
		//this.mainPanel.repaint();
		//System.out.println(robotImage.getLocation());
	}
	
	
	
	public void setRobotImage2(String pRuta) {
		//this.robotImage2.removeAll();
		this.robotImage2 = this.setImage(robotImage2, pRuta);
		
		//this.repaint();
		//System.out.println(robotImage.getLocation());
	}
	
	private void initMainPanel(){	// QUITAR TODOS LOS INITS Y HACERLOS EN UNA CLASE APARTE
		this.mainPanel = new JPanel();
		this.mainPanel.setBackground(Color.DARK_GRAY);
		this.mainPanel.setLayout(null);		
		this.mainPanel.setBounds(0,0,1200,675); // Por el momento iguales al frame
		this.mainPanel.repaint();
		this.add(mainPanel);	
		//this.mainPanel.repaint();
	}
	
	
	public void initComponents() {
		this.initRobotLabel();
		//this.initRobotLabel2();
		
		this.initMainPanel();
		//this.mainPanel.setFocusable(false);
		
		//this.mainPanel.add(robotImage2);
		this.mainPanel.add(robotImage);
		
		
		this.setVisible(true);
		this.repaint();
		
		
	}

	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		LocalTime time = LocalTime.now();
		switch(e.getExtendedKeyCode()){
			case KeyEvent.VK_UP:
				this.controller.reportMOVEMENT(2,time);
				break;
			case KeyEvent.VK_DOWN:
				this.controller.reportMOVEMENT(3,time);
				break;
			case KeyEvent.VK_LEFT:
				this.controller.reportMOVEMENT(0,time);
				break;
			case KeyEvent.VK_RIGHT:
				this.controller.reportMOVEMENT(1,time);
				break;
			case KeyEvent.VK_SPACE:
				this.controller.fire();
				break;
			case KeyEvent.VK_CONTROL:
				this.controller.hit();
				break;
			default:
				//System.out.println(e.getKeyChar());
				break;
		}
		//updateStrike();
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("Se dejo de presionar");
		switch(e.getExtendedKeyCode()){
			case KeyEvent.VK_UP:
				this.controller.stopMove2();
				break;
				
			case KeyEvent.VK_DOWN:
				this.controller.stopMove2();
				break;
			case KeyEvent.VK_LEFT:
				this.controller.stopMove1();
				break;
			case KeyEvent.VK_RIGHT:
				this.controller.stopMove1();
				break;
			case KeyEvent.VK_CONTROL:
				this.controller.stopHit();
				break;
			default:
				
				break;
		}
	
	}
	
	
	
	
	
}
