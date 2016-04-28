package bombermantest.client.network.client.game;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.handlers.ClientHandler;
import bombermantest.network.packets.enums.GameClientPackets;

public class GameHandler extends ClientHandler {
	
	@Override
	public void messageReceived(IoSession session, Object mess) throws Exception {
		//System.out.println("Client received message from Game");
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
			//return;
		}
		
		packet.parse(session, buf);
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("client sessionOpened " + session.getId());
		
		GameClientPackets.AUTHENTIFICATION.compose(session);
	}
	

	public void messageSent(IoSession arg0, Object arg1) throws Exception { 
		//System.out.println("client message sent " + arg1);
	}
	
}
