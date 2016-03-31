package bombermantest.game.events;

import com.google.common.eventbus.Subscribe;

import bombermantest.events.CrateDropEvent;
import bombermantest.game.network.game.client.GClientServer;
import bombermantest.network.packets.enums.GameClientPackets;

public class CrateDropEventListener {

	@Subscribe 
	public void listen(CrateDropEvent event){
		//ServerSender.broadcastEntity(booster);
		GameClientPackets.ENTITY.broadcast(GClientServer.get().getSessionList(), event.getDrop());
	}
	
}
