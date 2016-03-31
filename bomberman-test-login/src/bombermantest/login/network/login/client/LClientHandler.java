package bombermantest.login.network.login.client;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.packets.enums.LoginClientPackets;

public class LClientHandler extends ServerHandler {
	
	public LClientHandler(){
		
	}

	@Override
	public void messageReceived(IoSession session, Object mess) throws Exception {
		System.out.println("Login received message from Client");
		IoBuffer buf = (IoBuffer) mess;
		if(buf.position() != 0 || buf.remaining() <= 0){
			System.out.println("serv invalid buf position() or remaining() "+buf.toString());
			return;
		}

		int packetlength = buf.getInt();
		LoginClientPackets packet = buf.getEnum(LoginClientPackets.class);
		
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
		System.out.println("login client session opened ["+session.getId()+"]");
		
		//testLoginPlayerClient client = new testLoginPlayerClient(session);
		LClient client = new LClient();
		
		session.setAttribute(CLIENT_ATTR_KEY, client);
		session.setAttribute(SESSION_TYPE_ATTR_KEY, SessionType.Client);
		
		//LClientServer.get().clients.add(client);
		//Server.get().clients.add(client);
		
	}

}
