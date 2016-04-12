package bombermantest.objects.items.scenery.solid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.configs.AConstants.Platform;
import com.mygdx.engine.events.ScoreEvents;
import com.mygdx.engine.game.AGame;
import com.mygdx.engine.objects.characters.EntityStats;
import com.mygdx.engine.objects.items.Booster;
import com.mygdx.engine.objects.items.scenery.Block2d;
import com.mygdx.engine.objects.projectiles.Projectile;

import bombermantest.events.CrateDropEvent;
import bombermantest.generation.Generation;
import bombermantest.objects.buffs.BuffType;

public class Crate extends Block2d {
	
	private static ColorAttribute colorAttr = new ColorAttribute(ColorAttribute.Diffuse, Color.BROWN);

	public static EntityStats stats = new EntityStats();
	static{
		stats.defense = 0;
	}

	public Crate(AGame game, Vector3 pos) {
		super(game, pos, "tilewhite1");
		gfx.materials.peek().set(colorAttr);
	}
	

	@Override
	public EntityStats getStats(){
		return stats;
	}


	@Override
	public void onHitByProjectile(Projectile p) {
		if(getStats().hitAndDie(p.getDamage(), this)){
			ScoreEvents.AddSceneryBlockDestructionEvent.post();
			
			if(AConstants.rnd.nextInt(3) == 0 && AGame.platform == Platform.Server){
				Booster booster = new Booster(game, getGfxPos(), BuffType.randomBuffType());
				booster.publish();
				CrateDropEvent.post(booster);
			}
			
			if(AGame.platform == Platform.Server) Generation.currentMap.removeValue(this, true);
			dispose();
		}
	}

	
}
