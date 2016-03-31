package bombermantest.login.network.client.out;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;

public class ServerSelectionResultComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		try {
			byte accepted = (byte) objects[0];
			long serverID = (long) objects[1];
			String ip = (String) objects[2];
			int port = (int) objects[3];
			
			buf.put(accepted);
			buf.putLong(serverID);
			buf.putPrefixedString(ip, encoder);
			buf.putInt(port);
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
		return buf;
	}

}
