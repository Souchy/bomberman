package bombermantest.client.network.client.login;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.handlers.ClientHandler;
import bombermantest.network.packets.enums.LoginClientPackets;

public class LoginHandler extends ClientHandler {
	
	public LoginHandler(){
		
	}

	@Override
	public void messageReceived(IoSession session, Object mess) throws Exception {
		System.out.println("Client received message from Login");
		IoBuffer buf = (IoBuffer) mess;
		if(buf.position() != 0 || buf.remaining() <= 0 || buf.limit() <= 4){
			System.out.println("serv invalid buf position() or remaining() or limit() "+buf.toString());
			return;
		}
		
		int packetlength = buf.getInt();
		LoginClientPackets packet = buf.getEnum(LoginClientPackets.class);
		
		System.out.println("client receiv packet="+packet.name()+", packetlength="+packetlength+", remain="+buf.remaining()+", buf : "+buf.toString());
		
		if(!packet.isLengthCorrect(packetlength)){
			System.out.println("client receiv buf size invalid (packet="+packet.name()+"), (packetlength="+packetlength+"), (remain="+buf.remaining()+"), buf : "+buf.toString());
			//àreturn;
		}
		
		packet.parse(session, buf);
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("client sessionOpened " + session.getId());
		
		LoginClientPackets.AUTHENTIFICATION.compose(session);
	}
	

	public void messageSent(IoSession arg0, Object arg1) throws Exception { 
		System.out.println("client message sent " + arg1);
	}
	
}
