package bombermantest.game.ui.components;

import com.badlogic.gdx.graphics.Color;

import bombermantest.game.main.testGameConfig;
import bombermantest.game.network.game.client.GClientServer;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.ui.components.AChatboxEntryInputListener;

public class ChatboxEntryInputListener extends AChatboxEntryInputListener {
	

	public static AChatboxEntryInputListener get(){
		if(singleton == null) singleton = new ChatboxEntryInputListener();
		return singleton;
	}

	/*private ChatboxEntryInputListener(ChatboxEntry entry, ChatboxArea area) {
		super(entry, area);
	}*/

	@Override
	protected void enterMessage(String text){
		if(text.startsWith("/")){
			enterCommand(text.substring(1));
		} else {
			text = "[SERVER]" + " : " + text;
			//area.setColor(Color.WHITE);
			area.addMessage(text);
			
			//GameClientPackets.CHAT.compose(GameClient.get().getSession(), text, GameClient.getMyClient().team); 
		}
	}
	
	@Override
	protected void enterCommand(String text){
		//area.setColor(Color.YELLOW);
		String[] textt = text.split(" ", 1);
		String command = textt[0].toLowerCase();
		String input = textt[1];
		switch(command){
			case "all": 
				input = "[ALL] " + "[SERVER]" + " : " + input;
				GameClientPackets.CHAT.broadcast(GClientServer.get().getSessionList(), input, Color.rgba8888(Color.YELLOW)); 
				area.addMessage(input); 
			break;
			default : area.addMessage("Commande inconnue."); break;
		}
		
	}
	@Override
	public int getFocusChatKey() {
		return testGameConfig.focusChatKey;
	}
	
}
