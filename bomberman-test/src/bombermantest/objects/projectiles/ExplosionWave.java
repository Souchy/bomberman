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

public class ExplosionWave extends Projectile {

	public static EntityStats stats = new EntityStats(){{
			this.attack = 10;
			this.maxSpeed = new Vector2(0, 0);
			this.accelForce = new Vector2(0, 0);
	}};
	
	public final float radius;
	
	public ExplosionWave(AGame game, Vector2 pos, float radius, EntityStats weaponHolderStats) {
		super(game, pos, Vector2.Zero, weaponHolderStats);
		this.radius = radius;

		gfx = new ModelInstance(ModelsLoader.singleton.get("tree"));  
		gfx.transform.setTranslation(new Vector3(pos, 0));
		gfx.userData = this;
	}

	@Override
	public void onHitEffect() {
		// rien
	}

	@Override
	public long getDuration() {
		return 300;
	}

	@Override
	public boolean destroyOnHit() {
		return false;
	}

	@Override
	public boolean isSensor() {
		return true;
	}

	@Override
	public Shape getShape() {
		return AConstants.circleShape(radius);
	}

	@Override
	public EntityStats getStats() {
		return stats;
	}

}
