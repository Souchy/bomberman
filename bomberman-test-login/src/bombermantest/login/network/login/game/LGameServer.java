package bombermantest.login.network.login.game;

import org.apache.mina.filter.codec.ProtocolCodecFilter;

import com.mygdx.engine.network.zDecoder;
import com.mygdx.engine.network.zEncoder;

import Servers.MinaServer;

public class LGameServer extends MinaServer {

	private static LGameServer singleton;
	//public ArrayList<ServerClient> clients = new ArrayList<>();
	
	public static LGameServer get(){
		if(singleton == null) singleton = new LGameServer();
		return singleton;
	}
	
	private LGameServer(){
		super();
		BOTH_IDLE_TIME = 15 * 60; // 15 min d'idle
		setPort((short) 667);
		codec = new ProtocolCodecFilter(new zEncoder(), new zDecoder());
	}
	
	/*public ServerClient getClient(APlayer player){
		for(ServerClient c : clients){
			//System.out.println("for c : clients .id = ["+c.get().getId()+"], .playerkey = ["+c.get().getAttribute(ServerHandler.PLAYER_ATTR_KEY)+"], player = ["+player+"]");
			if(player.equals(c.get().getAttribute(ServerHandler.PLAYER_ATTR_KEY))){
				return c;
			}
		}
		return null;
	}*/

}
