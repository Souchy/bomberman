package bombermantest.client.module.injectors;

import org.apache.mina.core.session.IoSession;

import bombermantest.client.module.network.game.in.ChatParser;
import bombermantest.client.module.network.game.in.EntityListParser;
import bombermantest.client.module.network.game.in.EntityParser;
import bombermantest.client.module.network.game.in.GameAuthentificationResultParser;
import bombermantest.client.module.network.game.in.GameStateParser;
import bombermantest.client.module.network.game.in.LostPlayerParser;
import bombermantest.client.module.network.game.in.MovePlayerListParser;
import bombermantest.client.module.network.game.in.SuicideParser;
import bombermantest.client.module.network.game.in.UseWeaponParser;
import bombermantest.client.module.network.game.out.ChatComposer;
import bombermantest.client.module.network.game.out.CommandComposer;
import bombermantest.client.module.network.game.out.GameAuthentificationComposer;
import bombermantest.client.module.network.game.out.MovePlayerComposer;
import bombermantest.client.module.network.game.out.UseWeaponComposer;
import bombermantest.network.packets.IPacket;
import bombermantest.network.packets.enums.GameClientPackets;

public final class GamePacketInjector {
	
	public static final void inject(){
		// Game
		GameClientPackets.AUTHENTIFICATION.setComposer(new GameAuthentificationComposer());
		GameClientPackets.AUTHENTIFICATION_RESULT.setParser(new GameAuthentificationResultParser());
		
		GameClientPackets.GAME_STATE.setParser(new GameStateParser());
		
		GameClientPackets.ENTITY.setParser(new EntityParser());
		GameClientPackets.ENTITY_LIST.setParser(new EntityListParser());
		GameClientPackets.LOST_PLAYER.setParser(new LostPlayerParser());
		
		//GameClientPackets.MOVE_PLAYER.setComposer(new MovePlayerComposer());
		GameClientPackets.USE_WEAPON.setComposer(new UseWeaponComposer());
		GameClientPackets.USE_WEAPON.setParser(new UseWeaponParser());
		
		GameClientPackets.CHAT.setParser(new ChatParser());
		GameClientPackets.CHAT.setComposer(new ChatComposer());
		GameClientPackets.COMMAND.setComposer(new CommandComposer());
		GameClientPackets.SUICIDE.setParser(new SuicideParser());
		
		GameClientPackets.MOVE_PLAYER.setComposer(new MovePlayerComposer());
		GameClientPackets.MOVE_PLAYER_LIST.setParser(new MovePlayerListParser());
	}
	
	public static void broadcast(IPacket p, IoSession session, Object... objects){
		p.compose(session, objects);
	}
	
	
}
