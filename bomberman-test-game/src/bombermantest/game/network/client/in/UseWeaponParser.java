package bombermantest.game.network.client.in;

import static com.mygdx.engine.configs.AConstants.getVector2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.math.Vector2;

import bombermantest.game.network.game.client.GClientServer;
import bombermantest.main.TestGame;
import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.GameClientPackets;

public class UseWeaponParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		//long id = buf.getLong();
		
		GClient client = (GClient) session.getAttribute(ServerHandler.CLIENT_ATTR_KEY);
		
		Vector2 pos = client.player.getPos();
		Vector2 dir = getVector2(buf, new Vector2());
		
		client.player.weapon.attack(TestGame.get(), pos, dir, client.player.getBStats());
		
		GameClientPackets.USE_WEAPON.broadcast(GClientServer.get().getSessionList(), client.id, dir);
	}

}
