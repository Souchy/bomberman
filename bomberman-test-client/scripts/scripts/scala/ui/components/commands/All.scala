package scripts.scala.ui.components.commands

import bombermantest.enums.GameState
import bombermantest.network.packets.enums.GameClientPackets
import bombermantest.client.network.client.game.GameClient
import com.badlogic.gdx.graphics.Color
import bombermantest.client.ui.components.commands.ChatCommand

class All extends ChatCommand {
  
  def condition(input: String) = {
    GameState.state == GameState.INGAME
  }
  
  def accept(input: String) = {
    println("accepting all command")
    
    var input2 = "[ALL] " + GameClient.getMyClient().name + " : " + input;
    var y = Color.rgba8888(Color.YELLOW);
    val x: Object = y.asInstanceOf[Object];
		GameClientPackets.CHAT.compose(GameClient.get().getSession(), input2, x); 
		//area.addMessage(input); 
    
    println(input2)
  }
  
}