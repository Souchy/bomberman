package bombermantest.game.network.client.out;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;

public class SuicideComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		long id = (long) objects[0];
		
		buf.putLong(id);
		
		return buf;
	}

}
