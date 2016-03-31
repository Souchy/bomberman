package bombermantest.game.network.client.in;

import java.nio.charset.CharacterCodingException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.graphics.Color;

import bombermantest.game.network.game.client.GClientServer;
import bombermantest.game.ui.ChatHud;
import bombermantest.main.TestGame;
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
			
			Collection<IoSession> sessions = GClientServer.get().getSessionList();
			if(color != Color.rgba8888(Color.YELLOW)){
				sessions = GClientServer.get().getSessionList().stream()
						   .filter(s -> ((GClient) s.getAttribute(ServerHandler.CLIENT_ATTR_KEY)).team == color).collect(Collectors.toList());
			}
			GameClientPackets.CHAT.broadcast(sessions, message, color);
			
			ChatHud.get().setColor(new Color(color));
			ChatHud.get().addMessage(message);
			
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
	}

}
