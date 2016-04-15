package bombermantest.objects.characters.playables;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.badlogic.gdx.Input.Keys.G;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.Q;
import static com.badlogic.gdx.Input.Keys.R;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.SHIFT_LEFT;
import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.badlogic.gdx.Input.Keys.UP;
import static com.badlogic.gdx.Input.Keys.W;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.engine.configs.Test;
import com.mygdx.engine.objects.characters.playables.AController;
import com.mygdx.engine.objects.weapons.Weapon;

import bombermantest.ui.components.ChatboxEntry;

public class PlayerController extends AController {

	private BPlayer p;
	private Body body;
	private BombermanStats stats;
	private Weapon weapon;
	private Vector2 u = Vector2.Zero.cpy();

	public float weaponCooldown = 0;
	public float equipCooldown = 0;
	
	private Vector2 buffer = new Vector2(0, 0);
	
	public PlayerController(BPlayer player) {
		super(player);
		p = player;
		body = p.body;
		stats = (BombermanStats) p.getStats();
		weapon = p.weapon;
		//equip = p.equip;
	}
	
	
	@Override
	public void control(float delta) {
		
		if(ChatboxEntry.focused){
			return; // Désactive les contrôles du personnage si le focus est donné au chatboxentry (= la personne est en train d'écrire)
		}

		/*equipCooldown -= delta;
		if(Gdx.input.isKeyPressed(SHIFT_LEFT) && equip != null && equipCooldown <= 0){
			equipCooldown = equip.getCooldown();
			equip.takeEffect(game, p.getPos());
		}*/
		 
		
		// Actual key board controls
		//if(Gdx.input.isKeyPressed(Z)) p.dirAngles.x += 90;
		//if(Gdx.input.isKeyPressed(X)) p.dirAngles.y += 90;
		//if(Gdx.input.isKeyPressed(C)) p.dirAngles.z += 90;
		//if(Gdx.input.isKeyPressed(M)) p.equipMount(Game3d.testmount);
		if(Gdx.input.isKeyPressed(Q)) Test.get().drawDebug = !Test.get().drawDebug;
		if(Gdx.input.isKeyPressed(G)) Test.get().drawGrid = !Test.get().drawGrid;
		//if(Gdx.input.isKeyPressed(Y)) System.out.println("camry y : [" + screen.cam.position.y + "]");
		//if(Gdx.input.isKeyPressed(X)) System.out.println("camry x : [" + screen.cam.position.x + "]");
		//if(Gdx.input.isKeyPressed(C)) crouch = true;
		if(Gdx.input.isKeyPressed(R)){
			//body.setType(BodyType.KinematicBody);
			body.setTransform(Vector2.Zero, 0); // WOW
			if(p.mount != null)	p.mount.body.setTransform(Vector2.Zero, 0); // WOW
			//body.setType(BodyType.DynamicBody);
		}
		
		if(stats.life <= 0){
			
			
			return; // désactive certains contrôles si le player est mort
		}
		
		/*if(Gdx.input.isKeyPressed(NUM_1)) weapon = p.weapon = new Gun();
		if(Gdx.input.isKeyPressed(NUM_2)) weapon = p.weapon = new ShotGun();
		if(Gdx.input.isKeyPressed(NUM_3)) weapon = p.weapon = new MachineGun();
		if(Gdx.input.isKeyPressed(NUM_4)) weapon = p.weapon = new GodGun();
		if(Gdx.input.isKeyPressed(NUM_5)) weapon = p.weapon = new Bazooka();
		if(Gdx.input.isKeyPressed(NUM_6)) weapon = p.weapon = new C4();
		if(Gdx.input.isKeyPressed(NUM_7)) weapon = p.weapon = new EnergyShield();
		if(Gdx.input.isKeyPressed(NUM_8)) weapon = p.weapon = new Sword();
		if(Gdx.input.isKeyPressed(NUM_9)) weapon = p.weapon = new Staff();
		if(Gdx.input.isKeyPressed(NUM_0)) weapon = p.weapon = new Bow();*/
		
		u.set(0, 0);
		if(Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)){
			u.add(-1, 0); 
		}
		if(Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)){
			u.add(1, 0); 
		}
		if(Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)){
			u.add(0, 1);
		}
		if(Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)){
			u.add(0, -1);  
		}

		weaponCooldown -= delta;
		if(Gdx.input.isKeyPressed(SPACE) && weapon != null && stats.nbBombs >= weapon.getAmmoCost() && weaponCooldown <= 0){
			stats.nbBombs -= weapon.getAmmoCost(); //p.ammo
			weaponCooldown = weapon.getCooldown();
			//weapon.attack(game, p.getPos(), u, stats); pas besoin de faire l'attack ici, le server va renvoyer un packet qui va mettre le weapon et attaquer
			//GameClientPackets.USE_WEAPON.compose(GameClient., objects);
			//ClientSender.sendWeaponUse(weapon, p.getPos(), u); //, stats);
			//if(AGame.platform != AConstants.Platform.Server) 
				sendUseWeapon(weapon, p.getPos(), u);
		}
		
		// 
		if(u.x <= -1 && u.y == 0) p.dirAngles.y = 270; else
		if(u.x <= -1 && u.y >= 1) p.dirAngles.y = 270 - 45; else
		if(u.x <= -1 && u.y <= -1) p.dirAngles.y = 270 + 45; else
		
		if(u.x >= 1 && u.y == 0) p.dirAngles.y = 90; else
		if(u.x >= 1 && u.y >= 1) p.dirAngles.y = 90 + 45; else
		if(u.x >= 1 && u.y <= -1) p.dirAngles.y = 90 - 45; else
		
		if(u.x == 0 && u.y == 0) p.dirAngles.y = p.dirAngles.y; else
		if(u.x == 0 && u.y >= 1) p.dirAngles.y = 180; else
		if(u.x == 0 && u.y <= -1) p.dirAngles.y = 0;
		


		Vector2 pos = body.getPosition();
		
		if(Gdx.input.isKeyPressed(SHIFT_LEFT)){
			buffer.x += stats.getTotSpeed() * u.x;
			buffer.y += stats.getTotSpeed() * u.y;
			if(buffer.x >= 1){
				body.setTransform(pos.x + 1, pos.y, body.getAngle());
				buffer.x = 0;
			}
			if(buffer.y >= 1){
				buffer.y = 0;
				body.setTransform(pos.x, pos.y + 1, body.getAngle());
			}
			if(buffer.x <= -1){
				buffer.x = 0;
				body.setTransform(pos.x - 1, pos.y, body.getAngle());
			}
			if(buffer.y <= -1){
				buffer.y = 0;
				body.setTransform(pos.x, pos.y - 1, body.getAngle());
			}
		}else{
			body.setTransform(pos.x + stats.getTotSpeed() * u.x * delta, pos.y + stats.getTotSpeed() * u.y * delta, body.getAngle());
			
			//ClientSender.sendPos(game.universe.player);
			//if(AGame.platform != AConstants.Platform.Server) 
				sendPos();
			
			//System.out.println("delta = ["+(delta*1000)+"], dTime = ["+dTime+"]");
			//System.out.println("fps controller : ["+(1/delta)+"], fpsTime = ["+(1000/dTime)+"], pos = ["+pos+"], accelForce = ["+stats.accelForce+"], u = ["+u+"]");
		}
		//body.applyForceToCenter(u.scl(stats.accelForce), true);
	}

	/**
	 * Hook to be implemented
	 * @param weapon
	 * @param pos 
	 * @param u 
	 */
	protected void sendUseWeapon(Weapon weapon, Vector2 pos, Vector2 u){
		
	}
	
	/**
	 * Hook to be implemented
	 */
	protected void sendPos(){
		
	}

}
