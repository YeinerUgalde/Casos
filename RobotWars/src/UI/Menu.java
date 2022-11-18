package UI;

import UIController.Controller;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import java.util.ArrayList;

public class Menu extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JPanel robots, pArmas, pStrikes;
	private String[] lArmas,lStrikes,lRobots;
	
	private ArrayList<JLabel> robotPanel = new ArrayList<JLabel>();
	private int x = 100;
	private int y = 290;
	private int seleccion = 0; //DEFAULT
	private int strike = 0;
	private int armas[];
	private int armasINDEX = 0;
	

	
	public Menu(Controller pController) {
		this.armas = new int[2];
		
		this.controller = pController;
		this.setTitle("Menu");
		this.setLayout(null);
		this.setBounds(0,0,1200,675);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.initComponents();
		
		this.setVisible(true);
	}
	
	public void finMenu() {
		controller.createRobot(seleccion, armas, strike);
		controller.initComponents();
	}
	
	public JPanel getPanelSeleccion() {	
		return this.robots;
	}
	

	public JLabel setImage(JLabel pLabel, String ruta) {
		ImageIcon image = new ImageIcon(ruta); 
		image.getImage();
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(pLabel.getWidth(), pLabel.getHeight(), Image.SCALE_DEFAULT));	
		pLabel.setIcon(icon); 
		return pLabel;
	}


	
	public JLabel addLabel(int pPosX, int pPosY,String pRuta) {
		JLabel label = new JLabel();
		label.setBounds(pPosX,pPosY,200,150);
		label = this.setImage(label, pRuta);
		return label;
	}
	
	public JButton addButton(int pPosX, int pPosY, String pNombre) {
		JButton button = new JButton(pNombre);
		button.setBounds(pPosX,pPosY,200,100);
		return button;
	}
	
	public JButton addActionSeleecionarRobot(JButton pButton,int pINDEX) {
		
		pButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Robot: "+ pINDEX);
				seleccion = pINDEX;
			
				//controller.initComponents();
				robots.setVisible(false);
				x = 100;
				y = 290;
				initArmasPanel();
			}
		});
		return pButton;
		
	}
	public JButton addActionSeleecionarArma(JButton pButton,int pINDEX) {
		
		pButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("arma: "+ pINDEX);
				armas[armasINDEX] = pINDEX;
				armasINDEX++;
			
				if (armasINDEX >= 2) {
			
					pArmas.setVisible(false);
					remove(pArmas);
					x = 100;
					y = 290;
					initStrikesPanel();
				}
				
				
			}
		});
		return pButton;
		
	}
	
	public JButton addActionSeleecionarStrike(JButton pButton,int pINDEX) {
		
		pButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("strike: "+ pINDEX);
				strike = pINDEX;
				pStrikes.setVisible(false);
				setVisible(false);
				
				//controller.initComponents();
				finMenu();
				
			}
		});
		return pButton;
		
	}
	
	

	public void addRobots(String rutas[]){
		for (int i = 0; i < rutas.length; i++) {
			JLabel robot = addLabel(this.x,this.y,rutas[i]);
			JButton bSeleccionar = addButton(this.x, this.y+150, "seleccionar");
			bSeleccionar = addActionSeleecionarRobot(bSeleccionar, i);
			
			this.robots.add(robot);
			this.robots.add(bSeleccionar);
			
			x+=400;
			
			this.robots.repaint();
			
		}
	}
	
	public void addArmas(String rutas[]){
		for (int i = 0; i < rutas.length; i++) {
			JLabel arma = addLabel(this.x,this.y,rutas[i]);
			JButton bSeleccionar = addButton(this.x, this.y+150, "seleccionar");
			bSeleccionar = addActionSeleecionarArma(bSeleccionar, i);
			
			this.pArmas.add(arma);
			this.pArmas.add(bSeleccionar);
			
			x+=400;
			
			this.pArmas.repaint();
			
		}
	}
	
	public void addStrike(String rutas[]){
		for (int i = 0; i < rutas.length; i++) {
			JLabel strike = addLabel(this.x,this.y,rutas[i]);
			JButton bSeleccionar = addButton(this.x, this.y+150, "seleccionar");
			bSeleccionar = addActionSeleecionarStrike(bSeleccionar, i);
			
			this.pStrikes.add(strike);
			this.pStrikes.add(bSeleccionar);
			
			x+=400;
			
			this.pStrikes.repaint();
			
		}
	}

	public void initRobotPanel() {
		this.robots = new JPanel();
		this.robots.setBounds(0,0,1200,675);
		this.robots.setLayout(null);
		this.robots.setBackground(Color.GRAY);
		this.robots.setVisible(true);
		this.add(robots);
		
		String robots[] = {"../RobotWars/src/Imagenes/Robots/RobotRojo.png","../RobotWars/src/Imagenes/Robots/RobotRojo.png"};
		this.addRobots(robots);

	}
	
	public void initArmasPanel() {
		this.pArmas = new JPanel();
		this.pArmas.setBounds(0,0,1200,675);
		this.pArmas.setLayout(null);
		this.pArmas.setBackground(Color.GRAY);
		this.pArmas.setVisible(true);
		this.add(pArmas);
		
		String armas[] = {"../RobotWars/src/Imagenes/ArmasCortoAlcance/EspadaIz.png","../RobotWars/src/Imagenes/Robots/RobotRojo.png"};
		this.addArmas(armas);

	}
	
	public void initStrikesPanel() {
		this.pStrikes = new JPanel();
		this.pStrikes.setBounds(0,0,1200,675);
		this.pStrikes.setLayout(null);
		this.pStrikes.setBackground(Color.GRAY);
		this.pStrikes.setVisible(true);
		this.add(pStrikes);
		
		String strikes[] = {"../RobotWars/src/Imagenes/ArmasCortoAlcance/EspadaRg.png","../RobotWars/src/Imagenes/Robots/RobotRojo.png"};
		this.addStrike(strikes);

	}
	
	
			
	private void initComponents() {
		this.initRobotPanel();
		
		//PRUEBA
	
	}

	public int getSeleccion() {
		return seleccion;
	}



	
}