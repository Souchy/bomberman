package bombermantest.game.module.network.client.entityencoders;

import org.apache.mina.core.buffer.IoBuffer;

import bombermantest.network.entities.EntityEncoder;
import bombermantest.objects.characters.playables.BPlayer;

public class PlayerEncoder implements EntityEncoder<BPlayer> {

	@Override
	public void encode(BPlayer player, IoBuffer buf) {
		buf.putLong(player.client.id);
		
		buf.putInt(player.getBStats().nbBombsMax);
		buf.putInt(player.getBStats().nbBombs);
		buf.putInt(player.getBStats().power);
		buf.putFloat(player.getBStats().speed);
		
	}

}
