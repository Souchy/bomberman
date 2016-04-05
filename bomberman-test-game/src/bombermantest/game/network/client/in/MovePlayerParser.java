package bombermantest.game.network.client.in;

import static com.mygdx.engine.configs.AConstants.getVector2;
import static com.mygdx.engine.configs.AConstants.getVector3;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;

public class MovePlayerParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		GClient client = (GClient) session.getAttribute(ServerHandler.CLIENT_ATTR_KEY);

		Vector2 pos = getVector2(buf, new Vector2());
		getVector3(buf, client.player.dirAngles);
		
		Gdx.app.postRunnable(() -> {
			client.player.body.setTransform(pos.x, pos.y, client.player.body.getAngle());
		});
	}

}
