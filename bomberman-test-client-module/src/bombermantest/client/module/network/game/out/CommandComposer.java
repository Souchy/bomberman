package bombermantest.client.module.network.game.out;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;

public class CommandComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		String command = (String) objects[0];
		
		try {
			buf.putPrefixedString(command, encoder);
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
				// rien à mettre dans le buffer
			break;
			default : System.out.println("[Client] Composing Commande inconnue : ["+command+"]"); break;
		}
		
		return buf;
	}

}
