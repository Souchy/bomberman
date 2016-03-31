package bombermantest.events;

import com.google.common.eventbus.Subscribe;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.events.PlayerDeathEvent;

import bombermantest.enums.ClientState;
import bombermantest.enums.GameState;
import bombermantest.main.TestGame;
import bombermantest.objects.characters.playables.BombermanStats;

public class PlayerDeathEventListener {
	
	private String[] words = new String[]{
			"killed", "decimated", "destroyed", "exploded", "anihilated", "BURNED", "ate", "rolled over"
	};
	
	@Subscribe 
	public void listen(PlayerDeathEvent event){
		System.out.println("GREEEEEEEEE");
		
		BombermanStats killerStats = (BombermanStats) event.getKiller().getWeaponHolderStats();
		BombermanStats deadStats = (BombermanStats) event.getDead().getStats();


		String word = words[AConstants.rnd.nextInt(words.length)];
		if(killerStats == deadStats){
			TextPopupEvent.post(killerStats.player.client.name + " " + word + " himself like a n00b.");
		}else{
			TextPopupEvent.post(killerStats.player.client.name + " " + word + " " + deadStats.player.client.name + "!");
			killerStats.player.client.kills++;
		}
		
		deadStats.player.client.deaths++;
		
		long playingCount = TestGame.get().getClientList().stream().filter(c -> c.state == ClientState.PLAYING).count();
		if(playingCount == 0){
			GameStateChangeEvent.post(GameState.state, GameState.OUTGAME);
		}

		System.out.println("GRAAAAAAAAA");
	}


}
