package bombermantest.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.engine.actors.MyLabel;
import com.mygdx.engine.screens.Screen3d;
import com.mygdx.engine.services.FontsLoader;

import bombermantest.enums.GameState;
import bombermantest.ui.components.ChatboxArea;
import bombermantest.ui.components.ChatboxEntry;

@SuppressWarnings("rawtypes")
public class OutGameScreen extends Screen3d {

	private static OutGameScreen singleton;
	
	@SuppressWarnings("unchecked")
	public static OutGameScreen get(){
		if(singleton == null){
			singleton = new OutGameScreen(); 
			singleton.create(null);
		}
		return singleton;
	}

	public MyLabel timerLabel;
	public MyLabel test;
	
	@Override
	public void postCreateHook() {
		timerLabel = new MyLabel(Long.toString(GameState.timeRemaining() / 1000), FontsLoader.singleton.hongkong, Color.WHITE, 60);
		timerLabel.pack();
		timerLabel.setPosition(getHudCamWidth()/2 - timerLabel.getWidth()/2, getHudCamHeight() - timerLabel.getHeight() - 50);
		hud.addActor(timerLabel);
		
		// TODO : OutGameScreen UI : chatbox
		
		test = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		test.setPosition(30, 30);
		hud.addActor(test);
		
		// Add the chatbox Hud
		//hud.getActors().addAll(AChatHud.get().hud.getActors());
        hud.addActor(ChatboxEntry.get());
        hud.addActor(ChatboxArea.get());
	}
	
	@Override
	public void preDrawHook(float delta) {
		timerLabel.setText(Long.toString(GameState.timeRemaining() / 1000));
	}
	
	@Override
	public void postDrawHook(float delta) {
		// affiche l'écran de score par dessus le reste
		ScoreboardScreen.get().drawHud(delta);
		
		// Draw the chatbox Hud
		//AChatHud.get().drawHud(delta);
		
		test.setText("OutGameScreen here gamestate=["+GameState.state+"] gamestatetimer=["+GameState.timer+"]");
	}
	
	

}
