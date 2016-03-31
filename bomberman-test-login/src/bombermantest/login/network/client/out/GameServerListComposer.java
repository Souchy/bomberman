package bombermantest.login.network.client.out;

import static com.mygdx.engine.network.server.ServerHandler.CLIENT_ATTR_KEY;

import java.util.Collection;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.login.network.login.game.LGame;
import bombermantest.login.network.login.game.LGameServer;
import bombermantest.network.packets.Composer;

public class GameServerListComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		//Stream<ServerClient> stream = Server.get().clients.stream().filter(c -> c.session.getAttribute(SESSION_TYPE_ATTR_KEY) == SessionType.GameServer);
		//int nbServers = (int) stream.count();
		
		System.out.println("Login composing GameServerListPacket");
		
		int nbServers = LGameServer.get().acceptor.getManagedSessionCount();
		
		buf.putInt(nbServers);
		
		Collection<IoSession> sessions = LGameServer.get().acceptor.getManagedSessions().values();
		
		sessions.forEach(s -> {
			System.out.println("nbServers = ["+nbServers+"]");
			try {
				LGame g = (LGame) s.getAttribute(CLIENT_ATTR_KEY);
				buf.putLong(s.getId());
				buf.putPrefixedString(g.serverName, encoder);
				buf.putPrefixedString(g.map, encoder);
				buf.putEnum(g.mode);
				buf.putInt(g.port);
				buf.put((byte) (g.serverPassword == "" ? 0 : 1)); // if the gserv has a password or not
				buf.put(g.nbPlayers);
				buf.put(g.nbCapacity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		
		System.out.println("Login sending GameServerListPacket");
		
		return buf;
	}

}
