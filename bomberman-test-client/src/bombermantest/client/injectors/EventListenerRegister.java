package bombermantest.client.injectors;

import com.mygdx.engine.game.Universe;

import bombermantest.events.PlayerDeathEventListener;

public final class EventListenerRegister {
	
	public static final void register(){
		Universe.bus.register(new PlayerDeathEventListener());
	}

}
