package Factory;

import Robot.Weapon;
import Usable.Golpes.Espada;

public class StrikeFactory {
	
	public enum STRIKES {Espada}
	
	public static Weapon Strike(STRIKES pStrike) {
		Weapon strike;
		switch (pStrike) {
			case Espada:
				strike = new Espada();
				break;
			default:
				strike = new Espada();
				break;
		}
		return strike;
	}

}
