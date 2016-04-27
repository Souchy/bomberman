package bombermantest.game.module.network.login.out;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;

public class ClientTransferResultComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		boolean accepted = (boolean) objects[0];
		long clientID = (long) objects[1];
		
		buf.put((byte) (accepted ? 1 : 0));
		buf.putLong(clientID);
		
		return buf;
	}

}
