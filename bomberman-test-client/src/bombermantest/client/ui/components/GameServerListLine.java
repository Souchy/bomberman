package bombermantest.client.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.mygdx.engine.configs.AConstants;

import bombermantest.client.main.testClientConfig;
import bombermantest.client.network.client.game.CGame;
import bombermantest.client.network.client.login.LoginClient;
import bombermantest.client.ui.login.GameServerListScreen;
import bombermantest.network.packets.enums.LoginClientPackets;

public class GameServerListLine extends VisTable {


	//private static Skin skin = new Skin();
	private static Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
	private static Color textColor = Color.BLACK;
	private static Color backColor = new Color(112/255f, 122/255f, 135/255f, 1);
	private static Color backHoverColor = new Color(82/255f, 92/255f, 105/255f, 1);
	private static Drawable background;
	private static Drawable backgroundHover;
	private static int pad = 10;

	private static boolean selectionListenerEnabled = true;
	
	static {
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		//skin.add("default", new Texture(pixmap));
	}


	public GameServerListLine(CGame server) {
		//setSkin(skin);
		getSkin().add("white", new Texture(pixmap));
		background = getSkin().newDrawable("white", backColor);
		backgroundHover = getSkin().newDrawable("white", backHoverColor);
		setBackground(background);
		
		InputListener listener = new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(selectionListenerEnabled){
					if(server.hasPassword){
						AConstants.hoverEffectEnabled = false;
						selectionListenerEnabled = false;
						popup(server.serverID);
					}else{
						testClientConfig.serverPassword = "";
						LoginClientPackets.SERVER_SELECTION.compose(LoginClient.get().getSession(), server.serverID);
					}
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		};
		float cellw = 100;
		add(server.serverName).width(cellw).padLeft(pad);
		add(server.map).width(cellw);
		add(server.mode.name()).width(cellw);
		add(Boolean.toString(server.hasPassword)).width(cellw);
		add(Byte.toString(server.nbPlayers)).width(cellw);
		add(Byte.toString(server.nbCapacity)).width(cellw).padRight(pad);
		
		getCells().forEach(c -> {
			//c.getActor().addListener(AConstants.hoverEffect);
			c.getActor().addListener(hoverEffect);
			c.getActor().addListener(listener);
			c.getActor().setColor(textColor);
			c.padTop(pad);
			c.padBottom(pad);
			((Label)c.getActor()).setAlignment(Align.center);
		});

		pack();
	}

	//public GameServerListLine thiz = this;
	
	// Change color on hover
	public InputListener hoverEffect = new InputListener() {
		@Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
			setBackground(backgroundHover);
			super.enter(event, x, y, pointer, fromActor);
		}
		@Override public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
			setBackground(background);
			super.exit(event, x, y, pointer, toActor);
		}
	};

	private void popup(long serverID){
		//Dialog pop = new Dialog("Enter password", new Skin());
		float windowW = 400;
		float windowH = 150;
		
		VisWindow pop = new VisWindow("Enter password");
		
		//pop.setBounds(getHudCamWidth()/2 - windowW/2, getHudCamHeight()/2 - windowH/2, windowW, windowH);
		pop.setBounds(getStage().getWidth()/2 - windowW/2, getStage().getHeight()/2 - windowH/2, windowW, windowH);
		pop.setMovable(false);
		
		VisTextField password = new VisTextField();
		password.setMessageText("password");
		password.setPasswordMode(true);
		password.pack();
		password.setPosition(windowW/2 - password.getWidth()/2, windowH/2);
		
		VisTextButton ok = new VisTextButton("Ok");
		ok.addListener(new InputListener(){
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				testClientConfig.serverPassword = password.getText();
				pop.remove();
				AConstants.hoverEffectEnabled = true;
				selectionListenerEnabled = true;
				LoginClientPackets.SERVER_SELECTION.compose(LoginClient.get().getSession(), serverID);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		ok.pack();
		ok.setPosition(windowW/2 - ok.getWidth() - 10, 10);
		
		VisTextButton cancel = new VisTextButton("Cancel");
		cancel.addListener(new InputListener(){
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				pop.remove();
				AConstants.hoverEffectEnabled = true;
				selectionListenerEnabled = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		cancel.setPosition(windowW/2 + 10, 10);

		pop.addActor(password);
		pop.addActor(ok);
		pop.addActor(cancel);
		//pop.pack();
		//pop.show(hud);
		
		GameServerListScreen.get().hud.addActor(pop);
	}

}
