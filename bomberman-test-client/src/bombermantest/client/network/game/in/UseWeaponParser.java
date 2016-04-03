package bombermantest.client.network.game.in;


import static com.mygdx.engine.configs.AConstants.*;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.math.Vector2;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;

public class UseWeaponParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		long id = buf.getLong();
		GClient client = TestGame.get().getClient(id);
		Vector2 pos = client.player.getPos();
		Vector2 dir = getVector2(buf, new Vector2());
		
		if(id == GameClient.myid){
			System.out.println("[Client] Dont use weapon when receiving packet with my own id");
		}
		
		client.player.weapon.attack(TestGame.get(), pos, dir, client.player.getBStats());
	}

}
