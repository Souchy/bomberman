package bombermantest.client.ui.login;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.screens.Screen3d;

import bombermantest.client.main.testClientConfig;
import bombermantest.client.network.client.game.CGame;
import bombermantest.client.network.client.login.LoginClient;
import bombermantest.network.packets.enums.LoginClientPackets;

@SuppressWarnings("rawtypes")
public class GameServerListScreen extends Screen3d {

	private static GameServerListScreen singleton;
	
	@SuppressWarnings("unchecked")
	public static GameServerListScreen get(){
		if(singleton == null){
			singleton = new GameServerListScreen(); 
			singleton.create(null);
		}
		return singleton;
	}

	private VisTable table;
	
	@Override
	public void postCreateHook() {
		hud.setDebugAll(true);
		
        // Table
		table = new VisTable(true);
		hud.addActor(table);
		
	}

	@Override
	public void preDrawHook(float delta) { }

	@Override
	public void postDrawHook(float delta) { }

	public void clearList() {
		table.clear();
		table.pack();
	}
	
	private boolean selectionListenerEnabled = true;
	
	public void add(CGame server) {
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
		
		List<Cell<Label>> row = new ArrayList<>();
        
		row.add(table.add(server.serverName));
		row.add(table.add(server.map));
		row.add(table.add(server.mode.name()));
		row.add(table.add(Boolean.toString(server.hasPassword)));
		row.add(table.add(Byte.toString(server.nbPlayers)));
		row.add(table.add(Byte.toString(server.nbCapacity)));
		
		table.row();
		
		table.pack();
		
		row.forEach(c -> c.getActor().addListener(listener));
		row.forEach(c -> c.getActor().addListener(AConstants.hoverEffect));
	}
	

	
	private void popup(long serverID){
		//Dialog pop = new Dialog("Enter password", new Skin());
		float windowW = 400;
		float windowH = 150;
		
		VisWindow pop = new VisWindow("Enter password");
		pop.setBounds(getHudCamWidth()/2 - windowW/2, getHudCamHeight()/2 - windowH/2, windowW, windowH);
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
		
		hud.addActor(pop);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		table.setPosition(hud.getWidth()/2 - table.getWidth()/2, hud.getHeight()/2 - table.getHeight()/2);
	}

}
