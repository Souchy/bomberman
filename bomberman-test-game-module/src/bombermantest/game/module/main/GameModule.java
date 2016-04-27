package bombermantest.game.module.main;

import com.mygdx.engine.plugins.Plugin;

import bombermantest.game.module.injectors.ClientPacketInjector;
import bombermantest.game.module.injectors.EntityEncoderInjector;
import bombermantest.game.module.injectors.LoginPacketInjector;

public class GameModule extends Plugin {
	
	//static {
	//	Thread.currentThread().setContextClassLoader(ClientModule.class.getClassLoader().getParent());
	//}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		System.out.println("hi, loading GameModule");

		//Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());

		LoginPacketInjector.inject();
		ClientPacketInjector.inject();
		EntityEncoderInjector.inject();

		System.out.println("hi, done injecting GameModule");
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		System.out.println("hi, enabling GameModule");
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnload() {
		// TODO Auto-generated method stub
		
	}


}
