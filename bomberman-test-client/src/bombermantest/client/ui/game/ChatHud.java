package bombermantest.client.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.engine.events.PlayerDeathEvent;

import bombermantest.client.main.testClientConfig;
import bombermantest.client.network.client.game.GameClient;
import bombermantest.enums.ClientState;
import bombermantest.enums.GameState;
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
			text = GameClient.getMyClient().name + " : " + text;
			chatBox.setColor(GameClient.getMyClient().geTeamColor());
			addMessage(text);
			
			GameClientPackets.CHAT.compose(GameClient.get().getSession(), text, GameClient.getMyClient().team); 
		}
	}
	
	@Override
	protected void enterCommand(String text){
		chatBox.setColor(Color.YELLOW);
		String[] textt = text.split(" ", 1);
		String command = textt[0].toLowerCase();
		String input = textt.length == 1 ? "" : textt[1];
		
		if(!commandCondition(command)) return;
		
		switch(command){
			case "all": 
				input = "[ALL] " + GameClient.getMyClient().name + " : " + input;
				GameClientPackets.CHAT.compose(GameClient.get().getSession(), input, Color.rgba8888(Color.YELLOW)); 
				addMessage(input); 
			break;
			case "s": 
			case "suicide": 
				//input = "[ALL] " + GameClient.getMyClient().name + " : " + input;
				//GameClientPackets.CHAT.compose(GameClient.get().getSession(), input, Color.rgba8888(Color.YELLOW)); 
				//addMessage(input); 
				
				//GameClient.getMyClient().player.getBStats().life = 0; //déplacé dans le playerDeathEventListener
				PlayerDeathEvent.post(GameClient.getMyClient().player, GameClient.getMyClient().player);
				
				GameClientPackets.COMMAND.compose(GameClient.get().getSession(), command);
			break;
			default : addMessage("Commande inconnue."); break;
		}
	}
	
	private boolean commandCondition(String command){
		boolean condOk;
		switch(command){
			case "all": 
				condOk = (GameState.state == GameState.INGAME);
			case "s": 
			case "suicide": 
				condOk = (GameState.state == GameState.INGAME && GameClient.getMyClient().state == ClientState.PLAYING);
			default : 
				condOk = true;
		}
		
		if(!condOk) addMessage("You cant use this command at this time."); 
		
		return condOk;
	}
	
	@Override
	protected int getFocusChatKey() {
		return testClientConfig.focusChatKey;
	}
	
	
	
}
