package UI;

import UIController.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Mapa.Trampas;
import Robot.MOVEMENT;
import Robot.ORIENTATION;

public class Menu extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JPanel robots, pArmas, pStrikes, ptrampas;
	private String[] lArmas,lStrikes,lRobots;
	private ArrayList<JLabel> trampasArray;
	
	private ArrayList<JLabel> robotPanel = new ArrayList<JLabel>();
	private int x = 100;
	private int y = 290;
	private int seleccion = 0; //DEFAULT
	private int strike = 0;
	private int armas[];
	private int armasINDEX = 0;
	
	
	//TRAMPAS
	private TimerTask task;
	private Timer timer;
	private Point ultimaPos;
	private ArrayList<Point> pointers = new ArrayList<Point>();
	private ArrayList<int[]> coords = new ArrayList<int[]>();
	private int trampasINDEX = 0;
	
	public Menu(Controller pController) {
		this.armas = new int[2];
		this.trampasArray = new ArrayList<JLabel>();
		
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
		controller.setTrampasCoords(coords);
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
				controller.setRobotID(pINDEX);
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
					controller.setArmas(armas);
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
				remove(pStrikes);
				controller.setStrike(pINDEX);
				//setVisible(false);
				
				initTrampasPanel(); //SEGUIR PROGRAMANDO EL DRAG
	
				//finMenu(); // ACA ES DONDE SE HACIA
				
			}
		});
		return pButton;
		
	}
	
	public JButton addActionListo(JButton pButton) {
		
		pButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Listo");
				
				remove(ptrampas);
			
				setVisible(false);
				
				generateCoords(); //GENERA LOS COMPONENTES ELEGIDOS
				
				finMenu(); // ACA ES DONDE SE HACIA
				
			}
		});
		return pButton;
		
	}
	
	public int frameCount() {
		int count = 0;
		for(int i = 0; i < this.trampasArray.size(); i++) {
			if (trampasArray.get(i).getX() <= 1210) {
				count++;
			}
		}
		//System.out.println("ComponentesT: "+this.get);
		return count;
	}
	
	public void generateCoords() {
		for(int i = 0; i < this.trampasArray.size(); i++) {
			if (trampasArray.get(i).getX() <= 1210) {
				int x = trampasArray.get(i).getX();
				int y = trampasArray.get(i).getY();
				int id = i;
				int coord[] = {x,y,id};
				//System.out.println(coord[0] +" : "+ coord[1]+" : "+coord[2]);
				this.coords.add(coord);
			}
		}
	}
	
	public JLabel addActionSeleecionarTrampas(JLabel pButton,int pINDEX) {
		
		pButton.addMouseListener(new MouseListener(){
			

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("Mouse: "+getMousePosition());
				//System.out.println("Boton: "+pButton.getLocation());
				
				if(trampasINDEX >= 2 && getMousePosition().x >1200)return;
				ultimaPos = pButton.getLocation();
				task = new TimerTask() {
					
					@Override
					public void run() {
						if(getMousePosition()!= null) {
							int x = (int)getMousePosition().getX()-100;
							int y = (int)getMousePosition().getY()-100;
							if(x > 5 && x < 1500 && y > 5  && y < 540) {
								pButton.setLocation(x,y);
							}
							
						}
						
						
					}
							
				};
				timer = new Timer();
				timer.scheduleAtFixedRate(task, 1, 1);
				//pButton.setLocation(getMousePosition());
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println(getMousePosition());
				if(ultimaPos == null && pButton.getLocation().x >= 1100) {
					//System.out.println("relocate");
					pButton.setLocation(pointers.get(pINDEX));
					return;
				}
				
				
				if(pButton.getLocation().x >= 1100) {
				
					pButton.setLocation(pointers.get(pINDEX));
					if(ultimaPos.x < 1100) {
						if(trampasINDEX-1 >= 0) {
							trampasINDEX--;
						}
												
					}
					
					
					
					
					
				}else if(ultimaPos.x > 1100 && frameCount() <= 2){
					trampasINDEX++;					
				}else if(trampasINDEX != frameCount()) {

					pButton.setLocation(pointers.get(pINDEX));
					
				}
					
				
				//System.out.println(trampasINDEX);
				//System.out.println("Componentes: "+frameCount());
				if (timer != null) {
					ultimaPos = null;
					timer.cancel();
					timer = null;
				}
				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
	
	public JButton initButtonListo() {
		JButton button = new JButton("Listo");
		button.setBounds(20,10,250,40);
		button.setBackground(Color.getHSBColor(123, 23, 32));
		button = addActionListo(button);
		return button;
	}

	public void initRobotPanel() {
		this.robots = new JPanel();
		this.robots.setBounds(0,0,1200,675);
		this.robots.setLayout(null);
		this.robots.setBackground(Color.GRAY);
		this.robots.setVisible(true);
		this.add(robots);
		
		String robots[] = {"C:/Imagenes/Robots/RobotRojo.png","C:/Imagenes/Robots/RobotRojo.png"};
		this.addRobots(robots);

	}
	
	public void initArmasPanel() {
		this.pArmas = new JPanel();
		this.pArmas.setBounds(0,0,1200,675);
		this.pArmas.setLayout(null);
		this.pArmas.setBackground(Color.GRAY);
		this.pArmas.setVisible(true);
		this.add(pArmas);
		
		String armas[] = {"C:/Imagenes/Armas/BananaGunIz.png","C:/Imagenes/Armas/rpg.png"};
		this.addArmas(armas);

	}
	
	public void initStrikesPanel() {
		this.pStrikes = new JPanel();
		this.pStrikes.setBounds(0,0,1200,675);
		this.pStrikes.setLayout(null);
		this.pStrikes.setBackground(Color.GRAY);
		this.pStrikes.setVisible(true);
		this.add(pStrikes);
		
		String strikes[] = {"C:/Imagenes/ArmasCortoAlcance/EspadaRg.png","C:/Imagenes/ArmasCortoAlcance/Espada2.png","C:/Imagenes/ArmasCortoAlcance/Espada3.png"};
		this.addStrike(strikes);

	}
	
	
	public void initTrampasPanel() {
		System.out.println("INTITRAMPAS");
		Trampas nuevoPanel = Trampas.getInstance(); // VER SI LO CAMBIO
		nuevoPanel.initPanelTramas();
		
		String trampas[] = {"C:/Imagenes/Trampas/explosion.gif","C:/Imagenes/Trampas/fuego.gif","C:/Imagenes/Trampas/fuego.gif"};
		
		nuevoPanel.addTrampasPanel(); //trampas
		
		this.ptrampas = nuevoPanel.getTrampasPanel();
		this.trampasArray = nuevoPanel.getTrampasArray();
		
		for(int i = 0; i<trampasArray.size(); i++) {
			JLabel label = trampasArray.get(i);
			this.add(label);
			label = addActionSeleecionarTrampas(label, i);
			int x = this.getWidth()+label.getX();
			label.setLocation(x,label.getY());
			
			pointers.add(label.getLocation());
		}
		//trampasArray.clear();
		
		JButton listoButton = initButtonListo();
		ptrampas.add(listoButton);

		
		this.setBounds(0,0,1500,675);
		this.add(ptrampas);
		
	}
	
	
			
	private void initComponents() {
		this.initRobotPanel();
		
		//PRUEBA
	
	}

	public int getSeleccion() {
		return seleccion;
	}

	public ArrayList<int[]> getTrampsCoords(){
		return this.coords;
	}

	
}