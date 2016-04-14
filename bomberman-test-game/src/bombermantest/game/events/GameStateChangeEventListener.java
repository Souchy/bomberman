package bombermantest.game.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.google.common.eventbus.Subscribe;

import bombermantest.enums.ClientState;
import bombermantest.enums.GameState;
import bombermantest.events.GameStateChangeEvent;
import bombermantest.game.main.GameGame;
import bombermantest.game.network.game.client.GClientServer;
import bombermantest.generation.Generation;
import bombermantest.main.TestGame;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.objects.characters.playables.BPlayer;
import bombermantest.ui.game.GameScreen;
import bombermantest.ui.game.OutGameScreen;
import bombermantest.ui.game.PreparingGameScreen;

public class GameStateChangeEventListener {
	
	private static int i;

	@Subscribe 
	public void listen(GameStateChangeEvent event){
		GameState before = event.getBefore();
		GameState after = event.getAfter();
		
		GameState.change(after);
		
		Gdx.app.postRunnable(() -> {
			switch(after){
				case OUTGAME: onOutgame(); break;
				case PREPARING: onPreparing(); break;
				case INGAME: onIngame(); break;
			}
		});
		
		boolean onConnect = false;  // si c'est le premier GameStatePacket que le client recoit (quand il se connecte), ou si c'est un changement d'état subséquent.
		GameClientPackets.GAME_STATE.broadcast(GClientServer.get().getSessionList(), onConnect);
	}
	
	private void onOutgame(){
		// met tout le monde à jouer
		TestGame.get().getClientList().forEach(c -> c.state = ClientState.PLAYING);
		// vide les anciennes instances du monde 
		TestGame.get().universe.instances.forEach(c -> c.dispose(true));
		
		TestGame.get().setScreen(OutGameScreen.get());
	}
	
	private void onPreparing(){
		//TODO : create all world's instances
		Generation.Classic.generate(TestGame.get(), false);
		
		i = 0;
		TestGame.get().universe.player = new BPlayer(TestGame.get(), new Vector3(-2, i, 0));
		TestGame.get().universe.player.publish(false);
		
		GClientServer.get().getClientList().stream().filter(c -> c.state == ClientState.PLAYING).forEach(client -> {
			i += 2;
			client.player = new BPlayer(TestGame.get(), new Vector3(-2, i, 0));
			client.player.client = client;
			client.player.publish(false);
		});

		GameClientPackets.ENTITY_LIST.broadcast(GClientServer.get().getSessionList(), GameGame.get().universe.instances);
		System.out.println("---2sending complete entity list, size="+GameGame.get().universe.instances.size()+" -1 (enlève le player du server)");

		TestGame.get().setScreen(PreparingGameScreen.get());
	}
	
	private void onIngame(){
		
		TestGame.get().setScreen(GameScreen.get());
	}
	

}
