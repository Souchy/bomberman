package bombermantest.game.main;

import java.util.Collection;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.configs.AConstants.Platform;
import com.mygdx.engine.game.CollisionListenerBox2d;

import bombermantest.configs.Constants;
import bombermantest.enums.ClientState;
import bombermantest.enums.GameState;
import bombermantest.events.GameStateChangeEvent;
import bombermantest.game.network.game.client.GClientServer;
import bombermantest.game.network.game.login.GLoginClient;
import bombermantest.game.ui.ChatHud;
import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.ui.game.GameScreen;
import bombermantest.ui.game.OutGameScreen;
import bombermantest.ui.game.PreparingGameScreen;
import bombermantest.ui.game.StatusHud;

public class GameGame extends TestGame {

	
	public static GameGame get(){
		if(singleton == null) singleton = new GameGame();
		return (GameGame) singleton;
	}

	GameGame() {
		super(Platform.Server);
		singleton = this;
	}

	@Override
	public void create () {
		super.create();
		
		universe.collis = new CollisionListenerBox2d(this);
		universe.world = new World(new Vector2(0, -9.81f).scl(0.0f), false);
		universe.world.setContactListener(universe.collis);
		
		AConstants.scale = (float) 1/12;
		AConstants.makeSensorsAroundPlayables = false;
		Constants.setVisibleArea();
		
		//v-> déplacé dans GameStateChangeEventListener
		//Generation.Classic.generate(this); 
		//universe.player = new BPlayer(this, new Vector3(0, 3, 0));
		//universe.player.prePublish();

		ChatHud.get();
		StatusHud.get();
		OutGameScreen.get();
		PreparingGameScreen.get();
		GameScreen.get();
		
		setScreen(OutGameScreen.get());
	}

	@Override
	public void render () {
		super.render();
		
		if(GameState.state != GameState.OUTGAME){
			GameClientPackets.MOVE_PLAYER_LIST.broadcast(GClientServer.get().getSessionList(), TestGame.get().getClientList());
		}
		
		if(GameState.state != GameState.INGAME && GameState.state.timeRemaining() <= 0 && GClientServer.get().getClientCount() > 0){
			System.out.println("gamegame.render -> change screen");
			GameStateChangeEvent.post(GameState.state, GameState.state.getNext());
		}
		
	}
	

	@Override
	public void dispose () {
		super.dispose();
		
		OutGameScreen.get().dispose();
		PreparingGameScreen.get().dispose();
		GameScreen.get().dispose();
		StatusHud.get().dispose();
		ChatHud.get().dispose();
		
		GClientServer.get().off(true);
		GLoginClient.get().off(true);
	}
	
	@Override
	public int getClientCount() {
		return GClientServer.get().getClientCount();
	}

	@Override
	public Collection<Long> getClientIDList() {
		return GClientServer.get().acceptor.getManagedSessions().keySet();
	}

	@Override
	public Collection<GClient> getClientList() {
		return GClientServer.get().getClientList();
	}

	@Override
	public GClient getClient(long id) {
		return GClientServer.get().getClient(id);
	}


	@Override
	public ClientState getClientState() {
		return ClientState.PLAYING;
	}

	@Override
	public void setClientState(ClientState state) {
		System.out.println("cant set the clientState in gameserver");
		System.exit(0);
	}

}
