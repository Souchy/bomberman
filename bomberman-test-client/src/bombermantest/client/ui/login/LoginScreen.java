package bombermantest.client.ui.login;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.mygdx.engine.actors.MyLabel;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.screens.Screen3d;
import com.mygdx.engine.services.FontsLoader;

import bombermantest.client.main.ClientGame;
import bombermantest.client.main.testClientConfig;
import bombermantest.client.network.client.login.LoginClient;
import bombermantest.client.ui.OptionsScreen;

@SuppressWarnings("rawtypes")
public class LoginScreen extends Screen3d  {

	private static LoginScreen singleton;
	
	@SuppressWarnings("unchecked")
	public static LoginScreen get(){
		if(singleton == null){
			singleton = new LoginScreen(); 
			singleton.create(null);
		}
		return singleton;
	}
	
	//public VisWindow window;
	//private int windowW = 500;
	//private int windowH = 300;
	//private MyLabel test;
	private MyLabel play;
	private MyLabel options;
	private VisTextField username;
	private VisTextField password;
	private VisTable table;
	private VisSelectBox<Integer> portList;
	
	@Override
	public void postCreateHook() {
		
		setHudCamWidth(400);
		setHudCamHeight(600);
		
		hud.setDebugAll(false);
		// Login window
        //window = new VisWindow("", false);
        //window.setBounds(getViewportWidth()/2 - windowW/2, getViewportHeight()/2 - windowH/2, windowW, windowH);
        //window.setMovable(false);
        //hud.addActor(window);
        
        // Table
		table = new VisTable(true);
		hud.addActor(table);
        
		// Keyboard
        hud.addListener(new InputListener() {
			public boolean keyDown(InputEvent event, int keycode) {
				//if (keycode == Keys.ESCAPE) game.setScreen(game.resume);
				if (keycode == Keys.ENTER) pressPlay();
				return false;
			}
		});
		

		// Username textfield
		username = new VisTextField();
		username.setText(testClientConfig.username);
		username.setMessageText("username");
		username.pack();
		table.add(username);
		table.row();
		
		
		// Username textfield
		password = new VisTextField();
		password.setText(testClientConfig.password);
		password.setMessageText("password");
		password.setPasswordMode(true);
		password.pack();
		table.add(password);
		table.row();
		
		
		// Play
        play = new MyLabel("play", FontsLoader.singleton.hongkong, Color.WHITE, 60);
        play.pack();
		//resume.setBounds(0, 0, window.getWidth(), resume.getHeight());
		//resume.setAlignment(Align.center);
		//resume.setPosition(0, window.getHeight()/2);
        play.addListener(AConstants.hoverEffect);
        play.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//game.setScreen(game.login);
				pressPlay();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
       // hud.addActor(play);
        table.add(play);
		table.row();
		

		// Options
		options = new MyLabel("Options", FontsLoader.singleton.hongkong, Color.WHITE, 30);
		options.pack();
		options.addListener(AConstants.hoverEffect);
		options.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				ClientGame.get().setScreen(OptionsScreen.get());
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		hud.addActor(options);
		
		portList = new VisSelectBox<Integer>();
		portList.setSelected(testClientConfig.loginPortSelected);
		portList.setItems(testClientConfig.loginPorts);
		/*portList.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("port : " + portList.getSelected());
				
			}
		});*/
        table.add(portList);
		table.row();
		
		
		table.pack();
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
	
	private void pressPlay(){
		if(LoginClient.get().session != null) LoginClient.get().restart();
		
		testClientConfig.username = username.getText();
		testClientConfig.password = password.getText();
		testClientConfig.loginPortSelected = portList.getSelected().shortValue();
		
		LoginClient.get().port = (short) testClientConfig.loginPortSelected;
		LoginClient.get().on();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		table.setPosition(hud.getWidth()/2 - table.getWidth()/2, hud.getHeight()/2 - table.getHeight()/2);
		options.setPosition(hud.getWidth() - options.getWidth() - 10, hud.getHeight() - options.getHeight());
	}

}
