package bombermantest.game.module.network.client.entityencoders;

import org.apache.mina.core.buffer.IoBuffer;

import bombermantest.network.entities.EntityEncoder;
import bombermantest.objects.characters.playables.BombermanStats;
import bombermantest.objects.projectiles.NormalBomb;

public class NormalBombEncoder implements EntityEncoder<NormalBomb> {

	@Override
	public void encode(NormalBomb bomb, IoBuffer buf) {
		BombermanStats stats = (BombermanStats) bomb.getWeaponHolderStats();
		buf.putLong(stats.player.client.id);
		buf.putLong(bomb.timeRemaining);
	}

}
