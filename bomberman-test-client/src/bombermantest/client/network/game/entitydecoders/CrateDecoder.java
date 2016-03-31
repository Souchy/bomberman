package bombermantest.client.network.game.entitydecoders;

import org.apache.mina.core.buffer.IoBuffer;

import com.badlogic.gdx.math.Vector3;

import bombermantest.client.main.ClientGame;
import bombermantest.network.entities.EntityDecoder;
import bombermantest.objects.items.scenery.solid.Crate;

public class CrateDecoder implements EntityDecoder<Crate> {

	@Override
	public Crate decode(IoBuffer buf) {
		Crate crate = new Crate(ClientGame.get(), Vector3.Zero);
		//crate.prePublish();
		return crate;
	}

}
