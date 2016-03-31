package bombermantest.game.ui;

import com.badlogic.gdx.graphics.Color;

import bombermantest.game.main.testGameConfig;
import bombermantest.game.network.game.client.GClientServer;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.ui.game.AChatHud;

public class ChatHud extends AChatHud {

	public static ChatHud get(){
		if(singleton == null){
			singleton = new ChatHud(); 
			singleton.create(null);
		}
		return (ChatHud) singleton;
	}
	
	@Override
	protected void enterMessage(String text){
		if(text.startsWith("/")){
			enterCommand(text.substring(1));
		} else {
			text = "[SERVER]" + " : " + text;
			chatBox.setColor(Color.WHITE);
			addMessage(text);
			
			//GameClientPackets.CHAT.compose(GameClient.get().getSession(), text, GameClient.getMyClient().team); 
		}
	}
	
	@Override
	protected void enterCommand(String text){
		chatBox.setColor(Color.YELLOW);
		String[] textt = text.split(" ", 1);
		String command = textt[0].toLowerCase();
		String input = textt[1];
		
		switch(command){
			case "all": 
				input = "[ALL] " + "[SERVER]" + " : " + input;
				GameClientPackets.CHAT.broadcast(GClientServer.get().getSessionList(), input, Color.rgba8888(Color.YELLOW)); 
				addMessage(input); 
			break;
			default : addMessage("Commande inconnue."); break;
		}
		
	}
	@Override
	protected int getFocusChatKey() {
		return testGameConfig.focusChatKey;
	}

}
