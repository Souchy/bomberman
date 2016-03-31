package bombermantest.network.handlers;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.utils.Disposable;

import Handlers.MinaHandler;

public abstract class ServerHandler extends MinaHandler {

	public static enum SessionType {
		Client,
		GameServer,
		LoginServer,
		Proxy;
	}
	
	/**
	 * see the enum SessionType
	 */
	public static final String SESSION_TYPE_ATTR_KEY = "type";
	
	public static final String CLIENT_ATTR_KEY = "client";
	public static final String PLAYER_ATTR_KEY = "player";
	public static final String POSITION_ATTR_KEY = "pos";
	public static final String ANGLE_ATTR_KEY = "angle";
	public static final String STATE_ATTR_KEY = "state";
	
	
	@Override
	public void exceptionCaught(IoSession session, Throwable mess) throws Exception {
		//Disposable client = (Disposable) session.getAttribute(CLIENT_ATTR_KEY);
		//client.dispose();
		session.close(true);
		System.out.println("serv exceptionCaught kicked client: ["+session.getId()+"]");
		mess.printStackTrace();
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("serv sessionClosed id:"+session.getId());
		
		Disposable client = (Disposable) session.getAttribute(CLIENT_ATTR_KEY);
		client.dispose();
		//APlayer player = (APlayer) session.getAttribute(PLAYER_ATTR_KEY);
		//player.dispose(); //player.remove();
		//Server.get().clients.remove(client);
		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus mess) throws Exception {
		session.close(true);
		System.out.println("serv sessionIdle kicked client: ["+session.getId()+"]");
		//mess.printStackTrace();
	}


}
