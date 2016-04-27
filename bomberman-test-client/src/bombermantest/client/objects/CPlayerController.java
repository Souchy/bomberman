package bombermantest.client.objects;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.objects.weapons.Weapon;

import bombermantest.client.main.ClientGame;
import bombermantest.client.network.client.game.GameClient;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.objects.characters.playables.BPlayer;
import bombermantest.objects.characters.playables.PlayerController;

public class CPlayerController extends PlayerController {

	public CPlayerController(BPlayer player) {
		super(player);
		
		//ScheduledExecutorService serv = Executors.newSingleThreadScheduledExecutor();
		//serv.scheduleAtFixedRate(this::sendPos2, 1/60L, 1/60L, TimeUnit.SECONDS); // délai entre le début de chaque action
		//serv.scheduleWithFixedDelay(this::sendPos2, 1/60L, 1/60L, TimeUnit.SECONDS); //délai entre la fin d'une action et le début de la prochaine
	}
	
	/**
	 * Hook to be implemented
	 * @param weapon
	 * @param pos 
	 * @param u 
	 */
	protected void sendUseWeapon(Weapon weapon, Vector2 pos, Vector2 u){
		//GameClientPackets.USE_WEAPON.compose(GameClient.get().session, weapon, pos, u);
		GameClientPackets.USE_WEAPON.compose(GameClient.get().session, u);
	}
	
	/**
	 * Hook to be implemented
	 */
	protected void sendPos(){
		GameClientPackets.MOVE_PLAYER.compose(GameClient.get().session, ClientGame.get().universe.player);
	}
	

}
