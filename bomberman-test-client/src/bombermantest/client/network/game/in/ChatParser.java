package bombermantest.client.network.game.in;

import java.nio.charset.CharacterCodingException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.graphics.Color;

import bombermantest.client.ui.game.ChatHud;
import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.GameClientPackets;

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
