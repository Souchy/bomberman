package bombermantest.game.main;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.engine.configs.AConstants;

import bombermantest.game.injectors.ClientPacketInjector;
import bombermantest.game.injectors.EntityEncoderInjector;
import bombermantest.game.injectors.EventListenerRegister;
import bombermantest.game.injectors.LoginPacketInjector;
import bombermantest.game.network.game.client.GClientHandler;
import bombermantest.game.network.game.client.GClientServer;
import bombermantest.game.network.game.login.GLoginClient;
import bombermantest.game.network.game.login.GLoginHandler;

public class BombermanTestGame {

	public static List<String> transferingClients = new ArrayList<>();
	
	private static final String[] codenames = new String[]{
			"Pogo", "Poptart", "IceCreamSandwich", "Zebra", "Fat Whale", "Ur mum", "Sandbeach",
			"Fast Turtle", "Light Bird", "Jet Thunder", "Tropic Thunder", "Stinky Fish", "Ivory Snow",
			"Blank_"
	};
	
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.samples = AConstants.ANTI_ALIASING;
	    config.title = "Game Codename : " + codenames[AConstants.rnd.nextInt(codenames.length)];
		config.width = AConstants.WINDOW_WIDTH;
	    config.height = AConstants.WINDOW_HEIGHT;
	    
		new LwjglApplication(GameGame.get(), config);
		
		LoginPacketInjector.inject();
		ClientPacketInjector.inject();
		EntityEncoderInjector.inject();
		EventListenerRegister.register();
		
		GClientServer.get().setHandler(new GClientHandler());
		GLoginClient.get().setHandler(new GLoginHandler());
		
		GLoginClient.get().on();
		GClientServer.get().on();
		
	}

}
