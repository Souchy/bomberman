package bombermantest.login.network.game.in;

import static com.mygdx.engine.network.server.ServerHandler.CLIENT_ATTR_KEY;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.login.network.login.client.LClientServer;
import bombermantest.login.network.login.game.LGame;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.LoginClientPackets;

public class ClientTransferResultParser implements Parser {
	
	@Override
	public void parse(IoSession session, IoBuffer buf) {
		byte accepted = buf.get();
		long clientID = buf.getLong();
		LGame game = (LGame) session.getAttribute(CLIENT_ATTR_KEY);
		IoSession clientSession = LClientServer.get().acceptor.getManagedSessions().get(clientID);
		LoginClientPackets.SERVER_SELECTION_RESULT.compose(clientSession, 
				accepted, 
				session.getId(), 
				((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress(),
				game.port
				);
	}

}
