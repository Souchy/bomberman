package bombermantest.client.module.network.game.entitydecoders;

import org.apache.mina.core.buffer.IoBuffer;

import com.badlogic.gdx.math.Vector2;

import bombermantest.client.main.ClientGame;
import bombermantest.client.network.client.game.GameClient;
import bombermantest.network.entities.EntityDecoder;
import bombermantest.objects.characters.playables.BombermanStats;
import bombermantest.objects.projectiles.NormalBomb;

public class NormalBombDecoder implements EntityDecoder<NormalBomb> {

	@Override
	public NormalBomb decode(IoBuffer buf) {
		long playerId = buf.getLong();
		long timeRemain = buf.getLong();
		
		BombermanStats stats = GameClient.clients.get(playerId).player.getBStats();
		
		NormalBomb bomb = new NormalBomb(ClientGame.get(), Vector2.Zero, stats);
		bomb.timeRemaining = timeRemain;
		
		//bomb.prePublish();
		
		return bomb;
	}

}
