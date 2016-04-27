package bombermantest.client.module.network.game.in;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.GameClientPackets;

public class EntityListParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		int nb = buf.getInt();
		for(int i = 0; i < nb; i++){
			GameClientPackets.ENTITY.parse(session, buf);
		}
	}

}
