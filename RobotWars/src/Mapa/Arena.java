package Mapa;

import Jugador.Jugador;

public class Arena {
	private Jugador player1;
	private Jugador player2;
	public Arena() {
		
	}
	public Jugador getPlayer1() {
		return player1;
	}
	public void setPlayer1(Jugador player1) {
		this.player1 = player1;
	}
	public Jugador getPlayer2() {
		return player2;
	}
	public void setPlayer2(Jugador player2) {
		this.player2 = player2;
	}
	
}
