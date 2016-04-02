package bombermantest.game.injectors;

import bombermantest.game.network.client.in.ChatParser;
import bombermantest.game.network.client.in.ClientAuthentificationParser;
import bombermantest.game.network.client.in.MovePlayerParser;
import bombermantest.game.network.client.in.UseWeaponParser;
import bombermantest.game.network.client.out.ChatComposer;
import bombermantest.game.network.client.out.ClientAutificationResultComposer;
import bombermantest.game.network.client.out.EntityComposer;
import bombermantest.game.network.client.out.EntityListComposer;
import bombermantest.game.network.client.out.GameStateComposer;
import bombermantest.game.network.client.out.LostPlayerComposer;
import bombermantest.game.network.client.out.MovePlayerListComposer;
import bombermantest.network.packets.enums.GameClientPackets;

public final class ClientPacketInjector {

	public static final void inject() {
		
		//Client
		GameClientPackets.AUTHENTIFICATION.setParser(new ClientAuthentificationParser());
		GameClientPackets.AUTHENTIFICATION_RESULT.setComposer(new ClientAutificationResultComposer());
		
		GameClientPackets.GAME_STATE.setComposer(new GameStateComposer());
		GameClientPackets.ENTITY.setComposer(new EntityComposer());
		GameClientPackets.ENTITY_LIST.setComposer(new EntityListComposer());
		GameClientPackets.LOST_PLAYER.setComposer(new LostPlayerComposer());
		
		//GameClientPackets.MOVE_PLAYER.setParser(new MovePlayerParser());
		GameClientPackets.USE_WEAPON.setParser(new UseWeaponParser());

		GameClientPackets.CHAT.setParser(new ChatParser());
		GameClientPackets.CHAT.setComposer(new ChatComposer());
		
		GameClientPackets.MOVE_PLAYER.setParser(new MovePlayerParser());
		GameClientPackets.MOVE_PLAYER_LIST.setComposer(new MovePlayerListComposer());
	}
	
}
