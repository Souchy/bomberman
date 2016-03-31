package bombermantest.game.network.login.in;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.game.main.BombermanTestGame;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.LoginGamePackets;

public class ClientTransferParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		try {
			long clientID = buf.getLong();
			String playerUsername = buf.getPrefixedString(decoder);
			BombermanTestGame.transferingClients.add(playerUsername);
			LoginGamePackets.CLIENT_TRANSFER_RESULT.compose(session, true, clientID);
		} catch (CharacterCodingException e) {
			//LoginGamePackets.CLIENT_TRANSFER_RESULT.compose(session, false);
			e.printStackTrace();
		}
		
		
	}

}
