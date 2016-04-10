package bombermantest.client.network.game.in;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.mygdx.engine.events.PlayerDeathEvent;

import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;

public class SuicideParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		long id = buf.getLong();
		
		GClient client = TestGame.get().getClient(id);
		//client.player.getBStats().life = 0; //déplacé dans le playerDeathEventListener
		PlayerDeathEvent.post(client.player, client.player);
		
	}

}
