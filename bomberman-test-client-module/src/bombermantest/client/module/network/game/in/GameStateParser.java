package bombermantest.client.module.network.game.in;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.Gdx;

import bombermantest.client.main.ClientGame;
import bombermantest.client.network.client.game.GameClient;
import bombermantest.enums.ClientState;
import bombermantest.enums.GameState;
import bombermantest.main.TestGame;
import bombermantest.network.packets.Parser;
import bombermantest.ui.game.GameScreen;
import bombermantest.ui.game.OutGameScreen;
import bombermantest.ui.game.PreparingGameScreen;
import bombermantest.ui.game.Scoreboard;

public class GameStateParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		GameState.state = buf.getEnum(GameState.class);
		GameState.timer = buf.getLong();
		boolean onConnect = buf.get() == 1; // si c'est le premier GameStatePacket qu'il recoit (quand il se connecte), ou si c'est un changement d'�tat subs�quent.

		Gdx.app.postRunnable(() -> {
			switch(GameState.state){
			case OUTGAME:
				TestGame.get().getClientList().forEach(c -> c.state = ClientState.PLAYING); // met tout le monde � jouer
				//GameClient.getMyClient().state = ClientState.PLAYING; // pas besoin si on foreach() d�ja tout lmonde
				//ClientState.state = ClientState.PLAYING;
				TestGame.get().universe.instances.forEach(c -> c.dispose()); // clean all world's instances
				ClientGame.get().setScreen(OutGameScreen.get());
				break;
			case PREPARING:
				//if(onConnect) ClientState.state = ClientState.SPECTATING;
				//else ClientState.state = ClientState.PLAYING;
				if(onConnect) GameClient.getMyClient().state = ClientState.SPECTATING;
				else GameClient.getMyClient().state = ClientState.PLAYING;
				ClientGame.get().setScreen(PreparingGameScreen.get());
				break;
			case INGAME:
				//if(onConnect) ClientState.state = ClientState.SPECTATING;
				if(onConnect) GameClient.getMyClient().state = ClientState.SPECTATING;
				//if(onConnect || ClientState.state == ClientState.SPECTATING) ClientState.state = ClientState.SPECTATING;
				//else ClientState.state = ClientState.PLAYING;
				ClientGame.get().setScreen(GameScreen.get());
				break;
			}
			
			Scoreboard.get().updateClientList(); 
		});
		
		
	}

}
