package bombermantest.login.network.login.game;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.packets.enums.LoginGamePackets;

public class LGameHandler extends ServerHandler {
	
	public LGameHandler(){
		
	}

	@Override
	public void messageReceived(IoSession session, Object mess) throws Exception {
		System.out.println("Login received message from Game");
		IoBuffer buf = (IoBuffer) mess;
		if(buf.position() != 0 || buf.remaining() <= 0){
			System.out.println("serv invalid buf position() or remaining() "+buf.toString());
			return;
		}

		int packetlength = buf.getInt();
		LoginGamePackets packet = buf.getEnum(LoginGamePackets.class);
		
		System.out.println("serv receiv packet="+packet.name()+", packetlength="+packetlength+", remain="+buf.remaining()+", buf : "+buf.toString());
		
		if(!packet.isLengthCorrect(packetlength)){
			System.out.println("serv receiv buf size invalid (packet="+packet.name()+"), (packetlength="+packetlength+"), (remain="+buf.remaining()+"), buf : "+buf.toString());
		//	return;
		}
		
		packet.parse(session, buf);
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		// TODO ServerSender.broadcastLostPlayer(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("login game session opened ["+session.getId()+"]");
		
		//testLoginGameClient client = new testLoginGameClient(session);
		LGame g = new LGame();
		
		session.setAttribute(CLIENT_ATTR_KEY, g);
		session.setAttribute(SESSION_TYPE_ATTR_KEY, SessionType.Client);
		
		//LGameServer.get().clients.add(client);
		//Server.get().clients.add(client);
	}
}
