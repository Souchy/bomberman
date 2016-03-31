package bombermantest.client.network.login.in;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.client.network.client.game.CGame;
import bombermantest.client.ui.login.GameServerListScreen;
import bombermantest.enums.GameMode;
import bombermantest.network.packets.Parser;


public class GameServerListParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		System.out.println("Client in parse gameserver list");
		
		//System.out.println("packet : "+buf.limit()+", "+buf.remaining()+", "+buf.position()+", "+buf.getUnsignedShort(9)+", "+buf);
		//System.out.println("short : "+buf.getUnsignedShort(8)+", "+buf.getUnsignedShort(7)+", "+buf.getUnsignedShort(8)+", "+buf.getUnsignedShort(9)+", "+buf.getUnsignedShort(10)+", "+buf.getUnsignedShort(11)+", "+buf.getUnsignedShort(12)+", "+buf.getUnsignedShort(13)+", "+buf.getUnsignedShort(14)+", "+buf.getUnsignedShort(15)+", ");
		//System.out.println("short : "+buf.getShort(8)+", "+buf.getShort(7)+", "+buf.getShort(8)+", "+buf.getShort(9)+", "+buf.getShort(10)+", "+buf.getShort(11)+", "+buf.getShort(12)+", "+buf.getShort(13)+", "+buf.getShort(14)+", "+buf.getShort(15)+", ");
		
		GameServerListScreen.get().clearList();
		try {
			int nbServers = buf.getInt();
			System.out.println("Client : nbServers = ["+nbServers+"]");
			for (int i = 0; i < nbServers; i++) {
				CGame server = new CGame();
				server.serverID = buf.getLong();
				server.serverName = buf.getPrefixedString(decoder);
				server.map = buf.getPrefixedString(decoder);
				server.mode = buf.getEnum(GameMode.class);
				server.port = buf.getInt();
				server.hasPassword = buf.get() == 1;
				server.nbPlayers = buf.get();
				server.nbCapacity = buf.get();
				//LoginClient.gameServers.put(server.serverID, server);
				GameServerListScreen.get().add(server);
			}
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
	}

}
