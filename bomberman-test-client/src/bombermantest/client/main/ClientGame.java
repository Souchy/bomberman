package bombermantest.client.main;

import java.util.Collection;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.configs.AConstants.Platform;
import com.mygdx.engine.game.CollisionListenerBox2d;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.client.network.client.login.LoginClient;
import bombermantest.client.ui.OptionsScreen;
import bombermantest.client.ui.game.ChatHud;
import bombermantest.client.ui.login.GameServerListScreen;
import bombermantest.client.ui.login.LoginScreen;
import bombermantest.configs.Constants;
import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.ui.game.AChatHud;
import bombermantest.ui.game.GameScreen;
import bombermantest.ui.game.OutGameScreen;
import bombermantest.ui.game.PreparingGameScreen;
import bombermantest.ui.game.StatusHud;

public class ClientGame extends TestGame {

	public static ClientGame get(){
		if(singleton == null) singleton = new ClientGame();
		return (ClientGame) singleton;
	}
	
	ClientGame() {
		super(Platform.Desktop);
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

		
		//universe.player = new CPlayer(this, new Vector3(0, 3, 0));
		//universe.player.prePublish();
		
		
		LoginScreen.get();
		OptionsScreen.get();
		GameServerListScreen.get();
		OutGameScreen.get();
		PreparingGameScreen.get();
		GameScreen.get();
		StatusHud.get();
		ChatHud.get();

		//setScreen(LoginScreen.get());
		
		setScreen(ChatHud.get());
		
	}

	@Override
	public void render () {
		super.render();
	}
	

	@Override
	public void dispose () {
		super.dispose();
		
		LoginScreen.get().dispose();
		OptionsScreen.get().dispose();
		GameServerListScreen.get().dispose();
		OutGameScreen.get().dispose();
		PreparingGameScreen.get().dispose();
		GameScreen.get().dispose();
		StatusHud.get().dispose();
		ChatHud.get().dispose();

		//JsonLoader.singleton.saveJson(Settings.get());
		
		GameClient.get().off(true);
		LoginClient.get().off(true);

	}

	@Override
	public int getClientCount() {
		return GameClient.clients.size();
	}

	@Override
	public Collection<GClient> getClientList() {
		return GameClient.clients.values();
	}

	@Override
	public Collection<Long> getClientIDList() {
		return GameClient.clients.keySet();
	}

	@Override
	public GClient getClient(long id) {
		return GameClient.clients.get(id);
	}
	
}
