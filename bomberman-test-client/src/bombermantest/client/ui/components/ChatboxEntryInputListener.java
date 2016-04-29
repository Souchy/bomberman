package bombermantest.client.ui.components;

import java.util.HashMap;

import bombermantest.client.main.testClientConfig;
import bombermantest.client.network.client.game.GameClient;
import bombermantest.client.ui.components.commands.ChatCommand;
import bombermantest.network.packets.enums.GameClientPackets;
import bombermantest.ui.components.AChatboxEntryInputListener;
import scripts.scala.ui.components.commands.All;
import scripts.scala.ui.components.commands.Suicide;

public class ChatboxEntryInputListener extends AChatboxEntryInputListener {
	

	public static AChatboxEntryInputListener get(){
		if(singleton == null) singleton = new ChatboxEntryInputListener();
		return singleton;
	}
	
	private HashMap<String, ChatCommand> commands = new HashMap<>();
	
	private ChatboxEntryInputListener() {
		commands.put("all", 	new All());
		commands.put("s", 		new Suicide());
		commands.put("suicide", commands.get("s"));
	}
	
	@Override
	protected void enterMessage(String text){
		if(text.startsWith("/")){
			enterCommand(text.substring(1));
		} else {
			text = GameClient.getMyClient().name + " : " + text;
			//area.setColor(GameClient.getMyClient().geTeamColor());
			area.addMessage(text);
			
			GameClientPackets.CHAT.compose(GameClient.get().getSession(), text, GameClient.getMyClient().team); 
		}
	}
	
	@Override
	protected void enterCommand(String text){
		//area.setColor(Color.YELLOW);
		String[] textt = text.split(" ", 2);
		String command = textt[0].toLowerCase();
		String input = textt.length == 1 ? "" : textt[1];
		
		/*System.out.println("text = ["+text+"]");
		System.out.println("command = ["+command+"]");
		System.out.println("input = ["+input+"]");*/
		
		ChatCommand c = commands.get(command);
		if(c == null){
			area.addMessage("Unknown command");
		}
		else
		if(!c.condition(input)){
			area.addMessage("You cant use this command at this time."); 
		}
		else{
			c.accept(input);
		}
	}
	
	@Override
	public int getFocusChatKey() {
		return testClientConfig.focusChatKey;
	}
	
}
