package bombermantest.main;

import java.util.Collection;

import com.mygdx.engine.configs.AConstants.Platform;
import com.mygdx.engine.game.AGame;

import bombermantest.enums.ClientState;
import bombermantest.network.objects.GClient;

public abstract class TestGame extends AGame {
	
	public TestGame(Platform plat) {
		super(plat);
		//singleton = this;  //to do in childclasses
	}
	
	protected static TestGame singleton;
	/** to override */
	public static TestGame get(){
		//if(singleton == null) singleton = new TestGame();
		return singleton;
	}
	
	@Override
	public void render () {
		super.render();
	}

	public abstract int getClientCount();
	public abstract Collection<Long> getClientIDList();
	public abstract Collection<GClient> getClientList();
	public abstract GClient getClient(long id);
	public abstract ClientState getClientState();
	public abstract void setClientState(ClientState state);
	

}
