package bombermantest.client.network.client.game;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.filter.codec.ProtocolCodecFilter;

import com.mygdx.engine.network.zDecoder;
import com.mygdx.engine.network.zEncoder;

import Clients.MinaClient;
import bombermantest.network.objects.GClient;

public class GameClient extends MinaClient {
	
	private static GameClient singleton;
	
	public static GameClient get(){
		if(singleton == null) singleton = new GameClient();
		return singleton;
	}
	
	/**
	 * (includes himself)
	 */
	public static Map<Long, GClient> clients = new HashMap<Long, GClient>();
	public static long myid;
	
	public GameClient(){//MinaHandler handler){
		super();
		BOTH_IDLE_TIME = 15 * 60; // 15 min d'idle
		setIp("127.0.0.1");
		setPort((short) 444);
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
	
	public static GClient getMyClient(){
		return clients.get(myid);
	}

}
