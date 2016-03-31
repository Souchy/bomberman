package bombermantest.login.network.client.in;

import static bombermantest.network.handlers.ServerHandler.CLIENT_ATTR_KEY;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.login.network.login.client.LClient;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.LoginClientPackets;

public class ClientAuthentificationParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		LClient client = (LClient) session.getAttribute(CLIENT_ATTR_KEY);
		boolean accepted = false;
		try {
			client.username = buf.getPrefixedString(decoder);
			String password = buf.getPrefixedString(decoder);
			
			System.out.println("username = ["+client.username+"]");
			System.out.println("password = ["+password+"]");
			
			accepted = true;
			
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}

		LoginClientPackets.AUTHENTIFICATION_RESULT.compose(session, accepted);
		
	}

}
