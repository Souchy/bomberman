package bombermantest.game.network.client.entityencoders;

import org.apache.mina.core.buffer.IoBuffer;

import com.mygdx.engine.objects.items.Booster;

import bombermantest.network.entities.EntityEncoder;
import bombermantest.objects.buffs.BuffType;

public class BoosterEncoder implements EntityEncoder<Booster> {

	@Override
	public void encode(Booster item, IoBuffer buf) {
		BuffType type = BuffType.valueOf(item.getType().name());
		buf.putEnum(type);
	}

}
