package bombermantest.client.network.game.in;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.graphics.Color;

import bombermantest.client.ui.game.ChatHud;
import bombermantest.network.packets.Parser;

public class ChatParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		try {
			String message = buf.getPrefixedString(decoder);
			int color = buf.getInt();
			
			ChatHud.get().setColor(new Color(color));
			ChatHud.get().addMessage(message);
			
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
	}

}
