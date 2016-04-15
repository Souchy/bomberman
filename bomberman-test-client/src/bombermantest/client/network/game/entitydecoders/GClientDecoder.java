package bombermantest.client.network.game.entitydecoders;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;

import com.badlogic.gdx.Gdx;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.enums.ClientState;
import bombermantest.network.entities.EntityDecoder;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;
import bombermantest.ui.game.Scoreboard;

public class GClientDecoder implements EntityDecoder<GClient> {
	
	@Override
	public GClient decode(IoBuffer buf) {
		GClient client = new GClient();
		try {
			client.id = buf.getLong();
			client.name = buf.getPrefixedString(Parser.decoder);
			client.wins = buf.getInt();
			client.kills = buf.getInt();
			client.deaths = buf.getInt();
			client.state = buf.getEnum(ClientState.class);
			client.team = buf.getInt();
			
			Gdx.app.postRunnable(() -> 
				Scoreboard.get().updateClientList()//.add(client)
			);
			
			
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
		GameClient.clients.put(client.id, client);
		
		return client;
	}

}
