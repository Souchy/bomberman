package bombermantest.login.network.game.in;

import static bombermantest.network.handlers.ServerHandler.CLIENT_ATTR_KEY;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.enums.GameMode;
import bombermantest.login.network.login.game.LGame;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.LoginGamePackets;

public class GameAuthentificationParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		boolean accepted = false;
		try {
			LGame g = (LGame) session.getAttribute(CLIENT_ATTR_KEY);
			g.playerUsername = buf.getPrefixedString(decoder);
			g.playerPassword = buf.getPrefixedString(decoder);
			g.serverName = buf.getPrefixedString(decoder);
			g.serverPassword = buf.getPrefixedString(decoder);
			g.map = buf.getPrefixedString(decoder);
			g.mode = buf.getEnum(GameMode.class);
			g.port = buf.getInt();
			g.nbCapacity = buf.get();
			
			System.out.println("player username = ["+g.playerUsername+"]");
			System.out.println("player password = ["+g.playerPassword+"]");
			System.out.println("game name = ["+g.serverName+"]");
			System.out.println("game password = ["+g.serverPassword+"]");
			
			accepted = true;
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}

		LoginGamePackets.AUTHENTIFICATION_RESULT.compose(session, accepted);
	}

}
