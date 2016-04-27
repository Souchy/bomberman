package bombermantest.client.module.network.game.entitydecoders;

import org.apache.mina.core.buffer.IoBuffer;

import com.badlogic.gdx.math.Vector3;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.client.objects.CPlayer;
import bombermantest.main.TestGame;
import bombermantest.network.entities.EntityDecoder;
import bombermantest.network.objects.GClient;
import bombermantest.objects.characters.playables.BPlayer;
import bombermantest.objects.characters.playables.BombermanStats;

public class PlayerDecoder implements EntityDecoder<BPlayer> {

	@Override
	public BPlayer decode(IoBuffer buf) {
		long id = buf.getLong();
		
		GClient client = GameClient.clients.get(id);
		
		System.out.println("Decoding Player : id=["+id+"], myid=["+GameClient.myid+"]");
		
		if(id == GameClient.myid){
			client.player = new CPlayer(TestGame.get(), Vector3.Zero);
			TestGame.get().universe.player = client.player;
		}else{
			client.player = new BPlayer(TestGame.get(), Vector3.Zero);
		}
		
		
		BPlayer p = client.player; 
		BombermanStats stats = p.getBStats();
		
		p.client = client;
		stats.nbBombsMax = buf.getInt();
		stats.nbBombs = buf.getInt();
		stats.power = buf.getInt();
		stats.speed = buf.getFloat();
		
		//p.prePublish();
		
		return p;
	}

}
