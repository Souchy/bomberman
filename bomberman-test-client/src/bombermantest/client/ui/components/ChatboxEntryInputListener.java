package bombermantest.client.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.engine.events.PlayerDeathEvent;

import bombermantest.client.main.testClientConfig;
import bombermantest.client.network.client.game.GameClient;
import bombermantest.enums.ClientState;
import bombermantest.enums.GameState;
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
			text = GameClient.getMyClient().name + " : " + text;
			area.setColor(GameClient.getMyClient().geTeamColor());
			area.addMessage(text);
			
			GameClientPackets.CHAT.compose(GameClient.get().getSession(), text, GameClient.getMyClient().team); 
		}
	}
	
	@Override
	protected void enterCommand(String text){
		area.setColor(Color.YELLOW);
		String[] textt = text.split(" ", 1);
		String command = textt[0].toLowerCase();
		String input = textt.length == 1 ? "" : textt[1];
		
		if(!commandCondition(command)) return;
		
		switch(command){
			case "all": 
				input = "[ALL] " + GameClient.getMyClient().name + " : " + input;
				GameClientPackets.CHAT.compose(GameClient.get().getSession(), input, Color.rgba8888(Color.YELLOW)); 
				area.addMessage(input); 
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
			default : area.addMessage("Commande inconnue."); break;
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
		
		if(!condOk) area.addMessage("You cant use this command at this time."); 
		
		return condOk;
	}
	
	@Override
	public int getFocusChatKey() {
		return testClientConfig.focusChatKey;
	}
	
}
