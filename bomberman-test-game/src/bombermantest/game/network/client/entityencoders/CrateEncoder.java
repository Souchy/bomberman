package bombermantest.game.network.client.entityencoders;

import org.apache.mina.core.buffer.IoBuffer;

import bombermantest.network.entities.EntityEncoder;
import bombermantest.objects.items.scenery.solid.Crate;

public class CrateEncoder implements EntityEncoder<Crate> {

	@Override
	public void encode(Crate object, IoBuffer buf) {
		// TODO Auto-generated method stub
		// rien askip
	}

}
