package bombermantest.objects.characters.playables;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Shape;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.configs.Test;
import com.mygdx.engine.game.AGame;
import com.mygdx.engine.objects.characters.EntityStats;
import com.mygdx.engine.objects.characters.playables.APlayer;
import com.mygdx.engine.objects.characters.playables.mounts.Mount;
import com.mygdx.engine.objects.weapons.Weapon;
import com.mygdx.engine.services.ModelsLoader;

import bombermantest.network.objects.GClient;
import bombermantest.objects.weapons.BombPlacer;

public class BPlayer extends APlayer {

	public static Vector3 boxV = new Vector3(9, 8, 14).scl(AConstants.scale);
	public static float volume = boxV.x * boxV.y * boxV.z;
	public static float mass = 60 * AConstants.scale;
	public static float stepHeight = 5.0f * AConstants.scale;
	

	public GClient client;
	private BombermanStats bstats = new BombermanStats();
	
	{
		getStats().density = mass / volume;
		getStats().attack = 0;
		getStats().defense = 0;
		getStats().life = 20;
		getStats().maxLife = 100;
		getStats().accelForce = new Vector2(3, 3); // vitesse en mètres / sec
		//getStats().power = 1;
		//getStats().nbBombs = 1;
	}
	
	
	/** for infinite ammo : set ammo cost of weapon to 0, or set ammo to Integer.MAX_VALUE each update() */
	//public int ammo = 1;
	public Weapon weapon = new BombPlacer();
	
	public BPlayer(AGame game, Vector3 pos) {
		super(game, pos);
		gfx = new ModelInstance(ModelsLoader.singleton.get(Test.get().playerskin));
		dirAngles.x = 90;
		//dirAngles.z = 90;
		gfx.transform.setTranslation(pos);
		//gfx.transform.rotate(Vector3.X, 90);
		//gfx.transform.rotate(Vector3.Y, 90);
		gfx.userData = this;
		bstats.player = this;
	}

	@Override
	protected void build(){
		super.build();
		body.setType(BodyType.DynamicBody);
		if(game.universe.player == this) controller = new PlayerController(this); // pas de controller si ce player n'est pas le 'main' player.
		//equipMount(Game3d.testmount);
	}


	@Override
	public void equipMount(Mount m) { }

	@Override
	public void unequipMount() { }

	@Override
	public Vector3 getBox() {
		return boxV;
	}
	

	@Override
	public Shape getShape(){
		return AConstants.capsuleShape(getBox().x, getBox().y, -getBox().x/2, -getBox().y/2);
	}
	
	public BombermanStats getBStats(){
		return bstats;
	}

	@Override
	public EntityStats getStats() {
		return bstats;
	}

}
