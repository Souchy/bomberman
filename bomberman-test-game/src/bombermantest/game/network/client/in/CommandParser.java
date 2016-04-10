package bombermantest.game.network.client.in;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.mygdx.engine.events.PlayerDeathEvent;

import bombermantest.game.network.game.client.GClientServer;
import bombermantest.network.handlers.ServerHandler;
import bombermantest.network.objects.GClient;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.GameClientPackets;

public class CommandParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		GClient client = (GClient) session.getAttribute(ServerHandler.CLIENT_ATTR_KEY);
		String command = "null";
		try {
			command = buf.getPrefixedString(decoder);
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}	
		
		switch(command){
			/*case "all": 
				input = "[ALL] " + GameClient.getMyClient().name + " : " + input;
				GameClientPackets.CHAT.compose(GameClient.get().getSession(), input, Color.rgba8888(Color.YELLOW)); 
				addMessage(input); 
			break;*/
			case "s": 
			case "suicide": 
				//client.player.getBStats().life = 0; //déplacé dans le playerDeathEventListener
				PlayerDeathEvent.post(client.player, client.player);
				GameClientPackets.SUICIDE.broadcast(GClientServer.get().getSessionListBut(session), client.id);
			break;
			default : System.out.println("[Serv] Recv Commande inconnue. : ["+command+"]"); break;
		}
		
	}

}
