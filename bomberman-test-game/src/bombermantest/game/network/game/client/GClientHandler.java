package bombermantest.game.network.game.client;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.enums.ClientState;
import bombermantest.enums.GameState;
import bombermantest.events.GameStateChangeEvent;
import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.enums.GameClientPackets;

public class GClientHandler extends ServerHandler {
	
	public GClientHandler(){
		
	}

	@Override
	public void messageReceived(IoSession session, Object mess) throws Exception {
		//System.out.println("Game received message from Client");
		IoBuffer buf = (IoBuffer) mess;
		if(buf.position() != 0 || buf.remaining() <= 0 || buf.limit() <= 4){
			System.out.println("serv invalid buf position() or remaining() or limit() "+buf.toString());
			return;
		}

		int packetlength = buf.getInt();
		GameClientPackets packet = buf.getEnum(GameClientPackets.class);
		
		//System.out.println("client receiv packet="+packet.name()+", packetlength="+packetlength+", remain="+buf.remaining()+", buf : "+buf.toString());
		
		if(!packet.isLengthCorrect(packetlength)){
			System.out.println("client receiv buf size invalid (packet="+packet.name()+"), (packetlength="+packetlength+"), (remain="+buf.remaining()+"), buf : "+buf.toString());
			//àreturn;
		}
		
		packet.parse(session, buf);
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception { 
		//System.out.println("client message sent " + arg1);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("game serv client session opened ["+session.getId()+"]");
		
		//testGamePlayerClient client = new testGamePlayerClient(session);
		GClient client = new GClient();
		
		session.setAttribute(CLIENT_ATTR_KEY, client);
		session.setAttribute(SESSION_TYPE_ATTR_KEY, SessionType.Client);
		
		//GClientServer.get().acceptor.clients.add(client);
	}
	

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("serv sessionClosed id:"+session.getId());
		
		GClient client = (GClient) session.getAttribute(CLIENT_ATTR_KEY);
		
		GameClientPackets.LOST_PLAYER.broadcast(GClientServer.get().getSessionListBut(session), client.id);
		
		client.dispose();
		
		long playingCount = GClientServer.get().getClientList().stream().filter(c -> c.state == ClientState.PLAYING).count();
		if(playingCount == 0){
			GameStateChangeEvent.post(GameState.state, GameState.OUTGAME);
		}
	}
	
	
}
