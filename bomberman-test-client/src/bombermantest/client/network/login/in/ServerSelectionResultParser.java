package bombermantest.client.network.login.in;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.network.packets.Parser;

public class ServerSelectionResultParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		try {
			byte accepted = buf.get();
			@SuppressWarnings("unused") 
			long serverID = buf.getLong();
			String ip = buf.getPrefixedString(decoder);
			short port = (short) buf.getInt();
			
			if(accepted == 1){
				GameClient.get().ip = ip;
				GameClient.get().port = port;
				GameClient.get().on();
			}
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
	}

}
