package bombermantest.events;

import com.mygdx.engine.game.Universe;

public class TextPopupEvent {
	

	private String text;
	
	private TextPopupEvent(String s){ 
		text = s; 
	}
	
	public static void post(String s){
		Universe.bus.post(new TextPopupEvent(s));
	}

	public String getText() {
		return text;
	}

}
