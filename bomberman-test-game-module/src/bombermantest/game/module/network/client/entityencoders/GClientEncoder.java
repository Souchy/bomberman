package bombermantest.game.module.network.client.entityencoders;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;

import bombermantest.network.entities.EntityEncoder;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Composer;

public class GClientEncoder implements EntityEncoder<GClient> {

	@Override
	public void encode(GClient client, IoBuffer buf) {
		try {
			buf.putLong(client.id);
			buf.putPrefixedString(client.name, Composer.encoder);
			buf.putInt(client.wins);
			buf.putInt(client.kills);
			buf.putInt(client.deaths);
			buf.putEnum(client.state);
			buf.putInt(client.team);
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
	}
	
}
