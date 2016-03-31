package bombermantest.events;

import com.mygdx.engine.game.Universe;
import com.mygdx.engine.objects.items.Booster;

public class CrateDropEvent {

	private Booster booster;
	
	private CrateDropEvent(Booster b){ 
		booster = b; 
	}
	
	public static void post(Booster booster){
		Universe.bus.post(new CrateDropEvent(booster));
	}

	public Booster getDrop() {
		return booster;
	}
}
