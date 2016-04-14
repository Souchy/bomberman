package bombermantest.game.network.client.in;

import static bombermantest.network.handlers.ServerHandler.CLIENT_ATTR_KEY;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import bombermantest.enums.ClientState;
import bombermantest.enums.GameState;
import bombermantest.events.GameStateChangeEvent;
import bombermantest.game.main.BombermanTestGame;
import bombermantest.game.main.GameGame;
import bombermantest.game.main.testGameConfig;
import bombermantest.game.network.game.client.GClientServer;
import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.ui.game.OutGameScreen;

public class ClientAuthentificationParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		GClient client = (GClient) session.getAttribute(CLIENT_ATTR_KEY);
		
		boolean accepted = false;
		boolean isFirstClient = GClientServer.get().getClientCount() == 1;
		boolean onConnect = true;  // si c'est le premier GameStatePacket que le client recoit (quand il se connecte), ou si c'est un changement d'état subséquent.
		
		if(isFirstClient){
			//Gdx.app.postRunnable(() -> {
				//GameState.change(GameState.OUTGAME);
				//TestGame.get().setScreen(OutGameScreen.get());
				
				GameStateChangeEvent.post(GameState.state, GameState.OUTGAME);
			//});
		}
		
		try {
			//System.out.println("GameTransferingClients : ["+BombermanTestGame.transferingClients+"]");
			String playerUsername = buf.getPrefixedString(decoder);
			String serverPassword = buf.getPrefixedString(decoder);
			
			if(testGameConfig.serverPassword == "" || testGameConfig.serverPassword.equals(serverPassword)){
				if(BombermanTestGame.transferingClients.remove(playerUsername)){
					accepted = true;
					
					client.id = session.getId();
					client.name = playerUsername;
					client.wins = 0;
					client.kills = 0;
					client.deaths = 0;
					client.team = Color.rgba8888(Color.WHITE);

					if(GameState.state == GameState.OUTGAME){
						client.state = ClientState.PLAYING;
					}else{
						client.state = ClientState.SPECTATING;
					}
				}
			}
			//System.out.println("username = ["+playerUsername+"], password = ["+serverPassword+"] vs ["+testGameConfig.serverPassword+"], accepted = ["+accepted+"]");
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
		GameClientPackets.AUTHENTIFICATION_RESULT.compose(session, accepted);
		
		if(accepted) GameClientPackets.GAME_STATE.compose(session, onConnect);
		if(accepted) GameClientPackets.ENTITY.broadcast(GClientServer.get().getSessionListBut(session), client);
		if(accepted) GameClientPackets.ENTITY_LIST.compose(session, GClientServer.get().getClientList());
		if(accepted && GameState.state != GameState.OUTGAME){
			GameClientPackets.ENTITY_LIST.compose(session, GameGame.get().universe.instances);
			System.out.println("---1sending complete entity list, size="+GameGame.get().universe.instances.size()+" -1 (enlève le player du server)");
		}
	}

}
