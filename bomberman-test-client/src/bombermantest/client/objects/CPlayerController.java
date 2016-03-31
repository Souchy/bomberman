package bombermantest.client.objects;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.objects.weapons.Weapon;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.objects.characters.playables.BPlayer;
import bombermantest.objects.characters.playables.PlayerController;

public class CPlayerController extends PlayerController {

	public CPlayerController(BPlayer player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Hook to be implemented
	 * @param weapon
	 * @param pos 
	 * @param u 
	 */
	protected void sendUseWeapon(Weapon weapon, Vector2 pos, Vector2 u){
		GameClientPackets.USE_WEAPON.compose(GameClient.get().session, weapon, pos, u);
	}
	
	/**
	 * Hook to be implemented
	 */
	protected void sendPos(){
	//	GameClientPackets.MOVE_PLAYER.compose(GameClient.get().session, ClientGame.get().universe.player);
	}
	

}
