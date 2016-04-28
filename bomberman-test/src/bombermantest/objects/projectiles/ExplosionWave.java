package bombermantest.objects.projectiles;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
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
		
		radius = 10;
		
		ModelBuilder builder = new ModelBuilder();
		Model bomb = ModelsLoader.singleton.get("penguin");
		//Model part1 = ModelsLoader.singleton.get("tree");
		//Model part2 = ModelsLoader.singleton.get("penguin");
		//part2.nodes.forEach(n -> n.translation.set(20, 20, 20));
		builder.begin();

		Node center = builder.node();
		center.id = "center";
		center.translation.set(0, 0, 0);
		builder.part(bomb.meshParts.first(), bomb.materials.first());
		
		for(float x = -radius; x < radius; x++){
			if(x == 0) continue;
			Node node = builder.node();
			node.id = "x"+x;
			node.translation.set(x*12, 0, 0);
			builder.part(bomb.meshParts.first(), bomb.materials.first());
		}
		for(float y = -radius; y < radius; y++){
			if(y == 0) continue;
			Node node = builder.node();
			node.id = "y"+y;
			node.translation.set(0, y*12, 0);
			builder.part(bomb.meshParts.first(), bomb.materials.first());
		}
		
		Model model = builder.end();
		
		gfx = new ModelInstance(model);  // ModelsLoader.singleton.get("tree")
		gfx.transform.setTranslation(new Vector3(pos, 0));
		gfx.nodes.forEach(n -> System.out.println("n name = ["+n.id+"]"));
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
