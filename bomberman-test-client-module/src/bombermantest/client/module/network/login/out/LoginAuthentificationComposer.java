package bombermantest.client.module.network.login.out;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.client.main.testClientConfig;
import bombermantest.network.packets.Composer;

public class LoginAuthentificationComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		//IoBuffer buf = LoginPlayerPacket.AUTHENTIFICATION.allocateAutoExpand(); //.allocate(); // puts packetlength and enum
		//System.out.println("Client Auth composing,,, username = ["+testClientConfig.username+"],,, password = ["+testClientConfig.password+"]");
		try {
			buf.putPrefixedString(testClientConfig.username, encoder);
			buf.putPrefixedString(testClientConfig.password, encoder);
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Client Auth sending, limit = ["+buf.limit()+"]");
		
		return buf;
	}

}
