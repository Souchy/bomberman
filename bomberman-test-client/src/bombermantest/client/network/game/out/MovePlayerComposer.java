package bombermantest.client.network.game.out;

import static com.mygdx.engine.configs.AConstants.putVector2;
import static com.mygdx.engine.configs.AConstants.putVector3;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;
import bombermantest.objects.characters.playables.BPlayer;

public class MovePlayerComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		BPlayer player = (BPlayer) objects[0];
		
		putVector2(buf, player.getPos()); // 2 * 4
		putVector3(buf, player.dirAngles); // 3 * 4
		
		return buf;
	}

}
