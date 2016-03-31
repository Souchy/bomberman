package bombermantest.client.network.login.out;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;

public class ServerSelectionComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		long serverID = (long) objects[0];
		buf.putLong(serverID);
		return buf;
	}

}
