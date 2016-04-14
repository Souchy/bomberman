package bombermantest.enums;

public enum GameState {
	
	OUTGAME(5000),
	PREPARING(5000),
	INGAME(0),
	;
	
	public static GameState state = OUTGAME;
	public static long timer;
	
	private long duration;
	
	private GameState(long milliseconds){
		duration = milliseconds;
	}
	
	public static void startTimer(){
		timer = System.currentTimeMillis();
	}
	
	public static long timeRemaining(){
		long time = state.duration - (System.currentTimeMillis() - timer);
		return time <= 0 ? 0 : time;
	}
	
	public static void change(GameState next){
		state = next;
		startTimer();
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
