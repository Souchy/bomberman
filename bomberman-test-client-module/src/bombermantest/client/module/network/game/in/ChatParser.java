package bombermantest.client.module.network.game.in;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.graphics.Color;

import bombermantest.network.packets.Parser;
import bombermantest.ui.components.ChatboxArea;

public class ChatParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		try {
			String message = buf.getPrefixedString(decoder);
			int color = buf.getInt();
			
			//ChatboxArea.get().setColor(new Color(color));
			ChatboxArea.get().addMessage(message);
			
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
	}

}
