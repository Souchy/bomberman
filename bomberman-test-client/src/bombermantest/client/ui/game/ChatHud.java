package bombermantest.client.ui.game;

import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.mygdx.engine.screens.Screen3d;
import com.mygdx.engine.services.FontsLoader;

import bombermantest.client.main.testClientConfig;
import bombermantest.client.network.client.game.GameClient;
import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
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
		String input = textt[1];
		switch(command){
			case "all": 
				input = "[ALL] " + GameClient.getMyClient().name + " : " + input;
				GameClientPackets.CHAT.compose(GameClient.get().getSession(), input, Color.rgba8888(Color.YELLOW)); 
				addMessage(input); 
			break;
			default : addMessage("Commande inconnue."); break;
		}
		
	}

	@Override
	protected int getFocusChatKey() {
		return testClientConfig.focusChatKey;
	}
	
	
	
}
