package bombermantest.game.network.game.login;

import org.apache.mina.filter.codec.ProtocolCodecFilter;

import com.mygdx.engine.network.zDecoder;
import com.mygdx.engine.network.zEncoder;

import Clients.MinaClient;

public class GLoginClient extends MinaClient {
	
	private static GLoginClient singleton;
	
	public static GLoginClient get(){
		if(singleton == null) singleton = new GLoginClient();
		return singleton;
	}

	public GLoginClient(){//MinaHandler handler){
		super();
		BOTH_IDLE_TIME = 15 * 60; // 15 min d'idle
		setIp("127.0.0.1");
		setPort((short) 667);
		//setHandler(handler);
		codec = new ProtocolCodecFilter(new zEncoder(), new zDecoder());
	}
	
	
	@Override
	public void on() {
		try{
			start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void off(boolean terminate) {
		stop();
	}

}
