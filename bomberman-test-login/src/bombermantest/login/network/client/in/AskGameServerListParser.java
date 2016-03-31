package bombermantest.login.network.client.in;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.LoginClientPackets;

public class AskGameServerListParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		LoginClientPackets.GAME_SERVER_LIST.compose(session);
	}

}
