package bombermantest.objects.weapons;

import com.mygdx.engine.objects.weapons.Weapon;

public enum Weapons {
	
	BombPlacer {
		@Override
		public Weapon newWeapon() {
			return new BombPlacer();
		}
	},
	/*Gun {
		@Override
		public Weapon newWeapon() {
			return new Gun();
		}
	},*/
	;
	
	public static Weapons get(Class<?> weaponClass){
		return Weapons.valueOf(weaponClass.getSimpleName());
	}
	
	public abstract Weapon newWeapon();
	
}
