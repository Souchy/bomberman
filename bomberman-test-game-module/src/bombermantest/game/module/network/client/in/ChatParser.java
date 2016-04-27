package bombermantest.game.module.network.client.in;

import java.nio.charset.CharacterCodingException;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.graphics.Color;

import bombermantest.game.network.game.client.GClientServer;
import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.ui.components.ChatboxArea;

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
			
			// Envoie le message à tous les joueurs de la même couleur (team) sauf à celui qui a écrit le message
			sessions.remove(session);
			GameClientPackets.CHAT.broadcast(sessions, message, color);
			
			ChatboxArea.get().setColor(new Color(color));
			ChatboxArea.get().addMessage(message);
			
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
	}

}
