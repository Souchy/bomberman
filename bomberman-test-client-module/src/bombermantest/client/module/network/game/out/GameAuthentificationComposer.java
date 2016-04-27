package bombermantest.client.module.network.game.out;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.client.main.testClientConfig;
import bombermantest.network.packets.Composer;

public class GameAuthentificationComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {

		try {
			buf.putPrefixedString(testClientConfig.username, encoder);
			buf.putPrefixedString(testClientConfig.serverPassword, encoder);
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
		return buf;
	}

}
