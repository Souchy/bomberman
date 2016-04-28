package bombermantest.objects.projectiles;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Shape;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.game.AGame;
import com.mygdx.engine.objects.characters.EntityStats;
import com.mygdx.engine.objects.projectiles.Projectile;
import com.mygdx.engine.services.ModelsLoader;

import bombermantest.objects.characters.playables.BombermanStats;

public class NormalBomb extends Projectile {

	public static EntityStats stats = new EntityStats(){{
			this.attack = -50;
			this.maxSpeed = new Vector2(0, 0);
			this.accelForce = new Vector2(0, 0);
	}};
	
	
	public NormalBomb(AGame game, Vector2 pos, EntityStats weaponHolderStats) {
		super(game, pos, Vector2.Zero, weaponHolderStats);
		
		//gfx = new ModelInstance(ModelsLoader.singleton.get("bomb"));
		gfx = new ModelInstance(ModelsLoader.singleton.get("Tron_Ball(solo)"));
		gfx.transform.scale(8, 8, 8);
		gfx.transform.setTranslation(new Vector3((float) Math.floor(pos.x) + 0.4f, (float) Math.floor(pos.y) + 0.4f, 0));
		gfx.userData = this;
	}
	

	@Override
	public void update(float delta){
		super.update(delta);
		if(timeRemaining <= 0){
			BombermanStats stats = (BombermanStats) weaponHolderStats;
			if(game.universe.player != null && game.universe.player.getStats() == stats){
				stats.nbBombs += 1;
			}
			// explosion
			float radius = stats.power / 2;
			ExplosionWave expl = new ExplosionWave(game, getPos(), radius, weaponHolderStats);
			expl.publish();
			
		}
	}

	@Override
	public void onHitEffect() {
		// rien
	}

	@Override
	public long getDuration() {
		return 2000;
	}

	@Override
	public boolean destroyOnHit() {
		return false;
	}

	@Override
	public boolean isSensor() {
		return false;
	}

	@Override
	public Shape getShape() {
		return AConstants.circleShape(8*AConstants.scale);
	}

	@Override
	public EntityStats getStats() {
		return stats;
	}

}
