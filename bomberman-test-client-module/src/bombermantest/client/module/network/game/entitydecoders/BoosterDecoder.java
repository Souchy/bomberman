package bombermantest.client.module.network.game.entitydecoders;

import org.apache.mina.core.buffer.IoBuffer;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.engine.objects.items.Booster;

import bombermantest.client.main.ClientGame;
import bombermantest.network.entities.EntityDecoder;
import bombermantest.objects.buffs.BuffType;

public class BoosterDecoder implements EntityDecoder<Booster> {

	@Override
	public Booster decode(IoBuffer buf) {
		BuffType type = buf.getEnum(BuffType.class);
		Booster booster = new Booster(ClientGame.get(), Vector3.Zero, type);
		//booster.prePublish();
		return booster;
	}
	
}
