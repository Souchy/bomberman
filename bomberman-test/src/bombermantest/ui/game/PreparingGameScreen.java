package bombermantest.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.engine.actors.MyLabel;
import com.mygdx.engine.screens.Screen3d;
import com.mygdx.engine.services.FontsLoader;

import bombermantest.enums.GameState;
import bombermantest.ui.components.ChatboxArea;
import bombermantest.ui.components.ChatboxEntry;

@SuppressWarnings("rawtypes")
public class PreparingGameScreen extends Screen3d {

	private static PreparingGameScreen singleton;
	
	@SuppressWarnings("unchecked")
	public static PreparingGameScreen get(){
		if(singleton == null){
			singleton = new PreparingGameScreen(); 
			singleton.create(null);
		}
		return singleton;
	}

	public MyLabel timerLabel;
	public MyLabel test;
	

	@Override
	public void show() {
		super.show();
   		GameScreen.get().resetCamTarget();
        System.out.println("PreparingGameScreen.show, camtarget = ["+GameScreen.get().camTarget+"]");

		// Add the Chatbox and Scoreboard huds
        hud.addActor(ChatboxEntry.get());
        hud.addActor(ChatboxArea.get());
        hud.addActor(Scoreboard.get());
	}
	
	@Override
	public void postCreateHook() {
		timerLabel = new MyLabel(Long.toString(GameState.timeRemaining() / 1000), FontsLoader.singleton.hongkong, Color.WHITE, 60);
		timerLabel.pack();
		timerLabel.setPosition(hud.getWidth() / 2 - timerLabel.getWidth()/2, hud.getHeight() - timerLabel.getHeight() - 50);
		hud.addActor(timerLabel);

		// TODO : OutGameScreen UI : chatbox

		test = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		test.setPosition(30, 30);
		hud.addActor(test);
	}
	
	@Override
	public void preDrawHook(float delta) {
		timerLabel.setText(Long.toString(GameState.timeRemaining() / 1000));
		
   		// affiche l'écran de jeu en dessous de tout
   		GameScreen.get().draw(delta);
	}
	
	@Override
	public void postDrawHook(float delta) {
		// affiche le hud de l'écran de jeu par-dessus le reste
		GameScreen.get().drawHud(delta);
		
		// affiche l'écran de score par dessus le reste
		//ScoreboardScreen.get().drawHud(delta);
		
		// Draw the chatbox Hud -> no cuz its already drawn via the GameScreen.get().drawHud(delta); 
		//AChatHud.get().drawHud(delta);
		
		test.setText("PreparingGameScreen here gamestate=["+GameState.state+"] gamestatetimer=["+GameState.timer+"]");
	}
	

}
