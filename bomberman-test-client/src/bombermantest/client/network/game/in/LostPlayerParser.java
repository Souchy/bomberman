package bombermantest.client.network.game.in;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.Gdx;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;
import bombermantest.ui.game.ScoreboardScreen;

public class LostPlayerParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		
		long sessionId = buf.getLong();
		
		if(GameClient.clients.containsKey(sessionId)){
			GClient client = GameClient.clients.remove(sessionId);
			client.dispose();
			Gdx.app.postRunnable(() -> {
				ScoreboardScreen.get().updateClientList(); 
			});
		}
		
	}

}
