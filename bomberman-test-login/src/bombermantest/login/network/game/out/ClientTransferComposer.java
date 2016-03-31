package bombermantest.login.network.game.out;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;

public class ClientTransferComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		long clientID = (long) objects[0];
		String playerUsername = (String) objects[1];
		try {
			buf.putLong(clientID);
			buf.putPrefixedString(playerUsername, encoder);
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
		return buf;
	}

}
