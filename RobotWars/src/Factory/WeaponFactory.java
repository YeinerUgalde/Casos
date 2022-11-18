package Factory;

import Robot.Weapon;
import Usable.Armas.*;


public class WeaponFactory {
	
	public enum WEAPONS {BananaGun,Rpg}
	
	public static Weapon Weapon(WEAPONS pWeapon) {
		Weapon weapon;
		switch (pWeapon) {
			case BananaGun:
				weapon = new BananaGun();
				break;
				
			case Rpg:
				weapon = new Rpg();
				break;
			default:
				weapon = new BananaGun();
				break;
		}
		return weapon;
	}

}
