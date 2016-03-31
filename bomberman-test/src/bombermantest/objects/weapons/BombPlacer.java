package bombermantest.objects.weapons;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.game.AGame;
import com.mygdx.engine.objects.characters.EntityStats;
import com.mygdx.engine.objects.weapons.Weapon;

import bombermantest.objects.projectiles.NormalBomb;

public class BombPlacer extends Weapon {
	
	@Override
	public void attack(AGame g, Vector2 initPos, Vector2 uDir, EntityStats weaponHolderStats) {
		NormalBomb bomb = new NormalBomb(g, initPos, weaponHolderStats);
		bomb.publishThreaded(true);
	}
	
	@Override
	public void specialAttack(AGame g, Vector2 initPos, EntityStats weaponHolderStats) {
		
	}

	@Override
	public int getAmmoCost() {
		return 1;
	}

	@Override
	public float getCooldown() {
		return 0.2f;
	}

}
