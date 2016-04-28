package scripts.scala.ui.components.commands

import bombermantest.client.network.client.game.GameClient
import bombermantest.enums.GameState
import bombermantest.network.packets.enums.GameClientPackets
import com.mygdx.engine.events.PlayerDeathEvent
import bombermantest.enums.ClientState
import bombermantest.client.ui.components.commands.ChatCommand

class Suicide extends ChatCommand {

  def condition(input: String) = {
    GameState.state == GameState.INGAME && GameClient.getMyClient().state == ClientState.PLAYING
  }

  def accept(input: String) = {
    println("accepting suicide command")
    PlayerDeathEvent.post(GameClient.getMyClient().player, GameClient.getMyClient().player);

    GameClientPackets.COMMAND.compose(GameClient.get().getSession(), "suicide");
  }
  
}