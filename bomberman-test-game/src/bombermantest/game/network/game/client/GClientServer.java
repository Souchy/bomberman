package bombermantest.game.network.game.client;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;

import com.mygdx.engine.network.zDecoder;
import com.mygdx.engine.network.zEncoder;

import Servers.MinaServer;
import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.objects.GClient;

public class GClientServer extends MinaServer {

	private static GClientServer singleton;
	//public ArrayList<ServerClient> clients = new ArrayList<>();
	
	public static GClientServer get(){
		if(singleton == null) singleton = new GClientServer();
		return singleton;
	}
	
	private GClientServer(){
		super();
		this.BOTH_IDLE_TIME = 15 * 60; // 15 min d'idle
		setPort((short) 444);
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

	public int getClientCount() {
		return GClientServer.get().acceptor.getManagedSessionCount();
	}
	public Collection<IoSession> getSessionList(){
		return acceptor.getManagedSessions().values();
	}
	public List<GClient> getClientList(){
		return acceptor.getManagedSessions().values().stream().map(s -> (GClient) s.getAttribute(ServerHandler.CLIENT_ATTR_KEY)).collect(Collectors.toList());
	}
	
	/**
	 * Créé une liste de toutes les sessions sauf celle en paramètre
	 * @param session - la session à éviter
	 * @return liste de toutes les sessions sauf celle en paramètre
	 */
	public List<IoSession> getSessionListBut(IoSession session){
		return acceptor.getManagedSessions().values().stream().filter(s -> s != session).collect(Collectors.toList());
	}
	
	/**
	 * Créé une liste de tous les gclients sauf celui de la session en paramètre
	 * @param session - la session à éviter
	 * @return liste de tous les gclients sauf celui de la session en paramètre
	 */
	public List<GClient> getClientListBut(IoSession session){
		return acceptor.getManagedSessions().values().stream().filter(s -> s != session).map(s -> (GClient) s.getAttribute(ServerHandler.CLIENT_ATTR_KEY)).collect(Collectors.toList());
	}
	
	public IoSession getSession(long id){
		return acceptor.getManagedSessions().get(id);
	}
	
	public GClient getClient(long id){
		return (GClient) getSession(id).getAttribute(ServerHandler.CLIENT_ATTR_KEY);
	}
	
	
	
}
