package bombermantest.game.network.client.out;

import static com.mygdx.engine.configs.AConstants.putVector2;
import static com.mygdx.engine.configs.AConstants.putVector3;

import java.util.Collection;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.enums.ClientState;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Composer;

public class MovePlayerListComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		@SuppressWarnings("unchecked")
		Collection<GClient> clients = (Collection<GClient>) objects[0];
		
		int nb = (int) clients.stream().filter(c -> c.state == ClientState.PLAYING).count();
		
		buf.putInt(nb);
		
		for(GClient client : clients){
			if(client.state == ClientState.PLAYING){
				buf.putLong(client.player.client.id); // 8
				putVector2(buf, client.player.getPos()); // 2  * 4
				putVector3(buf, client.player.dirAngles); // 3 * 4
			}
		}
		
		return buf;
	}

}
