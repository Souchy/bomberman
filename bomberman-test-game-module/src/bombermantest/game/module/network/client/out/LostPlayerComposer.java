package bombermantest.game.module.network.client.out;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;

public class LostPlayerComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		long sessionID = (long) objects[0];
		
		buf.putLong(sessionID);
		
		return buf;
	}

}
