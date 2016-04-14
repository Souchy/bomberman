package bombermantest.enums;

public enum GameState {
	
	OUTGAME(5000),
	PREPARING(5000),
	INGAME(0),
	;
	
	public static GameState state = OUTGAME;
	public static long timer;
	
	public long duration;
	
	private GameState(long milliseconds){
		duration = milliseconds;
	}
	
	public void startTimer(){
		timer = System.currentTimeMillis();
	}
	
	public long timeRemaining(){
		return duration - (System.currentTimeMillis() - timer);
	}
	
	public static void change(GameState next){
		state = next;
		state.startTimer();
	}
	
	public GameState getNext(){
		switch(this){
		case OUTGAME : return PREPARING;
		case PREPARING : return INGAME;
		case INGAME : return OUTGAME;
		default : return null;
		}
	}
	
}
