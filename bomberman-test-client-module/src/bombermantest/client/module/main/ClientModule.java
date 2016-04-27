package bombermantest.client.module.main;

import com.mygdx.engine.plugins.Plugin;

import bombermantest.client.module.injectors.EntityDecoderInjector;
import bombermantest.client.module.injectors.GamePacketInjector;
import bombermantest.client.module.injectors.LoginPacketInjector;

public class ClientModule extends Plugin {
	
	//static {
	//	Thread.currentThread().setContextClassLoader(ClientModule.class.getClassLoader().getParent());
	//}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		System.out.println("hi, loading ClientModule");

		//Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());
		
		LoginPacketInjector.inject();
		GamePacketInjector.inject();
		EntityDecoderInjector.inject();

		System.out.println("hi, done injecting ClientModule");
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		System.out.println("hi, enabling ClientModule");
		
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
