package bombermantest.client.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.engine.configs.AConstants;

import bombermantest.client.injectors.EntityDecoderInjector;
import bombermantest.client.injectors.EventListenerRegister;
import bombermantest.client.injectors.GamePacketInjector;
import bombermantest.client.injectors.LoginPacketInjector;
import bombermantest.client.network.client.game.GameClient;
import bombermantest.client.network.client.game.GameHandler;
import bombermantest.client.network.client.login.LoginClient;
import bombermantest.client.network.client.login.LoginHandler;

public class BombermanTestClient {
	
	private static final String[] codenames = new String[]{
			"Pogo", "Poptart", "IceCreamSandwich", "Zebra", "Fat Whale", "Ur mum", "Sandbeach",
			"Fast Turtle", "Light Bird", "Jet Thunder", "Tropic Thunder", "Stinky Fish", "Ivory Snow",
			"Blank_"
	};

	/*@FunctionalInterface
	public static interface Publisher {
		public void publish();
	}
	
	public static class Cat {
		public int n = 0;
		public Cat(int i) {
			n = i;
		}
		public void prePublish(){
			System.out.println("prepublish cat " + n);
		}
		public void publish(){
			System.out.println("publish cat " + n);
		}
	}
	
	public static void truc(Publisher sher){
		sher.publish();
	}*/
	
	
	public static void main (String[] arg) {
		/*Cat cat = new Cat(5);
		truc(() -> {});*/
		/*System.out.println("blue1 = ["+Color.BLUE.toIntBits()+"]"); // blue1 = [-65536]
		System.out.println("blue2 = ["+new Color(Color.BLUE.toIntBits()).toIntBits()+"]"); // blue2 = [65536]
		System.out.println("blue3 = ["+new Color(new Color(Color.BLUE.toIntBits()).toIntBits()).toIntBits()+"]"); // blue3 = [-65536] */
		//int ma = Color.rgba8888(Color.BLUE);
		//Color.rgba8888ToColor(new Color(), ma);
		//new Color(ma);
		
		//System.exit(0);
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.samples = AConstants.ANTI_ALIASING;
	    config.title = "Client Codename : " + codenames[AConstants.rnd.nextInt(codenames.length)];
		config.width = AConstants.WINDOW_WIDTH;
	    config.height = AConstants.WINDOW_HEIGHT;
	    
		new LwjglApplication(ClientGame.get(), config);
		
		LoginPacketInjector.inject();
		GamePacketInjector.inject();
		EntityDecoderInjector.inject();
		EventListenerRegister.register();
		
		LoginClient.get().setHandler(new LoginHandler());
		GameClient.get().setHandler(new GameHandler());
		//Client.get().setHandler(new LoginHandler(game));

	}
	
	


}
