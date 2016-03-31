package bombermantest.events;

import com.mygdx.engine.game.Universe;

import bombermantest.enums.GameState;

public class GameStateChangeEvent {
	
	private GameState before;
	private GameState after;
	
	private GameStateChangeEvent(GameState beforee, GameState afterr) {
		before = beforee;
		after = afterr;
	}
	
	public static void post(GameState before, GameState after){
		Universe.bus.post(new GameStateChangeEvent(before, after));
	}
	
	public GameState getBefore(){
		return before;
	}
	
	public GameState getAfter(){
		return after;
	}

}
