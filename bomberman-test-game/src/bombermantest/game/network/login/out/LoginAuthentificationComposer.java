package bombermantest.game.network.login.out;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.enums.GameMode;
import bombermantest.game.main.testGameConfig;
import bombermantest.network.packets.Composer;

public class LoginAuthentificationComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		
		try {
			buf.putPrefixedString(testGameConfig.playerUsername, encoder); // le joueur doit avoir un compte valide pour lancer un serveur
			buf.putPrefixedString(testGameConfig.playerPassword, encoder); // le joueur doit avoir un compte valide pour lancer un serveur
			buf.putPrefixedString(testGameConfig.serverName, encoder);
			buf.putPrefixedString(testGameConfig.serverPassword, encoder);
			buf.putPrefixedString(testGameConfig.map, encoder);
			buf.putEnum(GameMode.valueOf(testGameConfig.mode));
			buf.putInt(testGameConfig.port);
			buf.put(testGameConfig.nbCapacity);
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
		return buf;
	}
	
}
