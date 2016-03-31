package bombermantest.main;

import java.util.Collection;

import com.mygdx.engine.configs.AConstants.Platform;
import com.mygdx.engine.game.AGame;

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
		/*if(TestGame.get().universe.toDestroy.size() > 0){
			TestGame.get().universe.toDestroy.forEach(Box2dObject::destroy);
			TestGame.get().universe.toDestroy.clear();
		}
		if(TestGame.get().universe.toPublish.size() > 0){
			System.out.println("----------- PUBLISHING PUBLISHING PUBLISHING PUBLISHING PUBLISHING");
			//TestGame.get().universe.toPublish.forEach(Box2dObject::publish);
			TestGame.get().universe.toPublish.clear();
		}*/
	}

	public abstract int getClientCount();
	public abstract Collection<Long> getClientIDList();
	public abstract Collection<GClient> getClientList();
	public abstract GClient getClient(long id);
	

}
