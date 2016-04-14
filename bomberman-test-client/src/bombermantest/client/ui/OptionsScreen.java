package bombermantest.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.mygdx.engine.actors.MyLabel;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.screens.Screen3d;
import com.mygdx.engine.services.FontsLoader;

import bombermantest.client.main.ClientGame;
import bombermantest.client.ui.login.LoginScreen;

@SuppressWarnings("rawtypes")
public class OptionsScreen extends Screen3d {

	private static OptionsScreen singleton;
	
	@SuppressWarnings("unchecked")
	public static OptionsScreen get(){
		if(singleton == null){
			singleton = new OptionsScreen(); 
			singleton.create(null);
		}
		return singleton;
	}

	//public VisWindow window;
	public int windowW = 500;
	public int windowH = 300;
	public MyLabel test;
	public MyLabel play;
	public MyLabel options;
	public VisTextField username;
	public VisTextField password;
	public VisTable table;
	
	@Override
	public void postCreateHook() {
		hud.setDebugAll(true);
		// Login window
        //window = new VisWindow("", false);
        //window.setBounds(getViewportWidth()/2 - windowW/2, getViewportHeight()/2 - windowH/2, windowW, windowH);
        //window.setMovable(false);
        //hud.addActor(window);
        
        // Table
		table = new VisTable(true);
		table.setPosition(hud.getWidth()/2 - table.getWidth()/2, hud.getHeight()/2);
		table.setDebug(true); 
		hud.addActor(table);
        
		// Keyboard
        hud.addListener(new InputListener() {
			public boolean keyDown(InputEvent event, int keycode) {
				//if (keycode == Keys.ESCAPE) game.setScreen(game.resume);
				return false;
			}
		});
		
		// Resume
        play = new MyLabel("play", FontsLoader.singleton.hongkong, Color.WHITE, 60);
        play.pack();
        play.setPosition(hud.getWidth()/2 - play.getWidth()/2, hud.getHeight()/6*3);
		//resume.setBounds(0, 0, window.getWidth(), resume.getHeight());
		//resume.setAlignment(Align.center);
		//resume.setPosition(0, window.getHeight()/2);
        play.addListener(AConstants.hoverEffect);
        play.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				ClientGame.get().setScreen(LoginScreen.get());
				return super.touchDown(event, x, y, pointer, button);
			}
		});
        hud.addActor(play);
		

		// Options
		options = new MyLabel("Options", FontsLoader.singleton.hongkong, Color.WHITE, 60);
		options.pack();
		options.setPosition(hud.getWidth()/2 - options.getWidth()/2, hud.getHeight()/6*2);
		options.addListener(AConstants.hoverEffect);
		options.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				ClientGame.get().setScreen(OptionsScreen.get());
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		hud.addActor(options);

		
		// Username textfield
		username = new VisTextField("usernamamerd");
		//username.pack();
		//username.setPosition(window.getWidth()/2 - username.getWidth()/2, window.getHeight()/6*1);
		//window.addActor(username);
		table.add(username);
		table.row();
		

		// Username textfield
		password = new VisTextField("password");
		//password.pack();
		//password.setPosition(window.getWidth()/2 - password.getWidth()/2, window.getHeight()/6*1);
		table.add(password);
		table.row();
		

		//window.addActor(username);
		///window.addActor(password);
		
		

		// test
        test = new MyLabel("test", FontsLoader.singleton.hongkong, Color.WHITE, 60);
        test.pack();
        test.setPosition(0,100);
		//resume.setBounds(0, 0, window.getWidth(), resume.getHeight());
		//resume.setAlignment(Align.center);
		//resume.setPosition(0, window.getHeight()/2);
        test.addListener(AConstants.hoverEffect);
        test.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				ClientGame.get().setScreen(LoginScreen.get());
				return super.touchDown(event, x, y, pointer, button);
			}
		});
        hud.addActor(test);
		
	}

	@Override
	public void preDrawHook(float delta) {
		// affiche quand même l'écran de jeu en arrière plan
		//game.game.draw(delta);
		//game.game.drawHud(delta);
	}

	@Override
	public void postDrawHook(float delta) {
		
	}
	

	@Override
	public void resize(int width, int height) {
		hud.getViewport().update(width, height, true);
		

		table.setPosition(hud.getWidth()/2 - table.getWidth()/2, hud.getHeight()/2 - table.getHeight()/2);
        //play.setPosition(hud.getWidth()/2 - play.getWidth()/2, table.getY() - play.getHeight() - 10);
		options.setPosition(hud.getWidth() - options.getWidth() - 10, hud.getHeight() - options.getHeight());
	}

}
