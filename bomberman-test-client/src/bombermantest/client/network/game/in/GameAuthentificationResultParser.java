package bombermantest.client.network.game.in;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.network.packets.Parser;

public class GameAuthentificationResultParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		byte accepted = buf.get();
		
		if(accepted == 1){
			GameClient.myid = buf.getLong();
			System.out.println("Client accepted to game, id=["+GameClient.myid+"], wait for GameState packet");
		}else{
			System.out.println("Client denied to game");
			// TODO : new thread() -> { new DialogueBox("Connexion refused")  }
			session.close(true);
		}
	}

}
