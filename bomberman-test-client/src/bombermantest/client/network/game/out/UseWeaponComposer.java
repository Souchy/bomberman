package bombermantest.client.network.game.out;

import static com.mygdx.engine.configs.AConstants.putVector2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.math.Vector2;

import bombermantest.network.packets.Composer;

public class UseWeaponComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		
		Vector2 dir = (Vector2) objects[0];
		
		putVector2(buf, dir);
		
		return buf;
	}

}
