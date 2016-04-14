package bombermantest.client.network.client.login;

import org.apache.mina.filter.codec.ProtocolCodecFilter;

import com.mygdx.engine.network.zDecoder;
import com.mygdx.engine.network.zEncoder;

import Clients.MinaClient;

public class LoginClient extends MinaClient {

	private static LoginClient singleton;
	
	public static LoginClient get(){
		if(singleton == null) singleton = new LoginClient();
		return singleton;
	}

	//public static Map<Long, CGame> gameServers = new HashMap<Long, CGame>();

	public LoginClient(){//MinaHandler handler){
		super();
		BOTH_IDLE_TIME = 15 * 60; // 15 min d'idle
		setIp("127.0.0.1");
		setPort((short) 666);
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
