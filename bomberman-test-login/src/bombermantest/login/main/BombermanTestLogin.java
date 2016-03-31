package bombermantest.login.main;

import java.nio.charset.CharacterCodingException;

import bombermantest.login.network.login.client.LClientHandler;
import bombermantest.login.network.login.client.LClientServer;
import bombermantest.login.network.login.game.LGameHandler;
import bombermantest.login.network.login.game.LGameServer;
import bombermantest.login.network.packets.ClientPacketInjector;
import bombermantest.login.network.packets.GamePacketInjector;

public class BombermanTestLogin {
	

	public static void main (String[] arg) throws CharacterCodingException {
		/*IoBuffer buf = LoginClientPackets.GAME_SERVER_LIST.allocateAutoExpand();
		buf.putInt(69);
		buf.putPrefixedString("Sushi Shop", encoder);
		buf.flip();
		buf.putInt(0, buf.limit() - 4);
		
		int length = buf.getInt();
		LoginClientPackets enu = buf.getEnum(LoginClientPackets.class);
		int nbServ = buf.getInt();
		short stringPrefix = buf.getShort(buf.position());
		String servName = buf.getPrefixedString(decoder);
		System.out.printf("length=[%d], enum=[%s], nbServ=[%d], stringPrefix=[%d], servName=[%s]\n", length, enu, nbServ, stringPrefix, servName);
		
		System.exit(0);*/
		
		GamePacketInjector.inject();
		ClientPacketInjector.inject();
		
		LClientServer.get().setHandler(new LClientHandler());
		LGameServer.get().setHandler(new LGameHandler());
		
		LClientServer.get().on();
		LGameServer.get().on();
	}

}
