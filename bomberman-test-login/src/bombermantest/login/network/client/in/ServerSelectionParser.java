package bombermantest.login.network.client.in;

import static bombermantest.network.handlers.ServerHandler.CLIENT_ATTR_KEY;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.login.network.login.client.LClient;
import bombermantest.login.network.login.game.LGameServer;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.LoginGamePackets;

public class ServerSelectionParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		LClient client = (LClient) session.getAttribute(CLIENT_ATTR_KEY);
		long serverID = buf.getLong();
		IoSession serverSession = LGameServer.get().acceptor.getManagedSessions().get(serverID);
		LoginGamePackets.CLIENT_TRANSFER.compose(serverSession, session.getId(), client.username);
	}

}
