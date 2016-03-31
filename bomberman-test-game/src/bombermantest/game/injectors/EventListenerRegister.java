package bombermantest.game.injectors;

import com.mygdx.engine.game.Universe;

import bombermantest.events.PlayerDeathEventListener;
import bombermantest.game.events.CrateDropEventListener;
import bombermantest.game.events.GameStateChangeEventListener;

public final class EventListenerRegister {
	
	public static final void register(){
		Universe.bus.register(new PlayerDeathEventListener());
		Universe.bus.register(new CrateDropEventListener());
		Universe.bus.register(new GameStateChangeEventListener());
		//Universe.bus.register(game);
	}
	

}
