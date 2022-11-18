package Mapa;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Jugador.Jugador;

public class Trampas {
	private JPanel trampasPanel;
	private ArrayList<JLabel> trampasArray;
	private String[] trampas = new String[3];//La cantidad de trampas disponibles
	
	private static Trampas trampasINSTANCE;
	
	public Trampas() {
		this.trampasArray = new ArrayList<JLabel>();
		
		trampas[0] = "C:/Imagenes/Trampas/explosion.gif";
		trampas[1] = "C:/Imagenes/Trampas/fuego.gif";
		trampas[2] = "C:/Imagenes/Trampas/fuego.gif";
	}
	
	public void initPanelTramas() {
		this.trampasPanel = new JPanel();
		this.trampasPanel.setLayout(null);		
		//this.trampasPanel.setBackground(Color.getHSBColor(155, 50, 155));
		this.trampasPanel.setBackground(Color.ORANGE);
		this.trampasPanel.setBounds(1200,0,300,675);
		System.out.println("Fin init");
					
	}
	public Icon setImage(int altura, int ancho, String ruta) {
		ImageIcon image = new ImageIcon(ruta); 
		image.getImage();
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(ancho, altura, Image.SCALE_DEFAULT));	
		return icon;
	}
	
	public JLabel newLabelTrampa() {
		JLabel label = new JLabel();
		label.setLayout(null);
		label.setBounds(100,50,100,100);		
		return label;
	}
	
	public void addTrampasPanel(String[] pTrampas) {
		for (String ruta : pTrampas) {
			JLabel nuevaTrampa = newLabelTrampa();
			nuevaTrampa.setIcon(setImage(nuevaTrampa.getHeight(),nuevaTrampa.getWidth(),ruta)); // SE SETEA LA IMAGEN
	
			this.trampasPanel.add(nuevaTrampa);
			
			if (trampasArray.size() > 0) {
				int y = trampasArray.get(trampasArray.size()-1).getY()+200;
				nuevaTrampa.setLocation(nuevaTrampa.getX(),y);
			}
			
			
			this.trampasPanel.repaint();
			this.trampasArray.add(nuevaTrampa);
		}
	}
	
	
	public void addTrampasPanel() {
		for (String ruta : trampas) {
			JLabel nuevaTrampa = newLabelTrampa();
			nuevaTrampa.setIcon(setImage(nuevaTrampa.getHeight(),nuevaTrampa.getWidth(),ruta)); // SE SETEA LA IMAGEN
	
			this.trampasPanel.add(nuevaTrampa);
			
			if (trampasArray.size() > 0) {
				int y = trampasArray.get(trampasArray.size()-1).getY()+200;
				nuevaTrampa.setLocation(nuevaTrampa.getX(),y);
			}
			
			
			this.trampasPanel.repaint();
			this.trampasArray.add(nuevaTrampa);
		}
	}
	
	public ArrayList<JLabel> getTrampasArray(){
		return this.trampasArray;
	}
	
	public JPanel getTrampasPanel() {
		return this.trampasPanel;
	}
	
	public String[] getTrampasImage() {
		return this.trampas;
	}
	
	public String getTrampasImageINDEX(int pINDEX) {
		if(pINDEX < this.trampas.length) {
			return this.trampas[pINDEX];
		}
		return null;
		
	}
	
	public static Trampas getInstance() {
		
		if(Trampas.trampasINSTANCE == null) {
			Trampas.trampasINSTANCE = new Trampas();
		}
		return trampasINSTANCE;
	}
	
	
	
}
