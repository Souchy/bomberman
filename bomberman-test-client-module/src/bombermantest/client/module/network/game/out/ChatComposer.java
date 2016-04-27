package bombermantest.client.module.network.game.out;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;

public class ChatComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		String message = (String) objects[0];
		int color = (int) objects[1];
		
		try {
			buf.putPrefixedString(message, encoder);
			buf.putInt(color);
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
			
		return buf;
	}
	
}
